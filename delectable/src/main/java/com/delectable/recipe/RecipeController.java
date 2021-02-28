package com.delectable.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    RecipeService recipeService;


    @PreAuthorize("hasRole('USER')")
    @PostMapping
    Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping
    ResponseEntity<Map<String, Object>> getPageableRecipes(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query) {

        return new ResponseEntity<>(recipeService.getPageableRecipes(page, size, query),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    Recipe updateRecipe(@Valid @PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

}
