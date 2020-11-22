package com.delectable.recipe;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeService extends PagingAndSortingRepository<Recipe, Long> {
    List<Recipe> findAllByDeleted(boolean deleted);
    Page<Recipe> findAllByDeleted(Pageable pageable, boolean deleted);
    Page<Recipe> findAllByDeletedAndNameStartingWith(Pageable pageable, boolean deleted, String query);
}
