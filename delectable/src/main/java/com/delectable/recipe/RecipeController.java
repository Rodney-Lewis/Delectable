package com.delectable.recipe;

import org.springframework.web.bind.annotation.*;
import com.delectable.shared.crud.CRUSoftDeleteController;
import com.delectable.shared.crud.CRUSoftDeleteRepository;

@RestController
@RequestMapping(value = "/api/recipe")
@CrossOrigin
public class RecipeController extends CRUSoftDeleteController<Recipe> {

    public RecipeController(CRUSoftDeleteRepository<Recipe> repository) {
        super(repository);
    }
}
