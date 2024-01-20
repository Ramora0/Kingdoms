package com.kingdoms.helpers.math;

import java.util.List;

import com.kingdoms.Kingdoms;

public class MathUtils {
  public static <T> T random(T[] values) {
    return values[(int) (Math.random() * values.length)];
  }

  public static int random(int[] values) {
    return values[(int) (Math.random() * values.length)];
  }

  public static <T> T random(List<T> values) {
    return values.get((int) (Math.random() * values.size()));
  }

  public static boolean chance(double chance) {
    return Math.random() < chance;
  }

  public static float noise(float x, float y) {
    return Kingdoms.canvas.noise((float) x, (float) y);
  }

  public static double distance(double x1, double y1, double x2, double y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }
}
