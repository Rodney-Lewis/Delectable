package com.delectable.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.delectable.mealgroup.MealGroup;
import com.delectable.mealgroup.MealGroupUtil;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeUtil;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleUtil {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RecipeUtil recipeUtil;

    @Autowired
    private RestaurantUtil restaurantUtil;

    @Autowired
    private MealGroupUtil mealGroupUtil;

    public List<Schedule> insertSchedules(int numberToInsert, boolean deleted) {
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < numberToInsert; i++) {
            schedules.add(createScheduleForRecipe(deleted));
        }
        return (List<Schedule>) scheduleService.saveAll(schedules);
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

    public Schedule createScheduleForMealGroup(boolean deleted) {
        MealGroup mealGroup = mealGroupUtil.insertValidMealGroup(2, 2, deleted);
        Schedule mealGroupSchedule = new Schedule(new Date().getTime(), MealTime.BREAKFAST,
                ScheduleType.MEAL, mealGroup.getId(), mealGroup.getName());
        return mealGroupSchedule;
    }
}
