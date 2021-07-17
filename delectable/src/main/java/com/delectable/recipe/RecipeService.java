package com.delectable.recipe;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import com.delectable.shared.crud.CRUSoftDeleteService;

public class RecipeService extends CRUSoftDeleteService<Recipe> {
    public RecipeService(CRUSoftDeleteRepository<Recipe> repository) {
        super(repository);
    }
}
