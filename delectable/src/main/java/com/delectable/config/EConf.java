package com.delectable.config;

public enum EConf {
    ROLES_GENERATED("ROLES_GENERATED", EType.BOOLEAN),
    SUPER_USER_GENERATED("SUPER_USER_GENERATED", EType.BOOLEAN),
    DEFAULT_UNITS_GENERATED("DEFAULT_UNITS_GENERATED", EType.BOOLEAN),
    JWT_SECRET("JWT_SECRET", EType.STRING);

    private final EType type;
    private final String name;

    private EConf(String name, EType type) {
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