package com.forleven.student.repositories;

	
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.forleven.student.entities.Student;

	@DataJpaTest
	class StudentRepositoryTests {

		@Autowired
		private StudentRepository repository;

		private Long existingId;
		private Long inexistingId;
		
		@BeforeEach
		void setUp() throws Exception{
			existingId = 1L;
			inexistingId = 100L;
		}
		
		@Test
		void findByIdShouldDoNothingWhenIdDoesNotExist() {
			Assertions.assertDoesNotThrow(()->{
				repository.findById(inexistingId);
			});
		}
		
		@Test
		void findByIdShouldFindByIdObjectWhenIdExists() {
			Optional<Student> obj = repository.findById(existingId);
			Assertions.assertTrue(!obj.isEmpty());
		}
		
		@Test
		void insertShouldInsertObjectWhenEnrollmentsStudentDoesNotExist() {
			Student entity = new Student("Enroll_0100", "Jhon", "Zane");
			Assertions.assertNull(repository.findByEnrollment(entity.getEnrollment()));
			entity = repository.save(entity);
		}
		
		@Test
		void insertShouldInsertObjectWhenGetClassThenEqualsStudent() {
			Student entity = new Student("Enroll_01", "Alex", "Brown");
			Assertions.assertSame(Student.class, entity.getClass());
			entity = repository.save(entity);
		}
		
		@Test
		void updateShouldDoNothingWhenIdDoesNotExist() {
			Assertions.assertDoesNotThrow(()->{
				repository.getReferenceById(inexistingId);
			});
		}
		
		@Test
		void updateShouldUpdateObjectWhenIdExists() {
			Assertions.assertNotNull(repository.getReferenceById(existingId));
		}

		@Test
		void deleteShouldDeleteObjectWhenIdExists() {
			repository.deleteById(existingId);
			Optional<Student> obj = repository.findById(existingId);
			Assertions.assertTrue(obj.isEmpty());
		}
		
		@Test
		void deleteShouldDoNothingWhenIdDoesNotExist() {
			Assertions.assertDoesNotThrow(()->{
				repository.deleteById(inexistingId);
			});
		}

}
