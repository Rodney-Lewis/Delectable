package com.delectable.restaurant;

import java.util.List;
import com.delectable.shared.crud.CRUSoftDeleteRepository;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class RestaurantUtil {

  private RestaurantService service;

  public RestaurantUtil(CRUSoftDeleteRepository<Restaurant> repository) {
    this.service = new RestaurantService(repository);
  }

  public List<Restaurant> createValidRestaurants(int numberOfItemsToCreate, boolean markAsDeleted) {
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    List<String> tags = new ArrayList<String>();
    tags.add("tag1");
    tags.add("tag2");
    tags.add("tag3");
    for (int i = 0; i < numberOfItemsToCreate; i++) {
      restaurants.add(new Restaurant("Name" + i, "123", null, "City" + i, "State" + i, "12345",
          "123-456-7890", "stackoverflow.com", true, true, true, "", markAsDeleted, tags));
      if (markAsDeleted == true) {
        restaurants.get(i).setDeleted(true);
      }
    }
    return restaurants;
  }

  public List<Restaurant> insertValidRestaurants(int numberOfItemsToCreate, boolean markAsDeleted) {
    List<Restaurant> restaurants = new ArrayList<Restaurant>();
    restaurants.addAll(createValidRestaurants(numberOfItemsToCreate, markAsDeleted));
    restaurants = (List<Restaurant>) service.createFromList(restaurants);
    return restaurants;

  }

  public Restaurant insertValidRestaurant(boolean markAsDeleted) {
    Restaurant restaurant = (createValidRestaurants(1, markAsDeleted).get(0));
    restaurant = service.create(restaurant);
    return restaurant;
  }

  public Restaurant getRestaurant(long id) {
    return service.get(id);
  }
}
