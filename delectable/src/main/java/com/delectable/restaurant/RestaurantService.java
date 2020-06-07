package com.delectable.restaurant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantService extends CrudRepository<Restaurant, Integer> {}
