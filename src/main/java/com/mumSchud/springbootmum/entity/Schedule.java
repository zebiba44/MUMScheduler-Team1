package com.mumSchud.springbootmum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Schedules")
public class Schedule {

	@Id
    @GeneratedValue
    private Long id ;
    private ScheduleStatus status ;
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
}
