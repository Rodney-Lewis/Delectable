package com.delectable.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurantItems() {
        return (List<Restaurant>) restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        Optional<Restaurant> RestaurantItem = restaurantService.findById(id);
        return RestaurantItem.get();
    }

    @PostMapping
    public void addRestaurant(@RequestBody Restaurant RestaurantItem) {
        restaurantService.save(RestaurantItem);
    }

    @PutMapping
    void updateRestaurant(@RequestBody Restaurant RestaurantItem) {
        restaurantService.save(RestaurantItem);
    }

    @DeleteMapping("/{id}")
    void deleteRestaurantItemById(@PathVariable int id) {
        restaurantService.deleteById(id);
    }

}