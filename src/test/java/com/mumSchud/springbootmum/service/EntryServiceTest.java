package com.mumSchud.springbootmum.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
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

import com.mumSchud.springbootmum.entity.Entry;
import com.mumSchud.springbootmum.entity.Faculty;
import com.mumSchud.springbootmum.repository.EntryRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EntryServiceTest {
	
	@Mock
	private EntryRepository entryRepository;
	private EntryService entryService;
	private List<Entry> entryList;
	private Entry entry;
	private Entry entryUpdate;
	private int id;
	
	@BeforeEach
	void setUp() {
		entryService = new EntryService(entryRepository);
		Faculty faculty = new Faculty("test","test","test");
		faculty.setId(1);
		entry = new Entry("entry1",LocalDate.of(2021, 7, 14),1,1,1,1,1,1,faculty);
		entry.setId(1);
		entryUpdate = new Entry("entry1updated",LocalDate.of(2021, 7, 14),1,1,1,1,1,1,faculty);
		entryUpdate.setId(1);
		entryList = new ArrayList<Entry>();
		entryList.add(entry);
		id = 1;
	}
	
	@Test
	public void get_list_entry() {
		Mockito.when(entryRepository.findAll()).thenReturn(entryList);
		List<Entry> entries = entryService.getEntries();
		Assertions.assertThat(entries.size()).isEqualTo(1);
	}
	
	@Test
	public void get_entry() {
		Mockito.when(entryRepository.findById(id)).thenReturn(Optional.ofNullable(entry));
		Entry entry = entryService.getEntryById(id);
		Assertions.assertThat(entry.getName()).isEqualTo("entry1");
	}
	
	@Test
	public void add_entry() {
		Faculty faculty = new Faculty("test","test","test");
		faculty.setId(1);
		Entry entrySave = new Entry("entry1",LocalDate.of(2021, 7, 14),1,1,1,1,1,1,faculty);
		Mockito.when(entryRepository.save(entrySave)).thenReturn(entry);
		Entry entry = entryService.saveEntry(entrySave);
		Assertions.assertThat(entry.getName()).isEqualTo("entry1");
	}
	
	@Test
	public void update_entry() {
		Mockito.when(entryRepository.findById(id)).thenReturn(Optional.ofNullable(entry));
		Faculty faculty = new Faculty("test","test","test");
		faculty.setId(1);
		Entry entrySave = new Entry("entry1updated",LocalDate.of(2021, 7, 14),1,1,1,1,1,1,faculty);
		entrySave.setId(id);
		Mockito.when(entryRepository.save(entrySave)).thenReturn(entryUpdate);
		Entry entryUpdated = entryService.updateEntry(entrySave,1);
		Assertions.assertThat(entryUpdated.getName()).isEqualTo("entry1updated");
	}
	
	@Test
	public void delete_entry() {
		entryService.deleteEntry(id);
		 verify(entryRepository, times(1)).deleteById(id);
	}

}
