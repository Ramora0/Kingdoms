package com.kingdoms.ui;

public class Keys {
  public static final int SHIFT = 16;

  public static boolean[] keyPressed = new boolean[128];

  public static void keyPressed(Object key) {
    char c = (Character) key;
    if (c < keyPressed.length) {
      keyPressed[c] = true;
    }
  }

  public static void keyReleased(Object key) {
    char c = (Character) key;
    if (c < keyPressed.length) {
      keyPressed[c] = false;
    }
  }
}
