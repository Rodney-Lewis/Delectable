package com.delectable.restaurant;

import com.delectable.shared.crud.CRUDRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CRUDRepository<Restaurant> {
}
