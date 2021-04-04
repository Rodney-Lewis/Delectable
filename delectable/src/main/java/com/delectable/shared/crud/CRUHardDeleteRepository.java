package com.delectable.shared.crud;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CRUHardDeleteRepository<T extends CRUDEntity<T>> extends PagingAndSortingRepository<T, Long> {
}
