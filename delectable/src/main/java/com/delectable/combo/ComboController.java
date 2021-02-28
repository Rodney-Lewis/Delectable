package com.delectable.combo;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUDRepository;
import com.delectable.shared.crud.MarkDController;

@RestController
@RequestMapping(value = "/api/combo")
@CrossOrigin
public class ComboController extends MarkDController<Combo> {

    public ComboController(CRUDRepository<Combo> repository) {
        super(repository);
    }
}
