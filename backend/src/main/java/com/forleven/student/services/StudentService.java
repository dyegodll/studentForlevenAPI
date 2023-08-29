package com.forleven.student.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forleven.student.dto.StudentDTO;
import com.forleven.student.entities.Student;
import com.forleven.student.repositories.StudentRepository;
import com.forleven.student.services.exceptions.DataBaseException;
import com.forleven.student.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentRepository repository;
	
	@Transactional(readOnly = true)
	public Page<StudentDTO> findAllPaged(Pageable pageable){
		Page<Student> list = repository.findAll(pageable);
		return list.map( x -> new StudentDTO(x) );
	}
	
	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		Optional<Student> obj = repository.findById(id);
		Student entity = obj.orElseThrow( () -> new ResourceNotFoundException("Student id " +id+", Not Found!"));
		return new StudentDTO(entity);
	}
	
	@Transactional
	public StudentDTO insert(StudentDTO dto) {
		Student entity = new Student();
		entity.setId( repository.count() + 1 );
		copyDtoToEntity(dto, entity);
		if( repository.findByEnrollment(entity.getEnrollment()) == null ) {
			entity = repository.save(entity);
			return new StudentDTO(entity);
		}else throw new DataBaseException("This enrollment already exists!");
	}
	
	
	@Transactional
	public StudentDTO update(Long id, StudentDTO dto) {
		try {
			Student entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new StudentDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Student id "+ id + ", Not found to be Updated!");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Student id "+ id + ", Not found!");
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation");
		}
	}
	
	private void copyDtoToEntity(StudentDTO dto, Student entity) {
		entity.setEnrollment(dto.getEnrollment());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
	}
	
}
