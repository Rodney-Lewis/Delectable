package com.delectable.schedule;

import com.delectable.shared.crud.CRUHardDeleteRepository;
import com.delectable.shared.crud.CRUHardDeleteService;

public class ScheduleService extends CRUHardDeleteService<Schedule> {
    public ScheduleService(CRUHardDeleteRepository<Schedule> repository) {
        super(repository);
    }
}
