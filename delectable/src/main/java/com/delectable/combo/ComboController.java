package com.delectable.combo;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUSoftDeleteController;
import com.delectable.shared.crud.CRUSoftDeleteRepository;

@RestController
@RequestMapping(value = "/api/combo")
@CrossOrigin
public class ComboController extends CRUSoftDeleteController<Combo> {

    public ComboController(CRUSoftDeleteRepository<Combo> repository) {
        super(repository);
    }
}
