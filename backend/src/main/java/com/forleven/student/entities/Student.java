package com.forleven.student.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String enrollment;
	private String firstName;
	private String lastName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_student_course", 
			joinColumns = @JoinColumn(name = "student_enrollment"), 
			inverseJoinColumns = @JoinColumn(name = "course_id") 
			)
	private List<Course> courses = new ArrayList<>();

	public Student() {
	}

	public Student(Long id, String enrollment, String firstName, String lastName) {
		this.id = id;
		this.enrollment = enrollment;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(Long id, String enrollment, String firstName, String lastName, List<Course> courses) {
		this.id = id;
		this.enrollment = enrollment;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = courses;
	}

	public Student(String enrollment, String firstName, String lastName) {
		this.enrollment = enrollment;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(String enrollment, String firstName, String lastName, List<Course> courses) {
		this.enrollment = enrollment;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Course> getCourses() {
		return courses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enrollment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(enrollment, other.enrollment);
	}

}
