package com.delectable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.delectable.model.Pantry;
import com.delectable.service.PantryService;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@RequestMapping(value="/pantry")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PantryController {

    @Autowired
    private PantryService pantryService;

    @GetMapping(value="/get")
    public List<Pantry> getPantryItems() {
        return (List<Pantry>)pantryService.findAll();
    }
    
    @PostMapping(value="/add")
    public void addPantryItem(@RequestBody Pantry pantryItem) {
        pantryService.save(pantryItem);
    }

    @GetMapping("get/{id}")
    public Pantry getPantryItemById(@PathVariable int id) {
        Optional<Pantry> pantryItem = pantryService.findById(id);
        if(!pantryItem.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            return pantryItem.get();
        }
    }

    @PutMapping("update")
    void updatePantryItem(@RequestBody Pantry pantryItem) {
        Optional<Pantry> newRecipe = pantryService.findById(pantryItem.getId());
        if(!newRecipe.isPresent()) {
            throw new EntityNotFoundException();
        } else {
            pantryService.save(pantryItem);
        }
    }

    @DeleteMapping("/delete/{id}")
    void deletePantryItemById(@PathVariable int id) {
        pantryService.deleteById(id);
    }
    
}