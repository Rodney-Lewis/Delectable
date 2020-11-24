package com.delectable.mealgroup;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MealGroupService extends PagingAndSortingRepository<MealGroup, Long> {

  Page<MealGroup> findAllByDeleted(Pageable pageable, boolean b);

  Page<MealGroup> findAllByDeletedAndNameStartingWith(Pageable pageable, boolean b, String query);

  List<MealGroup> findAllByDeleted(boolean b);

}
