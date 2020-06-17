package com.delectable.pantry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryService extends CrudRepository<Pantry, Integer> {}
