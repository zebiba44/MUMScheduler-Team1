package com.mumSchud.springbootmum.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumSchud.springbootmum.entity.Student;
import com.mumSchud.springbootmum.repository.StudentRepository;

import java.util.List;

@Service
public class studentService {
    @Autowired(required = true)
    private StudentRepository repository;

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }
    
    public Student getStudentByEmail(String email) {
    	return repository.findByEmail(email).orElse(null);
    }

    public String deleteStudent(int id) {
        repository.deleteById(id);
        return "Student removed !! " + id;
    }

    public Student updateStudent(Student student,int id) {
        Student existingStudent = repository.findById(id).orElse(null);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        return repository.save(existingStudent);
    }


}