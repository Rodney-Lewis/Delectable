package com.delectable.shared.crud;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CRUSoftDeleteRepository<T extends CRUDEntity<T>> extends PagingAndSortingRepository<T, Long> {
    Page<T> findAllByDeleted(Pageable pageable, boolean deleted);
    Optional<T> findByIdAndDeleted(Long id, boolean b);
}
