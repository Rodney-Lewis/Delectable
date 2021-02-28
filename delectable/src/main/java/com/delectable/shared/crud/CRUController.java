package com.delectable.shared.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class CRUController<T extends CRUDEntity<T>> {

    protected final CRUDService<T> service;

    public CRUController(CRUDRepository<T> repository) {
        this.service = new CRUDService<T>(repository) {};
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T created) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(created));
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> getPage(Pageable pageable, @RequestParam(defaultValue = "") String name){
        return ResponseEntity.ok(service.getPage(pageable, name));
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
}