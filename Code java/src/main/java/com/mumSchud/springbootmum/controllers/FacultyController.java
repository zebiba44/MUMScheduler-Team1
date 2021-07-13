package com.mumSchud.springbootmum.controllers;

import java.io.Console;
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

import com.mumSchud.springbootmum.entity.Faculty;
import com.mumSchud.springbootmum.entity.Student;
import com.mumSchud.springbootmum.service.facultyService;

@Controller
public class FacultyController {
	 @Autowired
    private facultyService service;
	
	@GetMapping("/faculties")
	public String getAllFaculties(Model model) {
		List<Faculty>faculties=service.getFaculties();
    	model.addAttribute("faculties", faculties);
		return "faculties";
	}
	
	@RequestMapping("/faculties/{id}")
	public String updatePage(Model model,@PathVariable int id) {
		Faculty faculty=service.getFacultyById(id);
		System.out.println("____________faculty control________________");
    	System.out.println(faculty);
    	System.out.println("____________________________");
    	model.addAttribute("faculty", faculty);
		return "facultyUpdate";
	}
	
	@RestController
	public class RestFacultyController {

	   

	    @PostMapping("/api/faculties")
	    public ModelAndView addFaculty(Model model, @RequestParam String name,
                @RequestParam String phonenumber,
                @RequestParam String address) {
	    	Faculty faculty=new Faculty(name,phonenumber,address);
	        service.saveFaculty(faculty);
	        List<Faculty>faculties=service.getFaculties();
	    	model.addAttribute("faculties", faculties);
	        ModelAndView mav = new ModelAndView("redirect:/faculties");
	        return mav;
	    }

	    @GetMapping("/api/faculties")
	    public List<Faculty> findAllFaculties() {
        return service.getFaculties();
	    }

	    @GetMapping("/api/faculties/{id}")
	    public Faculty findFacultyById(@PathVariable int id) {
	        return service.getFacultyById(id);
	    }


	    @PutMapping("/api/faculties/{id}")
	    public Faculty updateFaculty(@RequestBody Faculty faculty,@PathVariable int id) {
	    	
	    	System.out.println("____________API faculty_________");
	    	System.out.println(faculty);
	    	//System.out.println(phoneNumber);
	    	
	    	System.out.println("____________API faculty_________");
	    	//Faculty faculty=new Faculty(name,phonenumber,address);
	    	
	        return service.updateFaculty(faculty,id);
	    }

	    @DeleteMapping("/api/faculties/{id}")
	    public String deleteFaculty(@PathVariable int id) {
	        return service.deleteFaculty(id);
	    }
	}
}