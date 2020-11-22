package com.delectable.schedule;

public enum MealTime {
    BREAKFAST("Breakfast"), BRUNCH("Brunch"), LUNCH("Lunch"), DINNER("Dinner"), SNACK("Snack");

    private final String name;

    private MealTime(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static String[] toStringArray() {
        String[] stringArray = new String[values().length];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = MealTime.values()[i].name;
        }
        return stringArray;
    }
}
