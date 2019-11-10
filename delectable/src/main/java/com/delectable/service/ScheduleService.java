package com.delectable.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.delectable.model.Schedule;

@Repository
public interface ScheduleService extends CrudRepository<Schedule, Integer> {    
    List<Schedule> findByEpochGreaterThanEqual(Long start);
}
