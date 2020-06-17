package com.delectable.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/restaurant")
@CrossOrigin
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurantItems() {
        return (List<Restaurant>) restaurantService.findAllByDeleted(false);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id) {
        Optional<Restaurant> RestaurantItem = restaurantService.findById(id);
        return RestaurantItem.get();
    }

    @PostMapping
    public void addRestaurant(@Valid @RequestBody Restaurant RestaurantItem) {
        restaurantService.save(RestaurantItem);
    }

    @PutMapping("/{id}")
    void updateRestaurant(@PathVariable int id, @Valid @RequestBody Restaurant newRestaurant) {
        Optional<Restaurant> optRestaurant = restaurantService.findById(id);
        Restaurant restaurantToUpdate = optRestaurant.get();
        if(!restaurantToUpdate.isDeleted()) {
            restaurantToUpdate.setName(newRestaurant.getName());
            restaurantService.save(restaurantToUpdate);
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