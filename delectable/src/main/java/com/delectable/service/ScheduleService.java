package com.delectable.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delectable.model.Schedule;

@Repository
public interface ScheduleService extends CrudRepository<Schedule, Integer> {}
