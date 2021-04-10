package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.delectable.combo.Combo;
import com.delectable.combo.ComboUtil;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeUtil;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleUtil {

  @Autowired
  private ScheduleService service;

  @Autowired
  private RecipeUtil recipeUtil;

  @Autowired
  private RestaurantUtil restaurantUtil;

  @Autowired
  private ComboUtil comboUtil;

  public List<Schedule> insertSchedules(int numberToInsert, boolean deleted) {
    List<Schedule> schedules = new ArrayList<>();
    for (int i = 0; i < numberToInsert; i++) {
      schedules.add(createScheduleForRecipe(deleted));
    }
    return (List<Schedule>) service.createFromList(schedules);
  }

  public Schedule createScheduleForRecipe(boolean deleted) {
    Recipe recipe = recipeUtil.insertValidTestRecipe(deleted);
    Schedule recipeSchedule = new Schedule(new Date().getTime(), MealTime.BREAKFAST,
        ScheduleType.RECIPE, recipe.getId(), recipe.getName());
    return recipeSchedule;
  }

  public Schedule createScheduleForRestaurant(boolean deleted) {
    Restaurant restaurant = restaurantUtil.insertValidRestaurant(deleted);
    Schedule restaurantSchedule = new Schedule(new Date().getTime(), MealTime.BREAKFAST,
        ScheduleType.RESTAURANT, restaurant.getId(), restaurant.getName());
    return restaurantSchedule;

  }

  public Schedule createScheduleForCombo(boolean deleted) {
    Combo combo = comboUtil.insertValidCombo(2, deleted);
    Schedule mealGroupSchedule = new Schedule(new Date().getTime(), MealTime.BREAKFAST,
        ScheduleType.COMBO, combo.getId(), combo.getName());
    return mealGroupSchedule;
  }
}
