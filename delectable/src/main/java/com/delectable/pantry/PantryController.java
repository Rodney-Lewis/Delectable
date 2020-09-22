package com.delectable.pantry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/pantry")
public class PantryController {

    @Autowired
    private PantryService pantryItemService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPantryItems(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) boolean schedulable) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
        List<PantryItem> contentList = new ArrayList<>();
        Page<PantryItem> pantryItemPages;

        if (query.equals("undefined") || query.equals("")) {
            pantryItemPages =
                    pantryItemService.findAllByDeletedAndSchedulable(pageable, false, schedulable);
        } else {
            pantryItemPages = pantryItemService.findAllByDeletedAndSchedulableAndNameStartingWith(
                    pageable, false, schedulable, query);
        }

        contentList = pantryItemPages.getContent();
        if (contentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", pantryItemPages.getContent());
        response.put("page", pantryItemPages.getNumber());
        response.put("size", pantryItemPages.getSize());
        response.put("totalPages", pantryItemPages.getTotalPages());
        response.put("totalElements", pantryItemPages.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
