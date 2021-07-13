package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Integer>{

}
