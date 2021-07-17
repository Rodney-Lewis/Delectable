package com.delectable.unit;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/unit")
public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    @GetMapping
    public List<Unit> getUnitOfMeasurementList() {
        return (List<Unit>) unitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Unit getUnitOfMeasurementById(@PathVariable int id) {
        Optional<Unit> unit = unitRepository.findById(id);
        return unit.get();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Unit addUnitOfMeasurement(@Valid @RequestBody Unit newUnit) {
        return (unitRepository.save(newUnit));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public void deleteUnitOfMeasurement(@PathVariable int id) {
        unitRepository.deleteById(id);
    }

}
