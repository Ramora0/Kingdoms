package com.kingdoms.ui.images;

import java.util.HashMap;
import java.util.Map;

import com.kingdoms.Kingdoms;

import processing.core.PImage;

public class ImageManager {
  static Map<String, PImage> cache = new HashMap<String, PImage>();

  public static PImage getImage(String path) {
    if (cache.containsKey(path)) {
      return cache.get(path);
    } else {
      PImage image = loadImage(path);
      cache.put(path, image);
      return image;
    }
  }

  public static PImage loadImage(String path) {
    return Kingdoms.canvas.loadImage(path);
  }

  /**
   * Gets the border image from the given borders where true indicates no border
   */
  public static PImage getBorder(boolean right, boolean top, boolean left, boolean bottom) {
    short border = (short) ((right ? 1 : 0) << 3 | (top ? 1 : 0) << 2 | (left ? 1 : 0) << 1 | (bottom ? 1 : 0));
    return getImage("images/borders/border" + border + ".png");
  }
}
