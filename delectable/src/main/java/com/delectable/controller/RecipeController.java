package com.delectable.controller;

import com.delectable.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.delectable.model.Recipe;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    Recipe addRecipe(@RequestBody String payload) {
        Recipe recipe = new Recipe();

        //Combine recipe forms
        //Filesource -> Call image API to store it.
        //Image API needs to return the path in which it's stored
        //Path needs to be added to the recipe

        return(recipeService.save(recipe));
    }

    @GetMapping("/get")
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeService.findAll();
    }

    @GetMapping("/get/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.get();
    }

    @PutMapping("/update")
    void updateRecipe(@RequestBody Recipe recipe) {
        recipe.setTotalTime();
        recipeService.save(recipe);
    }

    @DeleteMapping("/delete/{id}")
    void deleteRecipe(@PathVariable int id) {
        recipeService.deleteById(id);
    }
}