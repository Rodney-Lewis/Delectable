package com.delectable.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    Long epoch;

    @NotNull
    MealTime mealTime;

    @NotNull
    ScheduleType scheduleType;

    @NotNull
    Long scheduledItemId;

    public Schedule() {
    }

    public Schedule(@NotNull Long epoch, @NotNull MealTime mealTime,
            @NotNull ScheduleType scheduleType, @NotNull Long scheduledItemId) {
        this.epoch = epoch;
        this.mealTime = mealTime;
        this.scheduleType = scheduleType;
        this.scheduledItemId = scheduledItemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEpoch() {
        return epoch;
    }

    public void setEpoch(Long epoch) {
        this.epoch = epoch;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Long getScheduledItemId() {
        return scheduledItemId;
    }

    public void setScheduledItemId(Long scheduledItemId) {
        this.scheduledItemId = scheduledItemId;
    }

}
