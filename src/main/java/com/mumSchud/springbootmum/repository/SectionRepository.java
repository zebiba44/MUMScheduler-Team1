package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Section;

public interface SectionRepository extends JpaRepository<Section,Long> {

}
