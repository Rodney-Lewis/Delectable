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
    private PantryService pantryItemService;

    @GetMapping
    public List<PantryItem> getPantryItems(@RequestParam(required = false) boolean schedulable) {
        if (schedulable == true) {
            return (List<PantryItem>) pantryItemService.findAllBydeletedAndSchedulable(false, true);

        } else {
            return (List<PantryItem>) pantryItemService.findAllBydeleted(false);
        }
    }

    @GetMapping("/{id}")
    public PantryItem getPantryItem(@PathVariable int id) {
        Optional<PantryItem> pantryItem = pantryItemService.findById(id);
        return pantryItem.get();
    }

    @PostMapping
    PantryItem addPantryItem(@Valid @RequestBody PantryItem pantryItem) {
        return (pantryItemService.save(pantryItem));
    }

    @PutMapping("/{id}")
    PantryItem updatePantryItem(@Valid @RequestBody PantryItem newPantryItem, @PathVariable int id)
            throws Exception {
        Optional<PantryItem> optPantryItem = pantryItemService.findById(id);
        if (optPantryItem.isPresent()) {
            PantryItem pantryItemToUpdate = optPantryItem.get();
            if (!pantryItemToUpdate.isDeleted()) {
                pantryItemToUpdate.setName(newPantryItem.getName());
                pantryItemToUpdate.setImageSource(newPantryItem.getImageSource());
                pantryItemToUpdate.setBrand(newPantryItem.getBrand());
                pantryItemToUpdate.setIngredients(newPantryItem.getIngredients());
                return pantryItemService.save(pantryItemToUpdate);
            } else {
                throw new Exception(
                        "Pantry item has been marked as deleted, it will not be updated.");
            }
        } else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    void deletePantryItem(@PathVariable int id) {
        Optional<PantryItem> pantryItem = pantryItemService.findById(id);
        PantryItem pantryItemToMarkAsDeleted = pantryItem.get();
        pantryItemToMarkAsDeleted.setDeleted(true);
        pantryItemService.save(pantryItemToMarkAsDeleted);
    }

}
