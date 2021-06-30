package com.mumSchud.springbootmum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@Entity
@Table(name = "ENTRY_TBL")
public class Entry {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    
    private LocalDate entryDate;
    private int mpp;
    private int fpp;
    private int fppCPT;
    private int fppOPT;
    private int mppCPT;
    private int mppOPT;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    
	public Entry(String name,LocalDate entryDate, int mpp, int fpp, int fppCPT, int fppOPT,
				int mppCPT, int mppOPT, Faculty faculty) 
	{
		this.setName(name);
		this.setEntryDate(entryDate);
		this.setMpp(mpp);
		this.setFpp(fpp);
		this.setFppCPT(fppCPT);
		this.setFppOPT(fppOPT);
		this.setMppCPT(mppCPT);
		this.setMppOPT(mppOPT);
		this.setFaculty(faculty);
		
	}
	public Entry() {
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
	
	public LocalDate getEntryDate() {
		return  entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	public int getMpp() {
		return mpp;
	}
	public void setMpp(int mpp) {
		this.mpp = mpp;
	}
	public int getFpp() {
		return fpp;
	}
	public void setFpp(int fpp) {
		this.fpp = fpp;
	}
	public int getFppOPT() {
		return fppOPT;
	}
	public void setFppOPT(int fppOPT) {
		this.fppOPT = fppOPT;
	}
	public int getFppCPT() {
		return fppCPT;
	}
	public void setFppCPT(int fppCPT) {
		this.fppCPT = fppCPT;
	}
	public int getMppCPT() {
		return mppCPT;
	}
	public void setMppCPT(int mppCPT) {
		this.mppCPT = mppCPT;
	}
	public int getMppOPT() {
		return mppOPT;
	}
	public void setMppOPT(int mppOPT) {
		this.mppOPT = mppOPT;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+" "+name;
	}
}