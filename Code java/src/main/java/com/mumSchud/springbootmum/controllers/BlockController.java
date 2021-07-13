package com.mumSchud.springbootmum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumSchud.springbootmum.service.BlockService;

@Controller
@RequestMapping("/blocks")
public class BlockController {

	@Autowired
	BlockService service;

	@GetMapping
	public String listBlocks(Model model) {
		model.addAttribute("blocks", service.findAll());
		return "blocks";
	}

	@PostMapping
	public String createBlock(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "date") String date) {
		service.addBlock(name, date);
		return listBlocks(model);
	}

	@PostMapping("{block}/update")
	public String updateBlock(Model model, @PathVariable("block") int blockId, @RequestParam(name = "name") String name,
			@RequestParam(name = "date") String date) {
		service.updateBlock(blockId, name, date);
		return listBlocks(model);
	}

	@GetMapping("{block}/delete")
	public String deleteBlock(Model model, @PathVariable("block") int blockId) {
		service.deleteBlock(blockId);
		return listBlocks(model);
	}
}
