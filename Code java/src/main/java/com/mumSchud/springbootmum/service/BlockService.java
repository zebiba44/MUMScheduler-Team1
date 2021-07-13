package com.mumSchud.springbootmum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumSchud.springbootmum.entity.Block;
import com.mumSchud.springbootmum.repository.BlockRepository;

@Service
public class BlockService {

	@Autowired
	private BlockRepository repository;

	public List<Block> findAll() {
		return repository.findAll();
	}

	public Block getOne(int id) {
		return repository.getOne(id);
	}

	public Block addBlock(String name, String date) {
		Block block = new Block(name, date);
		return repository.save(block);
	}

	public Block updateBlock(Integer blockId, String name, String code) {
		Block block = repository.findById(blockId).orElse(null);
		block.setName(name);
		block.setDate(code);
		return repository.save(block);
	}

	public void deleteBlock(Integer courseId) {
		repository.deleteById(courseId);
	}
}
