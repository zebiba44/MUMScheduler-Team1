package com.mumSchud.springbootmum.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import com.mumSchud.springbootmum.entity.Schedule;
import com.mumSchud.springbootmum.entity.ScheduleStatus;
import com.mumSchud.springbootmum.repository.ScheduleRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ScheduleServiceTest {
	
	@Mock
	private ScheduleRepository scheduleRepository;
	private ScheduleService scheduleService;
	private List<Schedule> scheduleList;
	private Schedule schedule;
	private Schedule scheduleUpdate;
	private long id;
	
	@BeforeEach
	void setUp() {
		scheduleService = new ScheduleService(scheduleRepository);
		schedule = new Schedule(ScheduleStatus.Pending);
		schedule.setId(1L);
		scheduleUpdate = new Schedule(ScheduleStatus.Approved);
		scheduleUpdate.setId(1L);
		scheduleList = new ArrayList<Schedule>();
		scheduleList.add(schedule);
		id = 1;
	}
	
	@Test
	public void get_list_schedule() {
		Mockito.when(scheduleRepository.findAll()).thenReturn(scheduleList);
		List<Schedule> schedules = scheduleService.getSchedule();
		Assertions.assertThat(schedules.size()).isEqualTo(1);
	}
	
	@Test
	public void get_schedule() {
		Mockito.when(scheduleRepository.findById(id)).thenReturn(Optional.ofNullable(schedule));
		Schedule schedule = scheduleService.getScheduleById(id);
		Assertions.assertThat(schedule.getStatus().Pending).isEqualTo(ScheduleStatus.Pending);
	}
	
	@Test
	public void add_Schedule() {
		Schedule scheduleSave = new Schedule(ScheduleStatus.Pending);
		Mockito.when(scheduleRepository.save(scheduleSave)).thenReturn(schedule);
		Schedule schedule = scheduleService.saveSchedule(scheduleSave);
		Assertions.assertThat(schedule.getStatus().Pending).isEqualTo(ScheduleStatus.Pending);
	}
	
	@Test
	public void update_schedule() {
		Mockito.when(scheduleRepository.findById(id)).thenReturn(Optional.ofNullable(schedule));
		Mockito.when(scheduleRepository.save(schedule)).thenReturn(scheduleUpdate);
		Schedule scheduleUpdated = scheduleService.updateSchedule(schedule, 1L);
		Assertions.assertThat(scheduleUpdated.getStatus().Approved).isEqualTo(ScheduleStatus.Approved);
	}
	
	@Test
	public void delete_shcedule() {
		scheduleService.deleteSchedule(id);
		 verify(scheduleRepository, times(1)).deleteById(id);
	}

}
