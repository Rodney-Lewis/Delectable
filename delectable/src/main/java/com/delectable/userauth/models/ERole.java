package com.delectable.userauth.models;

import java.util.EnumSet;

public enum ERole {
    SUPER_USER,
    ADMIN,
    USER,
    VEIWER;

    public static ERole[] getRoles() {
        ERole[] roles = (ERole[]) EnumSet.of(ERole.ADMIN, ERole.USER, ERole.VEIWER).toArray();
        return roles;
    }
}