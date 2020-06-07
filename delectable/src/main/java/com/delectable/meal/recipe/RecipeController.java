package com.delectable.meal.recipe;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeService.findAllBydeleted(false);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.get();
    }

    @PostMapping
    Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
        return (recipeService.save(recipe));
    }

    @PutMapping
    Recipe updateRecipe(@Valid @RequestBody Recipe recipe) {
        return (recipeService.save(recipe));
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        Recipe recipeToDelete = recipe.get();
        recipeToDelete.setDeleted(true);
        recipeService.save(recipeToDelete);
    }
    
}