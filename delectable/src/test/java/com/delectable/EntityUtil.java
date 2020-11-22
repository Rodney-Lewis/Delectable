package com.delectable;

import java.util.List;
import java.util.ArrayList;
import com.delectable.recipe.Direction;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
import com.delectable.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class EntityUtil {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ScheduleService scheduleService;

    byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    List<Restaurant> createValidRestaurants(int numberOfItemsToCreate, boolean markAsDeleted) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        for (int i = 0; i < numberOfItemsToCreate; i++) {
            restaurants.add(new Restaurant());
            if (markAsDeleted == true) {
                restaurants.get(i).setDeleted(true);
            }
        }
        return restaurants;
    }

    void insertValidRestaurants(int numberOfItemsToCreate, boolean markAsDeleted) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.addAll(createValidRestaurants(numberOfItemsToCreate, markAsDeleted));
        restaurantService.saveAll(restaurants);
    }

    Long insertValidRestaurant(boolean markAsDeleted) {
        Restaurant restaurant = (createValidRestaurants(1, markAsDeleted).get(0));
        restaurant = restaurantService.save(restaurant);
        return restaurant.getId();
    }

    Recipe createValidTestRecipe() {
        List<Direction> instructions = new ArrayList<>();
        instructions.add(new Direction(1, "Step 1"));
        instructions.add(new Direction(2, "Step 2"));
        instructions.add(new Direction(3, "Step 3"));
        instructions.add(new Direction(4, "Step 4"));
        instructions.add(new Direction(5, "Step 5"));

        Recipe recipe = new Recipe();
        return recipe;
    }

    Long insertValidTestRecipe() {
        Recipe recipe = createValidTestRecipe();
        recipe = recipeService.save(recipe);
        return recipe.getId();
    }

    void insertValidTestRecipes(int numberOfRecipes, boolean markAsDeleted) {
        Recipe recipe;
        if (numberOfRecipes > 0) {
            for (int i = 0; i < numberOfRecipes; i++) {
                recipe = createValidTestRecipe();

                if (markAsDeleted == true) {
                    recipe.setDeleted(true);
                }
                recipeService.save(recipe);
            }
        }
    }
}
