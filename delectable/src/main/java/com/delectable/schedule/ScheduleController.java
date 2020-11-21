package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
import com.delectable.schedule.Schedule.MealTypes;
import com.delectable.schedule.Schedule.ScheduleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RecipeService recipeService;

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

    @GetMapping("/epochBetween")
    public ResponseEntity<Map<String, Object>> getScheduledBetween(@RequestParam String begin,
            @RequestParam String end) {

        List<Schedule> scheduled = new ArrayList<Schedule>();
        scheduled.addAll(scheduleService.findByEpochBetweenOrderByEpochAsc(Long.parseLong(begin),
                Long.parseLong(end)));
        Map<String, Object> response = new HashMap<>();

        for (Schedule schedule : scheduled) {
            if (schedule.scheduleType.equals(ScheduleType.RECIPE.toString())) {
                schedule.setScheduledItemName(
                        recipeService.findById(schedule.scheduledItemId).get().getName());
            } else if (schedule.scheduleType.equals(ScheduleType.RESTAURANT.toString())) {
                Restaurant res = restaurantService.findById(schedule.scheduledItemId).get();
                schedule.setScheduledItemName(res.getName());
            }
        }

        List<List<Schedule>> scheduledByDate = new ArrayList<List<Schedule>>();

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date1.setTimeInMillis(Long.parseLong(begin));
        date2.setTimeInMillis(Long.parseLong(end));

        var timeBetweenDates = Math.abs(date1.getTimeInMillis() - date2.getTimeInMillis());
        var daysBetweenDates = Math.floor(timeBetweenDates / (24 * 3600 * 1000));
        List<Date> dates = new ArrayList<Date>();

        for (int i = 0; i < daysBetweenDates + 1; i++) {
            dates.add(date1.getTime());
            date1.add(Calendar.DATE, 1);
        }

        for (int k = 0; k < dates.size(); k++) {
            scheduledByDate.add(new ArrayList<Schedule>());
            for (int j = 0; j < scheduled.size(); j++) {
                if (dates.get(k).getTime() == scheduled.get(j).epoch) {
                    scheduledByDate.get(k).add(scheduled.get(j));
                }
            }
        }
        response.put("content", scheduledByDate);
        response.put("totalElements", scheduled.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{epoch}")
    List<Schedule> getScheduledByDate(@PathVariable Long epoch) {
        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules = scheduleService.findByEpoch(epoch);
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
        return (scheduleService.save(schedule));
    }

    @DeleteMapping("/{Id}")
    void deleteSchedule(@PathVariable int Id) {
        scheduleService.deleteById(Id);
    }
}
