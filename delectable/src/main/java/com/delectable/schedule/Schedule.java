package com.delectable.schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    Long epoch;

    @NotNull
    String mealTime;

    @NotNull
    String scheduleType;

    @NotNull
    Long scheduledItemId;

    @NotNull
    String scheduledItemName;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(Long epoch) {
        this.epoch = epoch;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealType(String mealTime) {
        for (MealTime type : MealTime.values()) {
            if (type.toString().equals(mealTime)) {
                this.mealTime = mealTime;
            }
        }
    }

    public String getScheduleType() {
        return this.scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        for (ScheduleType type : ScheduleType.values()) {
            if (type.toString().equals(scheduleType)) {
                this.scheduleType = scheduleType;
            }
        }
    }

    public Long getScheduledItemId() {
        return scheduledItemId;
    }

    public String getScheduledItemName() {
        return scheduledItemName;
    }

    public void setScheduledItemName(String scheduledItemName) {
        this.scheduledItemName = scheduledItemName;
    }

    public void setScheduledItemId(Long scheduledItemId) {
        this.scheduledItemId = scheduledItemId;
    }

}
