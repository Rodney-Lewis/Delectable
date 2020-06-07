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
    int id;

    @NotNull
    long epoch;

    @NotNull
    String mealType;

    @NotNull
    String scheduleType;

    @NotNull
    int scheduledTypeId;

    public Schedule() {
    }

    public Schedule(long epoch, String mealType, String scheduleType) {
        this.epoch = epoch;
        setMealType(mealType);
        setScheduleType(scheduleType);
    }

    public Schedule(int id, long epoch, String mealType, String scheduleType) {
        this.id = id;
        this.epoch = epoch;
        setMealType(mealType);
        setScheduleType(scheduleType);
    }

    public int getId() {
        return id;
    }

    public long getEpoch() {
        return epoch;
    }

    public String getMealType() {
        return mealType;
    }

    private void setMealType(String mealType) {
        for (MealTypes type : MealTypes.values()) {
            if (type.toString().equals(mealType)) {
                this.mealType = mealType;
            }
        }
    }

    public String getScheduleType() {
        return this.scheduleType;
    }

    public int getScheduledTypeId() {
        return this.scheduledTypeId;
    }

    private void setScheduleType(String scheduleType) {
        for (ScheduleType type : ScheduleType.values()) {
            if (type.toString().equals(scheduleType)) {
                this.scheduleType = scheduleType;
            }
        }
    }

    public enum ScheduleType {
        RESTAURANT("Restaurant"), RECIPE("Recipe"), PREPAREDFOOD("Prepared food");

        private final String name;

        private ScheduleType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static String[] toStringArray() {
            String[] stringArray = new String[values().length];
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = ScheduleType.values()[i].name;
            }
            return stringArray;
        }
    }

    public enum MealTypes {
        BREAKFAST("Breakfast"), LUNCH("Lunch"), DINNER("Dinner"), SNACK("Snack");

        private final String name;

        private MealTypes(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static String[] toStringArray() {
            String[] stringArray = new String[values().length];
            for (int i = 0; i < stringArray.length; i++) {
                stringArray[i] = MealTypes.values()[i].name;
            }
            return stringArray;
        }
    }

}