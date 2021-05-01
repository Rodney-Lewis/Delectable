package com.delectable.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.delectable.combo.Combo;
import com.delectable.combo.ComboService;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
import com.delectable.shared.crud.CRUSoftDeleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
  ResponseEntity<?> addSchedule(@RequestBody List<Schedule> schedule) {
    return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createFromList(schedule));
  }

  @GetMapping("/schedulable")
  ResponseEntity<Map<String, Object>> getSchedulable(Pageable pageable, @RequestParam String name) {
    List<Schedulable> schedulable = new ArrayList<>();
    Map<String, Object> response = new HashMap<>();

    List<Recipe> recipes = recipeService.getPage(pageable, name).toList();
    if (recipes.size() >= pageable.getPageSize()) {
      recipes = recipes.subList(0, pageable.getPageSize());
    }

    for (Recipe recipe : recipes) {
      schedulable.add(new Schedulable(recipe.getId(), recipe.getName(), ScheduleType.RECIPE));
    }

    List<Restaurant> restaurants = restaurantService.getPage(pageable, name).toList();
    if (restaurants.size() >= pageable.getPageSize()) {
      restaurants = restaurants.subList(0, restaurants.size());
    }

    for (Restaurant restaurant : restaurants) {
      schedulable
          .add(new Schedulable(restaurant.getId(), restaurant.getName(), ScheduleType.RESTAURANT));
    }

    List<Combo> combos = comboService.getPage(pageable, name).toList();
    if (combos.size() >= pageable.getPageSize()) {
      combos = combos.subList(0, pageable.getPageSize());
    }

    for (Combo combo : combos) {
      schedulable.add(new Schedulable(combo.getId(), combo.getName(), ScheduleType.COMBO));
    }

    Collections.sort(schedulable, (a, b) -> a.name.compareTo(b.name));
    if (combos.size() + restaurants.size() + recipes.size() >= pageable.getPageSize()) {
      schedulable = schedulable.subList(0, pageable.getPageSize());
    }
    response.put("content", schedulable);

    return ResponseEntity.ok().body(response);
  }

  @GetMapping
  ResponseEntity<Map<String, Object>> getScheduledBetween(@RequestParam String begin,
      @RequestParam String end) {
    Map<String, Object> response = new HashMap<>();

    LocalDate beginDate = LocalDate.parse(begin);
    LocalDate endDate = LocalDate.parse(end);
    endDate = endDate.plusDays(1L);
    List<Schedule> scheduledBetweenDates =
        new ArrayList<Schedule>(scheduleService.findByDateBetween(beginDate, endDate));
    List<LocalDate> datesBetweenDates = beginDate.datesUntil(endDate).collect(Collectors.toList());

    List<List<Schedule>> scheduledByDate = new ArrayList<List<Schedule>>();
    Schedule temp;

    for (int k = 0; k < datesBetweenDates.size(); k++) {
      scheduledByDate.add(new ArrayList<Schedule>());
      for (int j = 0; j < scheduledBetweenDates.size(); j++) {
        temp = scheduledBetweenDates.get(j);
        if (datesBetweenDates.get(k).isEqual(temp.date)) {
          switch (temp.getScheduleType()) {
            case RECIPE:
              if (!recipeService.isDeleted(temp.getScheduledItemId())) {
                temp.setScheduledItemName(recipeService.get(temp.getScheduledItemId()).getName());
                scheduledByDate.get(k).add(temp);
              }
              break;
            case RESTAURANT:
              if (!restaurantService.isDeleted(temp.getScheduledItemId())) {
                temp.setScheduledItemName(
                    restaurantService.get(temp.getScheduledItemId()).getName());
                scheduledByDate.get(k).add(temp);
              }
              break;
            case COMBO:
              if (!comboService.isDeleted(temp.getScheduledItemId())) {
                temp.setScheduledItemName(comboService.get(temp.getScheduledItemId()).getName());
                scheduledByDate.get(k).add(temp);
              }
              break;
            default:
              break;
          }
        }
      }
    }

    response.put("content", scheduledByDate);
    return ResponseEntity.ok().body(response);
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
