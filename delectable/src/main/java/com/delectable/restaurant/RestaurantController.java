package com.delectable.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getRestaurants(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
        List<Restaurant> contentList = new ArrayList<>();
        Page<Restaurant> restaurantPages;

        if (query.equals("undefined") || query.equals("")) {
            restaurantPages = restaurantService.findAllByDeleted(pageable, false);
        } else {
            restaurantPages =
                    restaurantService.findAllByDeletedAndNameStartingWith(pageable, false, query);
        }

        contentList = restaurantPages.getContent();
        if (contentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", restaurantPages.getContent());
        response.put("page", restaurantPages.getNumber());
        response.put("size", restaurantPages.getSize());
        response.put("totalPages", restaurantPages.getTotalPages());
        response.put("totalElements", restaurantPages.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        Optional<Restaurant> RestaurantItem = restaurantService.findById(id);
        return RestaurantItem.get();
    }

    @PostMapping
    public Restaurant addRestaurant(@Valid @RequestBody Restaurant RestaurantItem) {
        return (restaurantService.save(RestaurantItem));
    }

    @PutMapping("/{id}")
    Restaurant updateRestaurant(@PathVariable int id,
            @Valid @RequestBody Restaurant newRestaurant) {
        Optional<Restaurant> optRestaurant = restaurantService.findById(id);
        Restaurant restaurantToUpdate = optRestaurant.get();
        if (!restaurantToUpdate.isDeleted()) {
            newRestaurant.setId(restaurantToUpdate.getId());
            return restaurantService.save(newRestaurant);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    void deleteRestaurantItemById(@PathVariable int id) {
        Optional<Restaurant> optRestaurant = restaurantService.findById(id);
        Restaurant restaurantToMarkAsDeleted = optRestaurant.get();
        restaurantToMarkAsDeleted.setDeleted(true);
        restaurantService.save(restaurantToMarkAsDeleted);
    }

}
