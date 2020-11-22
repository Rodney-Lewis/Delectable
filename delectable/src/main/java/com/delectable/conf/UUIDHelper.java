package com.delectable.conf;

import java.security.SecureRandom;

public class UUIDHelper {
    static final String AllowedChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rand = new SecureRandom();
    static private final int length = 8;

    public static String generateUUID(String prefix) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AllowedChars.charAt(rand.nextInt(AllowedChars.length())));
        return prefix.toUpperCase()+"-"+sb.toString();
    }
}
