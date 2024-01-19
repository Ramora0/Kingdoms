package com.kingdoms.ui;

import processing.core.PApplet;

public class Keys {
  public static final int SHIFT = 16;

  public static boolean[] keyPressed = new boolean[128];

  public static void keyPressed(Object canvas) {
    int keyCode = ((PApplet) canvas).keyCode;
    if (keyCode < keyPressed.length && keyCode >= 0) {
      keyPressed[keyCode] = true;
    }
  }

  public static void keyReleased(Object canvas) {
    int keyCode = ((PApplet) canvas).keyCode;
    if (keyCode < keyPressed.length && keyCode >= 0) {
      keyPressed[keyCode] = false;
    }
  }
}
