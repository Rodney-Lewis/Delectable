package com.delectable.shared.crud;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class MarkDController<T extends MarkDEntity<T>> extends CRUController<T> {

    protected final MarkDService<T> markDSerivce;

    public MarkDController(CRUDRepository<T> repository) {
        super(repository);
        this.markDSerivce = new MarkDService<T>(repository){};
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void markAsDeleted(@PathVariable Long id) {
        markDSerivce.markAsDeleted(id);
    }
}