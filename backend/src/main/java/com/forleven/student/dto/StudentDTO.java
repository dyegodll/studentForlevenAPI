package com.forleven.student.dto;

import java.io.Serializable;

import com.forleven.student.entities.Student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Required field!")
	@Size(min = 3, message = "not enough characters!")
	private String enrollment;

	@NotBlank(message = "Required field!")
	@Size(min = 3, message = "not enough characters!")
	private String firstName;

	@NotBlank(message = "Required field!")
	@Size(min = 3, message = "not enough characters!")
	private String lastName;

	public StudentDTO() {
	}

	public StudentDTO(String enrollment, String firstName, String lastName) {
		this.enrollment = enrollment;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public StudentDTO(Student entity) {
		enrollment = entity.getEnrollment();
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
