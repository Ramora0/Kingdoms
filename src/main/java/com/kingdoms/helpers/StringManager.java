package com.kingdoms.helpers;

public class StringManager {
  public static String enumToString(Enum<?> e) {
    String[] words = e.toString().replace("_", " ").toLowerCase().split(" ");
    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
    }
    return String.join(" ", words);
  }

  public static String properCaps(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }
}
