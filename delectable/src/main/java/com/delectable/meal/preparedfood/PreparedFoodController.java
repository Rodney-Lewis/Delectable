package com.delectable.meal.preparedfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

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

    @PutMapping("/{id}")
    PreparedFood updateRecipe(@Valid @RequestBody PreparedFood newPreparedFood, @PathVariable int id) throws Exception {
        Optional<PreparedFood> optPreparedFood = preparedFoodService.findById(id);
        if(optPreparedFood.isPresent()) {
            PreparedFood preparedFoodToUpdate = optPreparedFood.get();
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
                return preparedFoodService.save(preparedFoodToUpdate);
            } else {
                throw new Exception("Prepared item has been marked as deleted, it will not be updated.");
            }
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    void deleteRecipe(@PathVariable int id) {
        Optional<PreparedFood> preparedFood = preparedFoodService.findById(id);
        PreparedFood preparedFoodToDelete = preparedFood.get();
        preparedFoodToDelete.setDeleted(true);
        preparedFoodService.save(preparedFoodToDelete);
    }
  
}