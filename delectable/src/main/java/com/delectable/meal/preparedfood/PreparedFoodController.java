package com.delectable.meal.preparedfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/prepared")
public class PreparedFoodController {

    @Autowired
    private PreparedFoodService preparedFoodService;

    @GetMapping
    public List<PreparedFood> getRecipes() {
       return (List<PreparedFood>) preparedFoodService.findAllBydeleted(false);
    }

    @GetMapping("/{id}")
    public PreparedFood getRecipe(@PathVariable int id) {
        Optional<PreparedFood> preparedFood = preparedFoodService.findById(id);
        return preparedFood.get();
    }

    @PostMapping
    PreparedFood addRecipe(@Valid @RequestBody PreparedFood preparedFood) {
        return (preparedFoodService.save(preparedFood));
    }

    @PutMapping
    PreparedFood updateRecipe(@Valid @RequestBody PreparedFood preparedFood) {
        return (preparedFoodService.save(preparedFood));
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<PreparedFood> preparedFood = preparedFoodService.findById(id);
        PreparedFood preparedFoodToDelete = preparedFood.get();
        preparedFoodToDelete.setDeleted(true);
        preparedFoodService.save(preparedFoodToDelete);
    }
  
}