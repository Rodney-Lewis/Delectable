package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.delectable.schedule.Schedule.MealTypes;
import com.delectable.schedule.Schedule.ScheduleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    List<Schedule> getScheduled() { 
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules = scheduleService.findByEpochGreaterThanEqual(calendar.getTimeInMillis());
        return schedules;
    }

    @GetMapping("/{epoch}")
    List<Schedule> getScheduledByDate(@PathVariable Long epoch) { 
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(epoch);
        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules = scheduleService.findByEpoch(calendar.getTimeInMillis());
        return schedules;
    }

    @GetMapping("/mealtypes")
    String[] getMealtypes() {
        return MealTypes.toStringArray();
    }

    @GetMapping("/scheduletypes")
    String[] getScheduleTypes() {
        return ScheduleType.toStringArray();
    }

    @PostMapping
    Schedule addSchedule(@RequestBody Schedule schedule) {
        return(scheduleService.save(schedule));
    }

    @DeleteMapping("/{Id}")
    void deleteSchedule(@PathVariable int Id) {
        scheduleService.deleteById(Id);
    }
}