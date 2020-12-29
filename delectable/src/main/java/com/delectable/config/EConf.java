package com.delectable.config;

public enum EConf {
    ROLES_GENERATED("ROLES_GENERATED", Etype.BOOLEAN),
    SUPER_USER_GENERATED("SUPER_USER_GENERATED", Etype.BOOLEAN),
    DEFAULT_UNITS_GENERATED("DEFAULT_UNITS_GENERATED", Etype.BOOLEAN),
    JWT_SECRET("JWT_SECRET", Etype.STRING);

    private final Etype type;
    private final String name;

    private EConf(String name, Etype type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
      return type.toString();
    }

    public String getName() {
        return name;
    }

}

enum Etype {
    BOOLEAN,
    INT,
    STRING
}