package com.delectable.meal.unit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitService extends CrudRepository<Unit, Integer> {

}
