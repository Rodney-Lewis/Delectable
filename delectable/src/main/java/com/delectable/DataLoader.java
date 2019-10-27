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

        Recipe recipe1 = recipeService
        .save(new Recipe("Easy crock pot teriyaki chicken","00:10:00", "06:00:00", "Food.com", new ArrayList<>(), new ArrayList<>() ));

        List<RecipeStep> recipeSteps1 = new ArrayList<>();
        recipeSteps1.add(new RecipeStep(1, "Brown ground beef, breaking meat into small pieces, cooking until pink disappears from meat.", recipe1));
        recipeSteps1.add(new RecipeStep(2, "Pour into colander and drain.", recipe1));
        recipeSteps1.add(new RecipeStep(3, "Place cooked meat and all other ingredients into crock pot and stir thoroughly.", recipe1));
        recipeSteps1.add(new RecipeStep(4, "Cook on High for 4 hours or Low for 6-8 hours.", recipe1));
        recipeSteps1.add(new RecipeStep(5, "***If you can still see individual grains of rice, keep cooking it until they disappear.", recipe1));
        recipeSteps1.add(new RecipeStep(6, "Sprinkle cheese and spring onions over each serving, if desired.", recipe1));

        List<Ingredient> items1 = new ArrayList<>();
        items1.add(new Ingredient(pantryItems.get(0), recipe1, "1", "Tablespoon"));
        items1.add(new Ingredient(pantryItems.get(1), recipe1, "3/4", "Tablespoon"));
        items1.add(new Ingredient(pantryItems.get(2), recipe1, "5/8", "Tablespoon"));

        recipe1.setDirections(recipeSteps1);
        recipe1.setIngredients(items1);
        recipe1 = recipeService.save(recipe1);

        Recipe recipe2 = recipeService
        .save(new Recipe("Crock pot texas hash", "00:10:00", "04:00:00", "Food.com", new ArrayList<>(), new ArrayList<>()));

        List<RecipeStep> recipeSteps2 = new ArrayList<>();
        recipeSteps2.add(new RecipeStep(1, "Brown ground beef, breaking meat into small pieces, cooking until pink disappears from meat.", recipe2));
        recipeSteps2.add(new RecipeStep(2, "Pour into colander and drain.", recipe2));
        recipeSteps2.add(new RecipeStep(3, "Place cooked meat and all other ingredients into crock pot and stir thoroughly.", recipe2));
        recipeSteps2.add(new RecipeStep(4, "Cook on High for 4 hours or Low for 6-8 hours.", recipe2));
        recipeSteps2.add(new RecipeStep(5, "***If you can still see individual grains of rice, keep cooking it until they disappear.", recipe2));
        recipeSteps2.add(new RecipeStep(6, "Sprinkle cheese and spring onions over each serving, if desired.", recipe2));

        List<Ingredient> items2 = new ArrayList<>();
        items2.add(new Ingredient(pantryItems.get(0), recipe2, "1", "Tablespoon"));
        items2.add(new Ingredient(pantryItems.get(1), recipe2, "3/4", "Tablespoon"));
        items2.add(new Ingredient(pantryItems.get(2), recipe2, "5/8", "Tablespoon"));

        recipe2.setDirections(recipeSteps2);
        recipe2.setIngredients(items2);
        recipe2 = recipeService.save(recipe2);

        Schedule schedule1 = new Schedule(0, recipe1);
        Schedule schedule2 = new Schedule(10, recipe2);

        scheduleService.save(schedule1);
        scheduleService.save(schedule2);
        
    }
}