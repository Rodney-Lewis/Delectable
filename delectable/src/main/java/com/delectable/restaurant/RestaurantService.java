package com.delectable.restaurant;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantService extends PagingAndSortingRepository<Restaurant, Long> {
    Page<Restaurant> findByDeletedAndNameContaining(Pageable pageable, boolean b, String query);
    Page<Restaurant> findAllByDeletedAndNameStartingWith(Pageable pageable, boolean b,
        String string);
    Page<Restaurant> findAllByDeleted(Pageable pageable, boolean deleted);
    List<Restaurant> findAllByDeleted(boolean b);
}
