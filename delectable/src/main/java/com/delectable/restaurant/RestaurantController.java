package com.delectable.restaurant;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUDRepository;
import com.delectable.shared.crud.MarkDController;

@RestController
@RequestMapping(value = "/api/restaurant")
@CrossOrigin
public class RestaurantController extends MarkDController<Restaurant> {

    public RestaurantController(CRUDRepository<Restaurant> repository) {
        super(repository);
    }
}
