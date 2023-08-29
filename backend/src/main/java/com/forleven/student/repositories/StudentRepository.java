package com.forleven.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forleven.student.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByEnrollment(String enrollment);

}
