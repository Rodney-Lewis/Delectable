package com.delectable.schedule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleService extends CrudRepository<Schedule, Integer> {    
    List<Schedule> findByEpochGreaterThanEqual(Long start);
    List<Schedule> findByEpoch(Long start);
    List<Schedule> findByEpochBetween(Long start, Long end);
    void deleteById(int Id);
}
