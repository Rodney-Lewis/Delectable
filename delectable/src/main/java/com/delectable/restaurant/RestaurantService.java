package com.delectable.restaurant;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import com.delectable.shared.crud.CRUSoftDeleteService;

public class RestaurantService extends CRUSoftDeleteService<Restaurant>{
    public RestaurantService(CRUSoftDeleteRepository<Restaurant> repository) {
        super(repository);
    }
}
