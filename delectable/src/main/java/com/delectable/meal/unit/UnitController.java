package com.delectable.meal.unit;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pantry")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping
    public List<Unit> getUnitOfMeasurementList() {
        return (List<Unit>) unitService.findAll();
    }

    @GetMapping
    public Unit getUnitOfMeasurementById(@PathVariable int id) {
        Optional<Unit> unit = unitService.findById(id);
        if (unit.isPresent()) {
            return unit.get();
        }
        return null;
    }

    @PostMapping
    public Unit addUnitOfMeasurement(@Valid @RequestBody Unit newUnit) {
        return (unitService.save(newUnit));
    }

    @DeleteMapping("/{id}")
    public void deleteUnitOfMeasurement(@PathVariable int id) {
        unitService.deleteById(id);
    }

}
