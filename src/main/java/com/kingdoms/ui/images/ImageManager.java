package com.kingdoms.ui.images;

import java.util.HashMap;
import java.util.Map;

import com.kingdoms.Kingdoms;
import com.kingdoms.helpers.canvas.Colors;

import processing.core.PGraphics;
import processing.core.PImage;

public class ImageManager {
  static Map<String, PImage> cache = new HashMap<String, PImage>();

  public static PImage getImage(String path) {
    if (cache.containsKey(path)) {
      return cache.get(path);
    }

    PImage image = loadImage(path);
    cache.put(path, image);
    return image;
  }

  public static PImage getColorImage(int color, int width, int height) {
    String cacheKey = Integer.toHexString(color) + ":" + width + ":" + height;

    if (cache.containsKey(cacheKey)) {
      return cache.get(cacheKey);
    }

    PGraphics image = Kingdoms.canvas.createGraphics(width, height);
    image.beginDraw();
    image.background(color);
    image.endDraw();

    cache.put(cacheKey, image);

    return image;
  }

  public static PImage getTeamImage(String path, int newColor) {
    String cacheKey = path + ":" + Integer.toHexString(newColor);

    if (cache.containsKey(cacheKey)) {
      return cache.get(cacheKey);
    }

    PImage image = getImage(path).copy();

    int purple = Colors.color(255, 0, 255);
    int darkPurple = Colors.color(200, 0, 200);
    int darkNewColor = Colors.lerpColor(newColor, Colors.color(0), 0.2);

    image.loadPixels();
    for (int i = 0; i < image.pixels.length; i++) {
      if (image.pixels[i] == purple) {
        image.pixels[i] = newColor;
      } else if (image.pixels[i] == darkPurple) {
        image.pixels[i] = darkNewColor;
      }
    }
    image.updatePixels();

    cache.put(cacheKey, image);

    return image;
  }

  public static PImage loadImage(String path) {
    return Kingdoms.canvas.loadImage(path);
  }
}
