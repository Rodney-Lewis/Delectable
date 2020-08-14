package com.delectable.pantry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/pantry")
public class PantryController {

    @Autowired
    private PantryService pantryService;

    @GetMapping
    public List<PantryItem> getRecipes() {
        return (List<PantryItem>) pantryService.findAllBydeleted(false);
    }

    @GetMapping("/{id}")
    public PantryItem getRecipe(@PathVariable int id) {
        Optional<PantryItem> preparedFood = pantryService.findById(id);
        return preparedFood.get();
    }

    @PostMapping
    PantryItem addRecipe(@Valid @RequestBody PantryItem preparedFood) {
        return (pantryService.save(preparedFood));
    }

    @PutMapping("/{id}")
    PantryItem updateRecipe(@Valid @RequestBody PantryItem newPreparedFood, @PathVariable int id)
            throws Exception {
        Optional<PantryItem> optPreparedFood = pantryService.findById(id);
        if (optPreparedFood.isPresent()) {
            PantryItem preparedFoodToUpdate = optPreparedFood.get();
            if (!preparedFoodToUpdate.isDeleted()) {
                preparedFoodToUpdate.setName(newPreparedFood.getName());
                preparedFoodToUpdate.setImageSource(newPreparedFood.getImageSource());
                preparedFoodToUpdate.setBrand(newPreparedFood.getBrand());
                preparedFoodToUpdate.setIngredients(newPreparedFood.getIngredients());
                return pantryService.save(preparedFoodToUpdate);
            } else {
                throw new Exception(
                        "Pantry item has been marked as deleted, it will not be updated.");
            }
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<PantryItem> preparedFood = pantryService.findById(id);
        PantryItem preparedFoodToDelete = preparedFood.get();
        preparedFoodToDelete.setDeleted(true);
        pantryService.save(preparedFoodToDelete);
    }

}
