package com.mumSchud.springbootmum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "FACULTY_TBL")
public class Faculty {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phonenumber;
    private String address;
    
    
    
	public Faculty(String name, String phonenumber, String address) {
		this.name=name;
		this.phonenumber=phonenumber;
		this.address=address;
	}
	public Faculty() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String number) {
		this.phonenumber = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String position) {
		this.address = position;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+" "+name+" "+phonenumber+" "+ address;
	}
	
}