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

import com.mumSchud.springbootmum.entity.Course;
import com.mumSchud.springbootmum.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CourseServiceTest {
	
	@Mock
	private CourseRepository courseRepository;
	private CourseService courseService;
	private List<Course> courseList;
	private Course course;
	private Course courseUpdate;
	private int id;
	
	@BeforeEach
	void setUp() {
		courseService = new CourseService(courseRepository);
		course = new Course("course1","1");
		course.setId(1);
		courseUpdate = new Course("course1Updated","1");
		courseUpdate.setId(1);
		courseList = new ArrayList<Course>();
		courseList.add(course);
		id = 1;
	}
	
	@Test
	public void get_list_course() {
		Mockito.when(courseRepository.findAll()).thenReturn(courseList);
		List<Course> courses = courseService.findAll();
		Assertions.assertThat(courses.size()).isEqualTo(1);
	}
	
	@Test
	public void get_one() {
		Mockito.when(courseRepository.getOne(id)).thenReturn(course);
		Course course = courseService.getOne(id);
		Assertions.assertThat(course.getName()).isEqualTo("course1");
	}
	
	@Test
	public void add_course() {
		Course courseSave = new Course("course1","1");
		Mockito.when(courseRepository.save(courseSave)).thenReturn(course);
		Course course = courseService.addCourse("course1","1");
		Assertions.assertThat(course.getName()).isEqualTo("course1");
	}
	
	@Test
	public void update_course() {
		Mockito.when(courseRepository.findById(id)).thenReturn(Optional.ofNullable(course));
		Course courseSave = new Course("course1Updated","1");
		courseSave.setId(id);
		Mockito.when(courseRepository.save(courseSave)).thenReturn(courseUpdate);
		Course courseUpdated = courseService.updateCourse(id, "course1Updated","1");
		Assertions.assertThat(courseUpdated.getName()).isEqualTo("course1Updated");
	}
	
	@Test
	public void delete_course() {
		courseService.deleteCourse(id);
		 verify(courseRepository, times(1)).deleteById(id);
	}

}
