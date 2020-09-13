package com.delectable;

import java.util.List;
import java.util.ArrayList;
import com.delectable.pantry.PantryItem;
import com.delectable.pantry.PantryService;
import com.delectable.recipe.Direction;
import com.delectable.recipe.Recipe;
import com.delectable.recipe.RecipeService;
import com.delectable.restaurant.Restaurant;
import com.delectable.restaurant.RestaurantService;
import com.delectable.schedule.ScheduleService;
import com.delectable.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class EntityUtil {

    @Autowired
    private PantryService pantryItemService;

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

    List<PantryItem> createValidPantryItems(int numberOfItemsToCreate, boolean markAsDeleted,
            boolean markAsSchedulable) {
        List<PantryItem> pantryItems = new ArrayList<PantryItem>();
        for (int i = 0; i < numberOfItemsToCreate; i++) {
            pantryItems.add(new PantryItem("Name" + i, "Brand" + i));

            if (markAsDeleted == true) {
                pantryItems.get(i).setDeleted(true);
            }

            if (markAsSchedulable == true) {
                pantryItems.get(i).setSchedulable(true);
            }
        }
        return pantryItems;
    }

    void insertValidPantryItems(int numberOfItemsToInsert, boolean markAsDeleted,
            boolean markAsSchedulable) {
        List<PantryItem> pantryItems = new ArrayList<PantryItem>();
        pantryItems.addAll(
                createValidPantryItems(numberOfItemsToInsert, markAsDeleted, markAsSchedulable));
        pantryItemService.saveAll(pantryItems);
    }

    int insertValidPantryItem(boolean markAsDeleted, boolean markAsSchedulable) {
        PantryItem pantryItem =
                (createValidPantryItems(1, markAsDeleted, markAsSchedulable).get(0));
        pantryItem = pantryItemService.save(pantryItem);
        return pantryItem.getId();
    }

    List<Restaurant> createValidRestaurants(int numberOfItemsToCreate, boolean markAsDeleted) {
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        for (int i = 0; i < numberOfItemsToCreate; i++) {
            restaurants.add(new Restaurant("Name" + i, "123 Main Street", "City", "State", "45678",
                    "123-456-7890", "http://mysite", "http://mysite/menu", false, false, ""));
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

    int insertValidRestaurant(boolean markAsDeleted) {
        Restaurant restaurant = (createValidRestaurants(1, markAsDeleted).get(0));
        restaurant = restaurantService.save(restaurant);
        return restaurant.getId();
    }

    Recipe createValidTestRecipe() {
        PantryItem pantryItem1 = new PantryItem("Carrot");
        PantryItem pantryItem2 = new PantryItem("Onion");
        PantryItem pantryItem3 = new PantryItem("Apples");
        pantryItem1 = pantryItemService.save(pantryItem1);
        pantryItem2 = pantryItemService.save(pantryItem2);
        pantryItem3 = pantryItemService.save(pantryItem3);

        List<Direction> instructions = new ArrayList<>();
        instructions.add(new Direction(1, "Step 1"));
        instructions.add(new Direction(2, "Step 2"));
        instructions.add(new Direction(3, "Step 3"));
        instructions.add(new Direction(4, "Step 4"));
        instructions.add(new Direction(5, "Step 5"));

        Recipe recipe = new Recipe("Recipe1", "Kitchen", "Description", 10, 10, 10, 10, 10, 10,
                instructions, "", false);
        recipe.addIngredientFromPantry(pantryItem1, "0", Ingredient.Unit.TEASPOON.toString());
        recipe.addIngredientFromPantry(pantryItem2, "1", Ingredient.Unit.TABLESPOON.toString());
        recipe.addIngredientFromPantry(pantryItem3, "2", Ingredient.Unit.DROP.toString());
        return recipe;
    }

    int insertValidTestRecipe() {
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
