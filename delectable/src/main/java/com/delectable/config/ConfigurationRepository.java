package com.delectable.config;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {
  boolean existsByName(String string);

  Configuration findByName(String name);
}
