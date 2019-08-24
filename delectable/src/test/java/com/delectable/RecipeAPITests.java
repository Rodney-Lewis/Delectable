package com.delectable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.delectable.model.Recipe;
import com.delectable.service.RecipeService;

import com.delectable.model.Pantry;
import com.delectable.service.PantryService;

import com.delectable.model.RecipeStep;
import com.delectable.model.Ingredient;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan
public class RecipeAPITests {
   
    @Autowired
    RecipeService recipeService;

    @Autowired
    PantryService pantryService;

    @Test
    public void createRecipeTest() {

        Pantry pantryItem1 = new Pantry("Eggs");
        pantryItem1 = pantryService.save(pantryItem1);

        Pantry pantryItem2 = new Pantry("Bread");
        pantryItem2 = pantryService.save(pantryItem2);

        Pantry pantryItem3 = new Pantry("Vanilla");
        pantryItem3 = pantryService.save(pantryItem3);

        Recipe recipe = recipeService
        .save(new Recipe("French Toast","10:20:30", "20:30:40", "Food network", new ArrayList<>(), new ArrayList<>() ));

        List<RecipeStep> recipeSteps = new ArrayList<>();
        recipeSteps.add(new RecipeStep(1, "In a small bowl, combine cinnamon, nutmeg, and sugar and set aside briefly.", recipe));
        recipeSteps.add(new RecipeStep(2, "In a 10-inch or 12-inch skillet, melt butter over medium heat. Whisk", recipe));

        List<Ingredient> items = new ArrayList<>();
        items.add(new Ingredient(pantryItem1, recipe, "1", "Tablespoon"));
        items.add(new Ingredient(pantryItem2, recipe, "3/4", "Tablespoon"));
        items.add(new Ingredient(pantryItem3, recipe, "5/8", "Tablespoon"));

        recipe.setDirections(recipeSteps);
        recipe.setIngredients(items);
        recipe = recipeService.save(recipe);

        //https://github.com/Rodney-Lewis/Delectable/issues/2
        //Verify that only 1 object was created
        assertTrue(recipeService.count() == 1);
    }

    
}