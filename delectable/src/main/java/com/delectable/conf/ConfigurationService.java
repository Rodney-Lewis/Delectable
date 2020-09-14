package com.delectable.conf;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationService extends CrudRepository<Configuration, Integer> {
}