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

import com.mumSchud.springbootmum.entity.Course;
import com.mumSchud.springbootmum.entity.Faculty;
import com.mumSchud.springbootmum.entity.Section;
import com.mumSchud.springbootmum.entity.Student;
import com.mumSchud.springbootmum.service.CourseService;
import com.mumSchud.springbootmum.service.SectionService;
import com.mumSchud.springbootmum.service.studentService;
import com.mumSchud.springbootmum.service.facultyService;


@Controller
public class SectionController { 

	  @Autowired
	    private SectionService service;
	  @Autowired
	    private CourseService courseService;
	  @Autowired
	    private facultyService facultyService;
	  @Autowired
	    private studentService studentService;

	  
	@RequestMapping("/sections")
	public String sectionsPortal(Model model) {
    	List<Section>sections=service.getSections();
    	List<Course>courses=courseService.findAll();
    	List<Faculty>faculties=facultyService.getFaculties();
    	model.addAttribute("sections", sections);
    	model.addAttribute("courses", courses);
    	model.addAttribute("faculties", faculties);
    	  return "sections";	}
	
	@RequestMapping("/sections/{id}")
	public String updatePage(Model model,@PathVariable Long id) {
		Section section=service.getSectionById(id);
		List<Course>courses=courseService.findAll();
    	List<Faculty>faculties=facultyService.getFaculties();
    	model.addAttribute("section", section);
    	model.addAttribute("courses", courses);
    	model.addAttribute("faculties", faculties);
		return "sectionUpdate";
	}
	
	@RequestMapping("/sections/detail/{id}")
	public String detailPage(Model model,@PathVariable Long id) {
		Section section=service.getSectionById(id);
		List<Student> students=section.getStudents();
		
		List<Course>courses=courseService.findAll();
    	List<Faculty>faculties=facultyService.getFaculties();
    	model.addAttribute("section", section);
    	model.addAttribute("courses", courses);
    	model.addAttribute("faculties", faculties);
    	model.addAttribute("students", students);
    	
		return "sectionDetail";
	}
	


	@RestController
	public class SectionRestController {

	    @PostMapping("/api/sections")
	    public ModelAndView addSection(Model model ,@RequestParam String roomLocation,
                @RequestParam Integer capacity,
                @RequestParam int facultyId,@RequestParam int courseId) {
	    	System.err.print(courseId + " ANAS "+facultyId );
	    	Course course= courseService.getOne(courseId);
	    	Faculty faculty= facultyService.getFacultyById(facultyId);
	    	Section section=new Section(roomLocation,capacity,course,faculty);
	    	service.saveSection(section);
	    	List<Section>sections=service.getSections();
	    	List<Course>courses=courseService.findAll();
	    	List<Faculty>faculties=facultyService.getFaculties();
	    	model.addAttribute("sections", sections);
	    	model.addAttribute("courses", courses);
	    	model.addAttribute("faculties", faculties);

	        ModelAndView mav = new ModelAndView("redirect:/sections");
	        return mav;

	    }

	    @GetMapping("/api/sections")
	    public List<Section> findAllsections() {
        return service.getSections();
	    }

	    @GetMapping("/api/sections/{id}")
	    public Section findSectionsById(@PathVariable Long id) {
	        return service.getSectionById(id);
	    }


	    @PutMapping("/api/sections/{id}")
	    public Section updateSection( @RequestBody Section section,@PathVariable Long id) {
	        System.err.println(section.getFaculty().getId());
	    	return service.updateSection(section,id);
	    }

	    @DeleteMapping("/api/sections/{id}")
	    public ModelAndView deleteSection(@PathVariable Long id) {
	        service.deleteSection(id);
	        ModelAndView mav = new ModelAndView("redirect:/sections");
	        return mav;
	    }
	    
	    @GetMapping("/api/sections/{id}/addStudent/{stdId}")
	    public ModelAndView addStudentToSection(Model model ,@PathVariable Long id,
	    		@PathVariable int stdId) {
	    	Student sdt=studentService.getStudentById(stdId);
	    	Section section=service.getSectionById(id);
	    	section.addStudent(sdt);
	    	service.saveSection(section);
	    	List<Section>sections=service.getSections();
	    	model.addAttribute("sections", sections);

	        ModelAndView mav = new ModelAndView("redirect:/student");
	        return mav;

	    }
	    
	
    @GetMapping("/api/sections/{id}/removeStudent/{stdId}")
    public ModelAndView removeStudentfromSection(Model model ,@PathVariable Long id,
    		@PathVariable int stdId) {
    	Student sdt=studentService.getStudentById(stdId);
    	Section section=service.getSectionById(id);
    	section.RemoveStudent(sdt);
    	service.saveSection(section);
    	List<Section>sections=service.getSections();
    	model.addAttribute("sections", sections);

        ModelAndView mav = new ModelAndView("redirect:/student");
        return mav;

    }
    
	}
}
