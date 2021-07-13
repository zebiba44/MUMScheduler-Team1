package com.mumSchud.springbootmum.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sections")
public class Section {

	@Id
	@GeneratedValue
    private Long id;
    private String roomLocation;
    private Integer capacity=0;

//    @JoinColumn(name = "block_id")
//    @ManyToOne
//    private Block block;

    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @JoinColumn(name = "faculty_id")
    @ManyToOne
    private Faculty faculty;
    
    @ManyToMany
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setUname(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

//    public Block getBlock() {
//        return block;
//    }
//
//    public void setBlock(Block block) {
//        this.block = block;
//    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    
    public List<Student> getStudents() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }

	public Section(String roomLocation, Integer capacity, Course course, Faculty faculty) {
		this.roomLocation = roomLocation;
		this.capacity = capacity;
		this.course = course;
		this.faculty = faculty;
	}
	
	public Section() {
		
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", roomLocation=" + roomLocation + ", capacity=" + capacity + ", course=" + course.getId()
				+ ", faculty=" + faculty.getId() + ", students=" + "]";
	}
	
	public boolean addStudent(Student student) {
		if(this.students.size()<this.capacity && !findStudent(student.getEmail())) {
			this.students.add(student);
			return true;
		}
		return false;
	}
	
	public boolean RemoveStudent(Student student) {
		int i=0;
		for(Student student1:this.students) {
			if(student1.getId()==(student.getId())) {
				this.students.remove(i);
				return true;
			}
			i++;
		}
		return false;
	
	}
	
	public boolean findStudent(String userName) {
		for(Student student:this.students) {
			if(student.getEmail().compareToIgnoreCase(userName)==0) {
				return true;
			}
		}
		return false;
	}
}
