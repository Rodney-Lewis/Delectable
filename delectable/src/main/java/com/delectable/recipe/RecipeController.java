package com.delectable.recipe;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRestaurants(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
        List<Recipe> contentList = new ArrayList<>();
        Page<Recipe> recipePages;

        if (query.equals("undefined") || query.equals("")) {
            recipePages = recipeService.findAllByDeleted(pageable, false);
        } else {
            recipePages = recipeService.findAllByDeletedAndNameStartingWith(pageable, false, query);
        }

        contentList = recipePages.getContent();
        if (contentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", recipePages.getContent());
        response.put("page", recipePages.getNumber());
        response.put("size", recipePages.getSize());
        response.put("totalPages", recipePages.getTotalPages());
        response.put("totalElements", recipePages.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
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
