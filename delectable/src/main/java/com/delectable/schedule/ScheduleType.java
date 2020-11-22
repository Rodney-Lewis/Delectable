package com.delectable.schedule;

public enum ScheduleType {
    RESTAURANT("Restaurant"), RECIPE("Recipe"), MEAL("Meal");

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