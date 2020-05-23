package com.delectable.pantry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pantry")
@CrossOrigin
public class PantryController {

    @Autowired
    private PantryService pantryService;

    @GetMapping
    public List<Pantry> getPantryItems() {
        return (List<Pantry>) pantryService.findAll();
    }

    @GetMapping("/{id}")
    public Pantry getPantryItemById(@PathVariable int id) {
        Optional<Pantry> pantryItem = pantryService.findById(id);
        return pantryItem.get();
    }

    @PostMapping
    public void addPantryItem(@RequestBody Pantry pantryItem) {
        pantryService.save(pantryItem);
    }

    @PutMapping
    void updatePantryItem(@RequestBody Pantry pantryItem) {
        pantryService.save(pantryItem);
    }

    @DeleteMapping("/{id}")
    void deletePantryItemById(@PathVariable int id) {
        pantryService.deleteById(id);
    }

}