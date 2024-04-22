package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a raw image file. Extends Image.
 */
public class RawImage extends Image {
  PImage rawImage;

  public RawImage(PImage image) {
    super();
    rawImage = image;
  }

  @Override
  public void display(PApplet canvas, float x, float y) {
    canvas.image(rawImage, x, y);
  }
}
