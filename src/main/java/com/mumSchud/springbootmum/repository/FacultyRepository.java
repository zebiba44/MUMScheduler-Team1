package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
	
}