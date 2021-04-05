package com.delectable.shared.crud;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class CRUHardDeleteController<T extends CRUDEntity<T>> {

    protected final CRUHardDeleteService<T> service;

    public CRUHardDeleteController(CRUHardDeleteRepository<T> repository) {
        this.service = new CRUHardDeleteService<T>(repository) {
        };
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(created));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<T>> getPage(Pageable pageable) {
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll(List<Long> ids) {
        return ResponseEntity.ok(service.getAllByIds(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
