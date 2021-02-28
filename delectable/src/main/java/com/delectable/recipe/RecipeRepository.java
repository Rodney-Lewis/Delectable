package com.delectable.recipe;

import com.delectable.shared.crud.CRUDRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CRUDRepository<Recipe> {
}
