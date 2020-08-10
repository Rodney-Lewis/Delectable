package com.delectable.meal.pantry;

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
    public List<Pantry> getRecipes() {
       return (List<Pantry>) pantryService.findAllBydeleted(false);
    }

    @GetMapping("/{id}")
    public Pantry getRecipe(@PathVariable int id) {
        Optional<Pantry> preparedFood = pantryService.findById(id);
        return preparedFood.get();
    }

    @PostMapping
    Pantry addRecipe(@Valid @RequestBody Pantry preparedFood) {
        return (pantryService.save(preparedFood));
    }

    @PutMapping("/{id}")
    Pantry updateRecipe(@Valid @RequestBody Pantry newPreparedFood, @PathVariable int id) throws Exception {
        Optional<Pantry> optPreparedFood = pantryService.findById(id);
        if(optPreparedFood.isPresent()) {
            Pantry preparedFoodToUpdate = optPreparedFood.get();
            if(!preparedFoodToUpdate.isDeleted()) {
                preparedFoodToUpdate.setCookTimeHour(newPreparedFood.getCookTimeHour());
                preparedFoodToUpdate.setCookTimeMinute(newPreparedFood.getCookTimeMinute());
                preparedFoodToUpdate.setCookTimeSecond(newPreparedFood.getCookTimeSecond());
                preparedFoodToUpdate.setPrepTimeHour(newPreparedFood.getPrepTimeHour());
                preparedFoodToUpdate.setPrepTimeMinute(newPreparedFood.getPrepTimeMinute());
                preparedFoodToUpdate.setPrepTimeSecond(newPreparedFood.getPrepTimeSecond());
                preparedFoodToUpdate.setName(newPreparedFood.getName());
                preparedFoodToUpdate.setDescription(newPreparedFood.getDescription());
                preparedFoodToUpdate.setImageSource(newPreparedFood.getImageSource());
                preparedFoodToUpdate.setBrand(newPreparedFood.getBrand());
                preparedFoodToUpdate.setDirections(newPreparedFood.getDirections());
                return pantryService.save(preparedFoodToUpdate);
            } else {
                throw new Exception("Prepared item has been marked as deleted, it will not be updated.");
            }
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<Pantry> preparedFood = pantryService.findById(id);
        Pantry preparedFoodToDelete = preparedFood.get();
        preparedFoodToDelete.setDeleted(true);
        pantryService.save(preparedFoodToDelete);
    }
  
}