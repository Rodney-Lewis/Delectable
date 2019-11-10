package com.delectable;

import java.util.ArrayList;
import java.util.List;

import com.delectable.model.Ingredient;
import com.delectable.model.Pantry;
import com.delectable.model.Recipe;
import com.delectable.model.RecipeStep;
import com.delectable.model.Schedule;
import com.delectable.service.PantryService;
import com.delectable.service.RecipeService;
import com.delectable.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private PantryService pantryService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Pantry> pantryItems = new ArrayList<Pantry>();
        pantryItems.add(new Pantry("Chicken breast"));
        pantryItems.add(new Pantry("Soy sauce"));
        pantryItems.add(new Pantry("Brown sugar"));
        pantryItems.add(new Pantry("Ginger"));
        pantryItems.add(new Pantry("Garlic"));
        pantryItems.add(new Pantry("Water"));
        pantryItems.add(new Pantry("80/20 Ground beef"));
        pantryItems.add(new Pantry("White onions"));
        pantryItems.add(new Pantry("Green bell pepper"));
        pantryItems.add(new Pantry("Diced tomatoes"));
        pantryItems.add(new Pantry("Worcestershire sauce"));
        pantryItems.add(new Pantry("Salt"));
        pantryItems.add(new Pantry("Chili powder"));
        pantryItems.add(new Pantry("Sharp cheddar cheese"));
        pantryItems.add(new Pantry("White rice"));

        for (Pantry item : pantryItems) {
            pantryService.save(item);
        }

        List<RecipeStep> recipeSteps1 = new ArrayList<>();
        recipeSteps1.add(new RecipeStep(1, "Brown ground beef, breaking meat into small pieces, cooking until pink disappears from meat."));
        recipeSteps1.add(new RecipeStep(2, "Pour into colander and drain."));
        recipeSteps1.add(new RecipeStep(3, "Place cooked meat and all other ingredients into crock pot and stir thoroughly."));
        recipeSteps1.add(new RecipeStep(4, "Cook on High for 4 hours or Low for 6-8 hours."));
        recipeSteps1.add(new RecipeStep(5, "***If you can still see individual grains of rice, keep cooking it until they disappear."));
        recipeSteps1.add(new RecipeStep(6, "Sprinkle cheese and spring onions over each serving, if desired."));

        List<Ingredient> items1 = new ArrayList<>();
        items1.add(new Ingredient(pantryItems.get(0), "1", "Tablespoon"));
        items1.add(new Ingredient(pantryItems.get(1),  "3/4", "Tablespoon"));
        items1.add(new Ingredient(pantryItems.get(2),  "5/8", "Tablespoon"));

        Recipe recipe1 = recipeService
        .save(new Recipe("Easy crock pot teriyaki chicken","00:10:00", "06:00:00", "Food.com", recipeSteps1, items1));

        List<RecipeStep> recipeSteps2 = new ArrayList<RecipeStep>();
        recipeSteps2.add(new RecipeStep(1, "Brown ground beef, breaking meat into small pieces, cooking until pink disappears from meat."));
        recipeSteps2.add(new RecipeStep(2, "Pour into colander and drain."));
        recipeSteps2.add(new RecipeStep(3, "Place cooked meat and all other ingredients into crock pot and stir thoroughly."));
        recipeSteps2.add(new RecipeStep(4, "Cook on High for 4 hours or Low for 6-8 hours."));
        recipeSteps2.add(new RecipeStep(5, "***If you can still see individual grains of rice, keep cooking it until they disappear."));
        recipeSteps2.add(new RecipeStep(6, "Sprinkle cheese and spring onions over each serving, if desired."));

        List<Ingredient> items2 = new ArrayList<Ingredient>();
        items2.add(new Ingredient(pantryItems.get(0),  "1", "Tablespoon"));
        items2.add(new Ingredient(pantryItems.get(1),  "3/4", "Tablespoon"));
        items2.add(new Ingredient(pantryItems.get(2), "5/8", "Tablespoon"));

        Recipe recipe2 = recipeService
        .save(new Recipe("Crock pot texas hash", "00:10:00", "04:00:00", "Food.com", recipeSteps2, items2));

        Schedule schedule1 = new Schedule(System.currentTimeMillis() + (86400000 * 1), Schedule.MealTypes.BREAKFAST.toString(), recipe1);
        Schedule schedule2 = new Schedule(System.currentTimeMillis() + (86400000 * 1), Schedule.MealTypes.LUNCH.toString(), recipe1);
        Schedule schedule3 = new Schedule(System.currentTimeMillis() + (86400000 * 1), Schedule.MealTypes.DINNER.toString(), recipe1);
        Schedule schedule4 = new Schedule(System.currentTimeMillis() + (86400000 * 2), Schedule.MealTypes.BREAKFAST.toString(), recipe2);
        Schedule schedule5 = new Schedule(System.currentTimeMillis() + (86400000 * 2), Schedule.MealTypes.LUNCH.toString(), recipe1);
        Schedule schedule6 = new Schedule(System.currentTimeMillis() + (86400000 * 2), Schedule.MealTypes.DINNER.toString(), recipe2);
        Schedule schedule7 = new Schedule(System.currentTimeMillis() + (86400000 * 4), Schedule.MealTypes.DINNER.toString(), recipe1);
        Schedule schedule8 = new Schedule(System.currentTimeMillis() + (86400000 * 5), Schedule.MealTypes.DINNER.toString(), recipe2);
        Schedule schedule9 = new Schedule(System.currentTimeMillis() + (86400000 * 6), Schedule.MealTypes.BREAKFAST.toString(), recipe1);
        Schedule schedule10 = new Schedule(System.currentTimeMillis() + (86400000 * 6), Schedule.MealTypes.DINNER.toString(), recipe2);

        scheduleService.save(schedule1);
        scheduleService.save(schedule2);
        scheduleService.save(schedule3);
        scheduleService.save(schedule4);
        scheduleService.save(schedule5);
        scheduleService.save(schedule6);
        scheduleService.save(schedule7);
        scheduleService.save(schedule8);
        scheduleService.save(schedule9);
        scheduleService.save(schedule10);
    }
}