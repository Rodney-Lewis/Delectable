package com.delectable.shared.conf;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfRepository extends CrudRepository<Conf, Integer> {
  boolean existsByName(String string);

  Conf findByName(String name);
}
