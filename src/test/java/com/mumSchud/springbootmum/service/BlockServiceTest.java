package com.mumSchud.springbootmum.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import com.mumSchud.springbootmum.entity.Block;
import com.mumSchud.springbootmum.repository.BlockRepository;

import org.assertj.core.api.Assertions;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BlockServiceTest {
	
	@Mock
	private BlockRepository blockRepository;
	private BlockService blockService;
	private List<Block> blockList;
	private Block block;
	private Block blockUpdate;
	private int id;
	
	@BeforeEach
	void setUp() {
		blockService = new BlockService(blockRepository);
		block = new Block("Block1","2021-07-14");
		block.setId(1);
		blockUpdate = new Block("Block1Updated","2021-07-14");
		blockUpdate.setId(1);
		blockList = new ArrayList<Block>();
		blockList.add(block);
		id = 1;
	}
	
	@Test
	public void get_list_block() {
		Mockito.when(blockRepository.findAll()).thenReturn(blockList);
		List<Block> blocks = blockService.findAll();
		Assertions.assertThat(blocks.size()).isEqualTo(1);
	}
	
	@Test
	public void get_one() {
		Mockito.when(blockRepository.getOne(id)).thenReturn(block);
		Block block = blockService.getOne(id);
		Assertions.assertThat(block.getName()).isEqualTo("Block1");
	}
	
	@Test
	public void add_block() {
		Block blockSave = new Block("Block1","2021-07-14");
		Mockito.when(blockRepository.save(blockSave)).thenReturn(block);
		Block block = blockService.addBlock("Block1","2021-07-14");
		Assertions.assertThat(block.getName()).isEqualTo("Block1");
	}
	
	@Test
	public void update_block() {
		Mockito.when(blockRepository.findById(id)).thenReturn(Optional.ofNullable(block));
		Block blockSave = new Block("Block1Updated","2021-07-14");
		blockSave.setId(id);
		Mockito.when(blockRepository.save(blockSave)).thenReturn(blockUpdate);
		Block blockUpdated = blockService.updateBlock(id, "Block1Updated","2021-07-14");
		Assertions.assertThat(blockUpdated.getName()).isEqualTo("Block1Updated");
	}
	
	@Test
	public void delete_block() {
		blockService.deleteBlock(id);
		 verify(blockRepository, times(1)).deleteById(id);
	}

}
