package com.mumSchud.springbootmum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mumSchud.springbootmum.entity.Student;
import com.mumSchud.springbootmum.service.studentService;

@Controller
public class StudentController { 

	  @Autowired
	    private studentService service;
	  
	@RequestMapping("/students")
	public String studentPortal(Model model) {
		List<Student>students=service.getStudents();
    	model.addAttribute("students", students);
    	  return "students";	}
	
	@RequestMapping("/students/{id}")
	public String updatePage(Model model,@PathVariable int id) {
		Student student=service.getStudentById(id);
    	model.addAttribute("student", student);
		return "studentUpdate";
	}


	@RestController
	public class StudentRestController {

	    @PostMapping("/api/students")
	    public ModelAndView addStudent(Model model ,@RequestParam String firstname,
                @RequestParam String lastname,
                @RequestParam int age,@RequestParam String email) {
	    	System.err.print(firstname+" "+lastname);
	    	Student student=new Student(firstname,lastname,age,email);
	    	service.saveStudent(student);
	    	List<Student>students=service.getStudents();
	    	model.addAttribute("students", students);
	        ModelAndView mav = new ModelAndView("redirect:/students");
	        return mav;

	    }

	    @GetMapping("/api/students")
	    public List<Student> findAllStudents() {
        return service.getStudents();
	    }

	    @GetMapping("/api/students/{id}")
	    public Student findStudentById(@PathVariable int id) {
	        return service.getStudentById(id);
	    }


	    @PutMapping("/api/students/{id}")
	    public Student updateStudent( @RequestBody Student student,@PathVariable int id) {
	        return service.updateStudent(student,id);
	    }

	    @DeleteMapping("/api/students/{id}")
	    public ModelAndView deleteStudent(@PathVariable int id) {
	        service.deleteStudent(id);
	        ModelAndView mav = new ModelAndView("/students");
	        return mav;
	    }
	}
}