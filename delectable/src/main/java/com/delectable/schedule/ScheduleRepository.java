package com.delectable.schedule;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
  List<Schedule> findByEpochBetweenOrderByEpochAsc(Long start, Long end);
}
