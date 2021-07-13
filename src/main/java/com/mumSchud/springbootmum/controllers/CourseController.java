package com.mumSchud.springbootmum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumSchud.springbootmum.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseService service;

	@GetMapping
	public String listCourses(Model model) {
		model.addAttribute("courses", service.findAll());
		return "courses";
	}
	
	@PostMapping
	public String createCourse(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "code") String code) {
		service.addCourse(name, code);
		return listCourses(model);
	}
	
	@PostMapping("{course}/update")
	public String updateCourse(Model model, @PathVariable("course") int courseId, @RequestParam(name = "name") String name, @RequestParam(name = "code") String code) {
		System.out.println(courseId);
		service.updateCourse(courseId, name, code);
		return listCourses(model);
	}
	
	@GetMapping("{course}/delete")
	public String deleteCourse(Model model, @PathVariable("course") int courseId) {
		service.deleteCourse(courseId);
		return listCourses(model);
	}
}
