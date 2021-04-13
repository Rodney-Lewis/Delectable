package com.delectable.schedule;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleService {

  @Autowired
  ScheduleRepository repository;

  public Schedule create(Schedule schedule) {
    return repository.save(schedule);
  }

  public List<Schedule> createFromList(List<Schedule> schedules) {
    return (List<Schedule>) repository.saveAll(schedules);
  }

  public List<Schedule> findByDateBetween(LocalDate begin, LocalDate end) {
    return repository.findByDateBetweenOrderByDateAsc(begin, end);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
