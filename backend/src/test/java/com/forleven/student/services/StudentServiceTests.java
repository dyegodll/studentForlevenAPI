package com.forleven.student.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.forleven.student.dto.StudentDTO;
import com.forleven.student.entities.Student;
import com.forleven.student.repositories.StudentRepository;
import com.forleven.student.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
class StudentServiceTests {

	@InjectMocks
	private StudentService service;
	
	@Mock
	private StudentRepository repository;
	
	private Long existingId;
	private Long inexistingId;
	private Student student;
	private StudentDTO dto;

	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		inexistingId = 100L;
		
		student = new Student(existingId, "Enroll_Test", "TestFirstName", "TestLastName");
		dto = new StudentDTO("Enroll_DTO_Test", "TestDTOFirstName", "TestDTOLastName");
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(inexistingId);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(student);
		Mockito.when(repository.getReferenceById(inexistingId)).thenThrow(ResourceNotFoundException.class);
		
		Mockito.when(repository.save(student)).thenReturn(student);
		
	}
	
	@Test
	void updateShouldUpdateObjectWhenIdExists() {
		service.update(existingId, dto);
		student = repository.getReferenceById(existingId);
	
		student.setEnrollment(dto.getEnrollment());
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
	
		student = repository.save(student);
		
		Assertions.assertEquals("TestDTOFirstName", student.getFirstName());
	}
	
	@Test
	void deleteDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(()->{
			service.delete(existingId);
		});
	}
	
	@Test
	void deleteThrowsResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			service.delete(inexistingId);
		});
	}
	
}
