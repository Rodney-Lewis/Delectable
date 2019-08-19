package com.delectable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.delectable.model.Pantry;
import com.delectable.service.PantryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/pantry")
@CrossOrigin
public class PantryController {

    @Autowired
    private PantryService pantryService;

    @PostMapping("/add")
    public void addPantryItem(@RequestBody Pantry pantryItem) {
        pantryService.save(pantryItem);
    }

    @GetMapping("/get")
    public List<Pantry> getPantryItems() {
        return (List<Pantry>)pantryService.findAll();
    }

    @GetMapping("get/{id}")
    public Pantry getPantryItemById(@PathVariable int id) {
        Optional<Pantry> pantryItem = pantryService.findById(id);
        return pantryItem.get();
    }

    @PutMapping("update")
    void updatePantryItem(@RequestBody Pantry pantryItem) {       
        pantryService.save(pantryItem);
    }

    @DeleteMapping("/delete/{id}")
    void deletePantryItemById(@PathVariable int id) {
        pantryService.deleteById(id);
    }
    
}