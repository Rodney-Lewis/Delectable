package com.delectable.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delectable.model.Pantry;

@Repository
public interface PantryService extends CrudRepository<Pantry, Integer> {}
