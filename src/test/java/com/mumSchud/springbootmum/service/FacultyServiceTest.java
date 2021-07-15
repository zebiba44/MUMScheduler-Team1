package com.mumSchud.springbootmum.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.mumSchud.springbootmum.entity.Faculty;
import com.mumSchud.springbootmum.repository.FacultyRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FacultyServiceTest {
	
	@Mock
	private FacultyRepository facultyRepository;
	private facultyService facultyService;
	private List<Faculty> facultyList;
	private Faculty faculty;
	private Faculty facultyUpdate;
	private int id;
	
	@BeforeEach
	void setUp() {
		facultyService = new facultyService(facultyRepository);
		faculty = new Faculty("faculty1","test","test");
		faculty.setId(1);
		facultyUpdate = new Faculty("faculty1updated","test","test");
		facultyUpdate.setId(1);
		facultyList = new ArrayList<Faculty>();
		facultyList.add(faculty);
		id = 1;
	}
	
	@Test
	public void get_list_faculty() {
		Mockito.when(facultyRepository.findAll()).thenReturn(facultyList);
		List<Faculty> faculties = facultyService.getFaculties();
		Assertions.assertThat(faculties.size()).isEqualTo(1);
	}
	
	@Test
	public void get_faculty() {
		Mockito.when(facultyRepository.findById(id)).thenReturn(Optional.ofNullable(faculty));
		Faculty faculty = facultyService.getFacultyById(id);
		Assertions.assertThat(faculty.getName()).isEqualTo("faculty1");
	}
	
	@Test
	public void add_faculty() {
		Faculty facultySave = new Faculty("faculty1","test","test");
		Mockito.when(facultyRepository.save(facultySave)).thenReturn(faculty);
		Faculty faculty = facultyService.saveFaculty(facultySave);
		Assertions.assertThat(faculty.getName()).isEqualTo("faculty1");
	}
	
	@Test
	public void update_faculty() {
		Mockito.when(facultyRepository.findById(id)).thenReturn(Optional.ofNullable(faculty));
		Faculty facultySave = new Faculty("faculty1updated","test","test");
		facultySave.setId(id);
		Mockito.when(facultyRepository.save(facultySave)).thenReturn(facultyUpdate);
		Faculty facultyUpdated = facultyService.updateFaculty(facultySave, 1);
		Assertions.assertThat(facultyUpdated.getName()).isEqualTo("faculty1updated");
	}
	
	@Test
	public void delete_faculty() {
		facultyService.deleteFaculty(id);
		 verify(facultyRepository, times(1)).deleteById(id);
	}

}
