package com.delectable.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.delectable.schedule.Schedule;
import com.delectable.schedule.ScheduleService;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    ResponseEntity<Map<String, Object>> getPageableRecipes(
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
    List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeService.findAllByDeleted(false);
    }

    @GetMapping("/{id}")
    Recipe getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        return recipe.get();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    Recipe addRecipe(@Valid @RequestBody Recipe newRecipe) {
        return (recipeService.save(newRecipe));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    Recipe updateRecipe(@Valid @PathVariable Long id, @Valid @RequestBody Recipe newRecipe) {
        Optional<Recipe> optRecipe = recipeService.findById(id);
        Recipe recipeToUpdate = optRecipe.get();
        newRecipe.setId(recipeToUpdate.getId());

        List<Schedule> scheduled = scheduleService.findAllByscheduledItemId(recipeToUpdate.getId());

        for (Schedule schedule : scheduled) {
            schedule.setScheduledItemName(newRecipe.getName());
            scheduleService.save(schedule);
        }

        return recipeService.save(newRecipe);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.findById(id);
        Recipe recipeToDelete = recipe.get();
        recipeToDelete.setDeleted(true);
        recipeService.save(recipeToDelete);
    }

}
