package com.kingdoms.ui.images;

import java.util.HashMap;
import java.util.Map;

import com.kingdoms.Kingdoms;

import processing.core.PImage;

public class ImageCache {
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
}
