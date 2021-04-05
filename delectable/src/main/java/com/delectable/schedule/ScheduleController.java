package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delectable.combo.ComboRepository;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeRepository;
import com.delectable.restaurant.RestaurantRepository;
import com.delectable.shared.crud.CRUHardDeleteController;
import com.delectable.shared.crud.CRUHardDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/schedule")
@CrossOrigin
public class ScheduleController extends CRUHardDeleteController<Schedule> {

    public ScheduleController(CRUHardDeleteRepository<Schedule> repository) {
        super(repository);
    }

    @Autowired
    ScheduleRepository scheduleService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ComboRepository comboRepository;

    @GetMapping
    ResponseEntity<Map<String, Object>> getScheduledBetween(@RequestParam String begin,
            @RequestParam String end) {

        List<Schedule> scheduled = new ArrayList<Schedule>();
        scheduled.addAll(scheduleService.findByEpochBetweenOrderByEpochAsc(Long.parseLong(begin),
                Long.parseLong(end)));
        Map<String, Object> response = new HashMap<>();
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
                    Schedule t = scheduled.get(j);

                    if (t.getScheduleType() == ScheduleType.RECIPE) {
                        t.setScheduledItemName(
                                recipeRepository.findById(t.getScheduledItemId()).get().getName());
                    } else if (t.getScheduleType() == ScheduleType.RESTAURANT) {
                        t.setScheduledItemName(restaurantRepository.findById(t.getScheduledItemId())
                                .get().getName());
                    }
                    scheduledByDate.get(k).add(scheduled.get(j));
                }
            }
        }
        response.put("content", scheduledByDate);
        response.put("totalElements", scheduled.size());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mealtypes")
    MealTime[] getMealtypes() {
        return MealTime.values();
    }

    @GetMapping("/scheduletypes")
    ScheduleType[] getScheduleTypes() {
        return ScheduleType.values();
    }
}
