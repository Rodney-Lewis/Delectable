package com.delectable.unit;

public enum EDefaultUnits {
        TEASPOONS("Teaspoon", "tsp"),
        TABLESPOONS("Tablespoon", "tbsp"),
        CUPS("Cup", "c"),
        FLUID_OUNCES("Fluid ounce", "fl. oz."),
        PINTS("Pint", "pt"),
        LITERS("Liter", "lt"),
        QUARTS("Quart", "qt"),
        MILLILITERS("Milliliter", "ml"),
        OUNCES("Ounce", "oz"),
        POUNDS("Pound", "lb"),
        GRAMS("Gram", "g"),
        KILOGRAMS("Kilogram", "kg"),
        PACKET("Packet"),
        TOTASTE("To taste"),
        BARREL("Barrel", "bbl"),
        DOZEN("Dozen", "doz");
    
        private final String name;
        private final String abbreviation;
    
        private EDefaultUnits(String name){
            this.name = name;
            this.abbreviation = name;
        }
    
        private EDefaultUnits(String name, String abbreviation){
            this.name = name;
            this.abbreviation = abbreviation;
        }
    
        public String getName() {
            return name;
        }
    
        public String getAbbreviation() {
            return abbreviation;
        }
}


