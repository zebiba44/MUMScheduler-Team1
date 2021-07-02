package edu.miu.cs.cs425.demo.studentsmgmtapp.model;

import java.time.LocalDate;

public class Students {
	private int studentId;
	private String name;
	private LocalDate dateOfAdmission;
	
	public Students() {
		super();
	}
	public Students( String name, LocalDate dateOfAdmission) {
		super();
		this.name = name;
		this.dateOfAdmission = dateOfAdmission;
	}
	
	public Students(int studentId, String name, LocalDate dateOfAdmission) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.dateOfAdmission = dateOfAdmission;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfAdmission() {
		return dateOfAdmission;
	}
	public void setDateOfAdmission(LocalDate dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}
	
	@Override
	public String toString() {
		return String.format("Students [studentId=%s, name=%s, dateOfAdmission=%s]", studentId, name, dateOfAdmission);
	}
	
}
