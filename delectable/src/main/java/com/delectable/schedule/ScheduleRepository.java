package com.delectable.schedule;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.delectable.shared.crud.CRUHardDeleteRepository;

@Repository
public interface ScheduleRepository extends CRUHardDeleteRepository<Schedule> {    
    List<Schedule> findByEpochBetweenOrderByEpochAsc(Long start, Long end);
}
