package com.delectable.restaurant;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CRUSoftDeleteRepository<Restaurant> {
}
