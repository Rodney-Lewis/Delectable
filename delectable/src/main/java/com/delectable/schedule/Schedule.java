package com.delectable.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.delectable.shared.crud.CRUDEntity;

@Entity
public class Schedule implements CRUDEntity<Schedule> {

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

    @Transient
    String scheduledItemName;

    public Schedule() {
    }

    public Schedule(@NotNull Long epoch, @NotNull MealTime mealTime,
            @NotNull ScheduleType scheduleType, @NotNull Long scheduledItemId,
            @NotNull String scheduledItemName) {
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

    public String getScheduledItemName() {
        return scheduledItemName;
    }

    public void setScheduledItemName(String scheduledItemName) {
        this.scheduledItemName = scheduledItemName;
    }

}
