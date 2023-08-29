package com.forleven.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forleven.student.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
