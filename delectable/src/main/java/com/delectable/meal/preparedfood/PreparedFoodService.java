package com.delectable.meal.preparedfood;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparedFoodService extends CrudRepository<PreparedFood, Integer> {
    List<PreparedFood> findAllBydeleted(boolean deleted);
}