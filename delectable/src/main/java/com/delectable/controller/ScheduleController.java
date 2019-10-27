package com.delectable.controller;

import java.util.List;

import com.delectable.model.Schedule;
import com.delectable.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @GetMapping("/get")
    List<Schedule> getScheduleByTimeframe(@RequestBody long start, @RequestBody long end) {
        return(List<Schedule>) scheduleService.findAll();
    }

    
}