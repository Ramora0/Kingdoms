package com.kingdoms.helpers.canvas;

import com.kingdoms.Kingdoms;

public class Colors {
  public static final int BEACH = color(227, 208, 114);
  public static final int SHALLOW_WATER = color(0, 153, 219);
  public static final int PLAINS = color(71, 163, 71);

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
