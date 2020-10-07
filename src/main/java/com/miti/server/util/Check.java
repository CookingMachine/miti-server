package com.miti.server.util;

public class Check {
    public static boolean param(String parameter) {
        if (parameter.isEmpty() || parameter.equals(null))
            return false;
        return true;
    }

    public static boolean param(Boolean parameter) {
        if (parameter == null)
            return false;
        return true;
    }

    public static boolean param(Long parameter) {
        if (parameter == null)
            return false;
        return true;
    }

    public static boolean param(Integer parameter) {
        if (parameter == null)
            return  false;
        return true;
    }
}
