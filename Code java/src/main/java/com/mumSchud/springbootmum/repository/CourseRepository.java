package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
