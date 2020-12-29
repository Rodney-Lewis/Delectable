package com.delectable.mealgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.delectable.schedule.Schedule;
import com.delectable.schedule.ScheduleService;

@RestController
@RequestMapping(value = "/api/mealgroup")
@CrossOrigin
public class MealGroupController {

    @Autowired
    MealGroupService mealGroupService;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    ResponseEntity<Map<String, Object>> getPageableMealGroups(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "name"));
        Page<MealGroup> mealGroupPages;

        if (query.equals("undefined") || query.equals("")) {
            mealGroupPages = mealGroupService.findAllByDeleted(pageable, false);
        } else {
            mealGroupPages =
                    mealGroupService.findAllByDeletedAndNameStartingWith(pageable, false, query);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", mealGroupPages.getContent());
        response.put("page", mealGroupPages.getNumber());
        response.put("size", mealGroupPages.getSize());
        response.put("totalPages", mealGroupPages.getTotalPages());
        response.put("totalElements", mealGroupPages.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    List<MealGroup> getAllMealGroups() {
        return mealGroupService.findAllByDeleted(false);

    }

    @GetMapping("/{id}")
    MealGroup getMealGroupByID (@PathVariable Long id) {
        Optional<MealGroup> mealGroup = mealGroupService.findById(id);
        return mealGroup.get();
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('SUPER_USER')")
    @PostMapping
    MealGroup createMealGroup(@Valid @RequestBody MealGroup newMealGroup) {
        return mealGroupService.save(newMealGroup);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('SUPER_USER')")
    @PutMapping("/{id}")
    MealGroup updateMealGroup(@PathVariable Long id, @Valid @RequestBody MealGroup newMealGroup) {
        Optional<MealGroup> optMealGroup = mealGroupService.findById(id);
        MealGroup mealGroupToUpdate = optMealGroup.get();
        newMealGroup.setId(mealGroupToUpdate.getId());

        List<Schedule> scheduled = scheduleService.findAllByscheduledItemId(mealGroupToUpdate.getId());

        for (Schedule schedule : scheduled) {
            schedule.setScheduledItemName(newMealGroup.getName());
            scheduleService.save(schedule);
        }

        return mealGroupService.save(newMealGroup);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('SUPER_USER')")
    @DeleteMapping("/{id}")
    void deleteMealGroup(@PathVariable Long id) {
        MealGroup mealGroupToDelete = mealGroupService.findById(id).get();
        mealGroupToDelete.setDeleted(true);
        mealGroupService.save(mealGroupToDelete);
    }


}
