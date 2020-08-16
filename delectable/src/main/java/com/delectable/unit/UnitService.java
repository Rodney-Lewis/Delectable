package com.delectable.unit;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitService extends CrudRepository<Unit, Integer> {
    List<Unit> findAllByDeleted(boolean deleted);
}
