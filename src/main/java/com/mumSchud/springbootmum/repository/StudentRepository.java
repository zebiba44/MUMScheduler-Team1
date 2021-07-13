package com.mumSchud.springbootmum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mumSchud.springbootmum.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	Optional<Student> findByEmail(@Param("email") String email);
}