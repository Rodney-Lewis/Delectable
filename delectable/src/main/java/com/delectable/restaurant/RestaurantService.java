package com.delectable.restaurant;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantService extends CrudRepository<Restaurant, Integer> {
    List<Restaurant> findAllByDeleted(boolean deleted);
}
