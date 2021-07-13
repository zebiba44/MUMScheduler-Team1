package com.mumSchud.springbootmum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumSchud.springbootmum.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
