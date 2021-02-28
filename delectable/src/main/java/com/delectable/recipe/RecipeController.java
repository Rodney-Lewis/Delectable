package com.delectable.recipe;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUDRepository;
import com.delectable.shared.crud.MarkDController;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController extends MarkDController<Recipe> {

    public RecipeController(CRUDRepository<Recipe> repository) {
        super(repository);
    }
}
