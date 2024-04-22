package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image with a tinted color.
 */
public class TintedImage extends Image {
  int color;

  public TintedImage(PImage image, int color) {
    super(image);
    this.color = color;
  }

  @Override
  public void display(PApplet canvas, float x, float y) {
    canvas.tint(color);
    super.display(canvas, x, y);
    canvas.noTint();
  }
}
