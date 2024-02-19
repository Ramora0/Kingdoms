package com.kingdoms.helpers.canvas;

import com.kingdoms.Kingdoms;

public class Colors {
  public static int SHALLOW_WATER;
  public static int DEEP_WATER;
  public static int PLAINS;
  public static int FOREST;
  public static int BEACH;
  public static int CLIFF;

  public static void load() {
    SHALLOW_WATER = color(0, 153, 219);
    DEEP_WATER = color(5, 121, 170);
    PLAINS = color(71, 163, 71);
    FOREST = color(33, 121, 33);
    BEACH = color(227, 208, 114);
    CLIFF = color(89);
  }

  public static int color(int r, int g, int b) {
    return Kingdoms.canvas.color(r, g, b);
  }

  public static int color(int w) {
    return Kingdoms.canvas.color(w);
  }

  public static int lerpColor(int c1, int c2, double amt) {
    return Kingdoms.canvas.lerpColor(c1, c2, (float) amt);
  }
}
