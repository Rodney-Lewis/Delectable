package com.delectable.recipe;

import com.delectable.shared.crud.CRUSoftDeleteRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CRUSoftDeleteRepository<Recipe> {
}
