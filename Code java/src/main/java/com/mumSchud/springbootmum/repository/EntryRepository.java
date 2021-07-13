package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry,Integer> {
	
}