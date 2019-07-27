package com.delectable.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delectable.model.Recipe;

@Repository
public interface RecipeService extends CrudRepository<Recipe, Integer> {}
