package com.delectable.schedule;

import java.util.List;
import com.delectable.shared.crud.CRUHardDeleteRepository;
import com.delectable.shared.crud.CRUHardDeleteService;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleService extends CRUHardDeleteService<Schedule> {

  @Autowired
  ScheduleRepository scheduleRepository;

  public ScheduleService(CRUHardDeleteRepository<Schedule> repository) {
    super(repository);
  }

  public List<Schedule> findByEpochBetweenOrderByEpochAsc(Long start, Long end) {
      return scheduleRepository.findByEpochBetweenOrderByEpochAsc(start, end);
    }
}
