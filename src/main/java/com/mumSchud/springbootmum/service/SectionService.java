package com.mumSchud.springbootmum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumSchud.springbootmum.entity.Section;
import com.mumSchud.springbootmum.repository.SectionRepository;

@Service
public class SectionService {
	@Autowired(required = true)
	private SectionRepository repository;

	public Section saveSection(Section section) {
		return repository.save(section);
	}

	public List<Section> getSections() {
		return repository.findAll();
	}

	public Section getSectionById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public String deleteSection(Long id) {
		repository.deleteById(id);
		return "Section removed !! " + id;
	}

	public Section updateSection(Section section, Long id) {
		Section existingSection = repository.findById(id).orElse(null);
		existingSection.setRoomLocation(section.getRoomLocation());
		existingSection.setCapacity(section.getCapacity());
		existingSection.setCourse(section.getCourse());
		existingSection.setFaculty(section.getFaculty());

		return repository.save(existingSection);
	}

}