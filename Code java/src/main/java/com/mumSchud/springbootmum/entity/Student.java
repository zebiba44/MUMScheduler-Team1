package com.mumSchud.springbootmum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "STUDENT_TBL")
public class Student {

	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	private int age;
	private String email;
	private String password;

	private final String DEFAULT_PASSWORD = "123456";

	public Student(String firstname, String lastname, int age, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.password = DEFAULT_PASSWORD;
	}

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + ", email="
				+ email + "]";
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
}