package com.delectable.recipe;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeUtil {

    @Autowired
    private RecipeService recipeService;

    public List<Recipe> createValidTestRecipes(int numberOfItemsToCreate, boolean markAsDeleted) {

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        List<String> tags = new ArrayList<String>();
        List<Direction> instructions = new ArrayList<Direction>();

        List<Recipe> recipes = new ArrayList<Recipe>();
        for (int i = 0; i < numberOfItemsToCreate; i++) {

            for (int j = 1; j <= 15; j++) {
                ingredients.clear();
                tags.clear();
                instructions.clear();
                ingredients.add(new Ingredient("Ingredient" + j, String.valueOf(j), "unit" + j));
                tags.add("tag" + j);
                instructions.add(new Direction(j, "Step" + j));
            }

            recipes.add(new Recipe("Name" + i, "Source" + i,
                    "Lorem ipsum dolor sit amet, sea no erant convenire, ne per esse facer oportere. An usu copiosae luptatum, ornatus veritus pro ne, in nam etiam aliquid. Et doming commodo ceteros mel. Eros oporteat nominati eos id. Tota eligendi ea est, legendos instructior est te, cu vix labore delenit. Cu nihil postulant quo, quas soleat quo id. Sale ludus deseruisse quo at.",
                    (byte) 10, (byte) 10, (byte) 10, (byte) 10, (byte) 10, (byte) 10, ingredients,
                    instructions, tags, ""));

            if (markAsDeleted == true) {
                recipes.get(i).setDeleted(true);
            }
        }

        return recipes;
    }

    public List<Recipe> insertValidTestRecipes(int numberOfRecipes, boolean markAsDeleted) {
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.addAll(createValidTestRecipes(numberOfRecipes, markAsDeleted));
        recipes = (List<Recipe>) recipeService.saveAll(recipes);
        return recipes;
    }

    public Recipe insertValidTestRecipe(boolean markAsDeleted) {
        Recipe recipe = createValidTestRecipes(1, markAsDeleted).get(0);
        recipe = recipeService.save(recipe);
        return recipe;
    }
}
