package com.mumSchud.springbootmum.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumSchud.springbootmum.entity.Entry;
import com.mumSchud.springbootmum.repository.EntryRepository;

import java.util.List;

@Service
public class EntryService {
    @Autowired(required = true)
    private EntryRepository repository;

    public Entry saveEntry(Entry Entry) {
        return repository.save(Entry);
    }

    public List<Entry> getEntries() {
        return repository.findAll();
    }

    public Entry getEntryById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteEntry(int id) {
        repository.deleteById(id);
        return "Entry removed !! " + id;
    }

    public Entry updateEntry(Entry Entry,int id) {
    	Entry existingEntry = repository.findById(id).orElse(null);
    
    	//existingEntry.setName(Entry.getName());
    	//existingEntry.setPhonenumber(Entry.getPhonenumber());
    	//existingEntry.setAddress(Entry.getAddress());
    	
    	System.out.println("___________update service_________________");
    	System.out.println(Entry.getEntryDate());
    	System.out.println("____________________________");
    	
        return repository.save(Entry);
    }

}