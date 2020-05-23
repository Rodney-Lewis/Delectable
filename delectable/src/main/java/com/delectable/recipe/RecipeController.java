package com.delectable.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeService.findAll();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.get();
    }

    @PostMapping
    Recipe addRecipe(@RequestBody Recipe recipe) {

        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] fileName = recipe.getImageSource().split(pattern);
        recipe.setImageSource(fileName[fileName.length - 1]);
        recipeService.save(recipe);
        return (recipe);
    }

    @PutMapping
    void updateRecipe(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        recipeService.deleteById(id);
    }
}