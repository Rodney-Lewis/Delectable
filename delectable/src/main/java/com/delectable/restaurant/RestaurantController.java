package com.delectable.restaurant;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUSoftDeleteController;
import com.delectable.shared.crud.CRUSoftDeleteRepository;

@RestController
@RequestMapping(value = "/api/restaurant")
@CrossOrigin
public class RestaurantController extends CRUSoftDeleteController<Restaurant> {

    public RestaurantController(CRUSoftDeleteRepository<Restaurant> repository) {
        super(repository);
    }
}
