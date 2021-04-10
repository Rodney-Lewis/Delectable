package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.delectable.combo.Combo;
import com.delectable.combo.ComboService;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
import com.delectable.shared.crud.CRUSoftDeleteRepository;
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
  ScheduleService scheduleService;

  RecipeService recipeService;
  RestaurantService restaurantService;
  ComboService comboService;

  public ScheduleController(CRUSoftDeleteRepository<Recipe> recipeRepository,
      CRUSoftDeleteRepository<Restaurant> restaurantRepository,
      CRUSoftDeleteRepository<Combo> comboRepository) {
    recipeService = new RecipeService(recipeRepository);
    restaurantService = new RestaurantService(restaurantRepository);
    comboService = new ComboService(comboRepository);
  }

  @PostMapping
  ResponseEntity<?> addSchedule(@RequestBody Schedule schedule) {
    return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(schedule));
  }

  @GetMapping
  ResponseEntity<List<List<Schedule>>> getScheduledBetween(@RequestParam String begin,
      @RequestParam String end) {

    List<Schedule> scheduled = new ArrayList<Schedule>();
    scheduled.addAll(scheduleService.findByEpochBetweenOrderByEpochAsc(Long.parseLong(begin),
        Long.parseLong(end)));
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
            t.setScheduledItemName(recipeService.get(t.getScheduledItemId()).getName());
          } else if (t.getScheduleType() == ScheduleType.RESTAURANT) {
            t.setScheduledItemName(restaurantService.get(t.getScheduledItemId()).getName());
          } else if (t.getScheduleType() == ScheduleType.COMBO) {
            t.setScheduledItemName(comboService.get(t.getScheduledItemId()).getName());
          }
          scheduledByDate.get(k).add(scheduled.get(j));
        }
      }
    }
    return ResponseEntity.ok().body(scheduledByDate);
  }

  @GetMapping("/mealtypes")
  MealTime[] getMealtypes() {
    return MealTime.values();
  }

  @GetMapping("/scheduletypes")
  ScheduleType[] getScheduleTypes() {
    return ScheduleType.values();
  }

  @DeleteMapping("/{Id}")
  void deleteSchedule(@PathVariable Long Id) {
    scheduleService.delete(Id);
  }
}
