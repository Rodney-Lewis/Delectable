package com.delectable.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
    Recipe addRecipe(@Valid @RequestBody Recipe newRecipe) {
        return (recipeService.save(newRecipe));
    }

    @PutMapping("/{id}")
    Recipe updateRecipe(@Valid @PathVariable int id, @RequestBody Recipe recipeUpdates)
            throws Exception {
        Optional<Recipe> optRecipe = recipeService.findById(id);
        if (optRecipe.isPresent()) {
            Recipe recipeToUpdate = optRecipe.get();
            if (recipeToUpdate.isDeleted())
                throw new Exception("Recipe has been marked as deleted, it will not be updated.");
            else {
                recipeUpdates.setId(recipeToUpdate.getId());
                return recipeService.save(recipeUpdates);
            }
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        Recipe recipeToDelete = recipe.get();
        recipeToDelete.setDeleted(true);
        recipeService.save(recipeToDelete);
    }

}
