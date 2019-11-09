package com.delectable.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.delectable.model.Schedule;
import com.delectable.model.Schedule.MealTypes;
import com.delectable.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    Schedule addSchedule(@RequestBody Schedule schedule) {
        return(scheduleService.save(schedule));
    }

    @DeleteMapping("/delete")
    void deleteSchedule(@RequestBody Schedule schedule) {
        scheduleService.delete(schedule);
    }

    @GetMapping("/get")
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

    @GetMapping("/get/mealtypes")
    MealTypes[] getMealtypes() {
        MealTypes[] mealTypes = MealTypes.values();
        return mealTypes;
    }
}