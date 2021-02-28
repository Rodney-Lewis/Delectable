package com.delectable.recipe;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import com.delectable.schedule.Schedule;
import com.delectable.schedule.ScheduleService;
import com.delectable.shared.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    PaginationService paginationService;

    @Autowired
    ScheduleService scheduleService;

    Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    List<Recipe> addRecipes(List<Recipe> recipes) {
        return (List<Recipe>) recipeRepository.saveAll(recipes);
    }

    Map<String, Object> getPageableRecipes(int page, int size, String query) {
        Pageable pageable = paginationService.getPageable(page, size);
        Page<Recipe> recipePages =
                recipeRepository.findAllByDeletedAndNameStartingWith(pageable, false, query);
        return paginationService.buildPageableResponse(recipePages);
    }

    Recipe getRecipeById(Long id) {
        try {
            Optional<Recipe> recipe = recipeRepository.findById(id);
            return recipe.get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unknown recipe ID - " + id);
        }
    }

    Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        //check if that recipe actually exsists to prevent incorrect creations
        Recipe recipe = getRecipeById(id);
        updatedRecipe.setId(recipe.getId());

        List<Schedule> scheduled = scheduleService.findAllByscheduledItemId(updatedRecipe.getId());

        for (Schedule schedule : scheduled) {
            schedule.setScheduledItemName(updatedRecipe.getName());
            scheduleService.save(schedule);
        }

        return recipeRepository.save(updatedRecipe);
    }

    void deleteRecipe(Long id) {
        Recipe recipe = getRecipeById(id);
        recipe.setDeleted(true);
        recipeRepository.save(recipe);
    }
}
