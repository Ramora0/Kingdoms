package com.kingdoms.helpers.canvas;

import com.kingdoms.Kingdoms;

public class Colors {
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
