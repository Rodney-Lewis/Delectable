package com.delectable.shared.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CRUDRepository<T extends CRUDEntity<T>> extends PagingAndSortingRepository<T, Long> {
    Page<T> findAllByDeletedAndNameStartingWith(Pageable pageable, boolean deleted, String name);
}
