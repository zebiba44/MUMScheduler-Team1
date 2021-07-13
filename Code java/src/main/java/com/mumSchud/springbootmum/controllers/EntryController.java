package com.mumSchud.springbootmum.controllers;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mumSchud.springbootmum.entity.Entry;
import com.mumSchud.springbootmum.entity.Faculty;
import com.mumSchud.springbootmum.service.EntryService;
import com.mumSchud.springbootmum.service.facultyService;

@Controller
public class EntryController {
	@Autowired
    private EntryService service;
	@Autowired
	private facultyService serviceFaculty;
	
	@GetMapping("/entries")
	public String getAllentries(Model model) {
		List<Entry>entries=service.getEntries();
		List<Faculty>faculties=serviceFaculty.getFaculties();
    	model.addAttribute("entries", entries);
    	model.addAttribute("faculties", faculties);
    	
		return "entries";
	}
	
	@RequestMapping("/entries/{id}")
	public String updatePage(Model model,@PathVariable int id) {
		Entry Entry=service.getEntryById(id);
		List<Faculty>faculties=serviceFaculty.getFaculties();
		
		System.out.println("____________Entry update________________");
    	System.out.println(Entry);
    	System.out.println("____________________________");
    	model.addAttribute("Entry", Entry);
    	model.addAttribute("faculties", faculties);
		return "EntryUpdate";
	}
	
	
	

	@RequestMapping(value="/entries/update/", method=RequestMethod.POST)
    public String updateEntry(Model model, @Validated @ModelAttribute("Entry") Entry oldEntry, BindingResult result) {
    	
    	System.out.println("____________API Entry_________");
    	System.out.println(oldEntry.getFaculty());
    	/***
    	System.out.println(result.getAllErrors().toString());
    	System.out.println(result.getFieldError());
    	System.out.println(result.getRawFieldValue("entryDate"));
    	System.out.println(result.getFieldValue("entryDate") );
    	System.out.println(result.getFieldValue("faculty") );
    	System.out.println(result.getRawFieldValue("faculty"));
    	***/
    	oldEntry.setEntryDate(LocalDate.parse(result.getFieldValue("entryDate").toString()));
    	
    	System.out.println(oldEntry.getEntryDate());
    	//System.out.println(phoneNumber);
    	
    	System.out.println("____________API Entry_________");
    	//Entry Entry=new Entry(name,phonenumber,address);
    	
        service.updateEntry(oldEntry,oldEntry.getId());
        
        List<Entry>entries=service.getEntries();
		List<Faculty>faculties=serviceFaculty.getFaculties();
    	model.addAttribute("entries", entries);
    	model.addAttribute("faculties", faculties);
    	
    	
    	
		return "entries";
    }
	
	@RestController
	public class RestEntryController {

	    @PostMapping("/api/entries")
	    public ModelAndView addEntry(Model model, @RequestParam String name, 
	    		@RequestParam String entrydate, 
	    		@RequestParam int mpp, 
	    		@RequestParam int fpp, 
	    		@RequestParam int fppcpt, 
	    		@RequestParam int fppopt,
	    		@RequestParam int mppcpt, 
	    		@RequestParam int mppopt, 
	    		@RequestParam String faculty
	    		) {
	    	
	    	System.out.println("__________");
	    	System.out.println(entrydate);
	    	System.out.println(faculty);
	    	System.out.println("______");
	    	
	    	LocalDate edate=null;
			try {
				edate = LocalDate.parse(entrydate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
			serviceFaculty.getFaculties().stream().forEach(facult-> System.out.println(facult));
	    	
	    	Entry Entry=new Entry(name, edate, mpp, fpp, fppcpt, fppcpt, mppcpt, mppcpt, serviceFaculty.getFacultyById(7));
	        service.saveEntry(Entry);
	        List<Entry>entries=service.getEntries();
	    	model.addAttribute("entries", entries);
	        ModelAndView mav = new ModelAndView("redirect:/entries");
	        return mav;
	    }

	    @GetMapping("/api/entries")
	    public List<Entry> findAllentries() {
        return service.getEntries();
	    }

	    @GetMapping("/api/entries/{id}")
	    public Entry findEntryById(@PathVariable int id) {
	        return service.getEntryById(id);
	    }


	    @PutMapping("/api/entries/{id}")
	    public Entry updateEntry(@RequestBody Entry Entry,@PathVariable int id) {
	    	
	    	System.out.println("____________API Entry_________");
	    	System.out.println(Entry);
	    	//System.out.println(phoneNumber);
	    	
	    	System.out.println("____________API Entry_________");
	    	//Entry Entry=new Entry(name,phonenumber,address);
	    	
	        return service.updateEntry(Entry,id);
	    }

	    @DeleteMapping("/api/entries/{id}")
	    public String deleteEntry(@PathVariable int id) {
	        return service.deleteEntry(id);
	    }
	}
}