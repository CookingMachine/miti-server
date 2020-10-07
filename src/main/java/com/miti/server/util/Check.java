package com.miti.server.util;

public class Check {
  public static boolean param(String parameter) {
    return !parameter.isEmpty();
  }

  public static boolean param(Boolean parameter) {
    return parameter != null;
  }

  public static boolean param(Long parameter) {
    return parameter != null;
  }

  public static boolean param(Integer parameter) {
    return parameter != null;
  }
}
