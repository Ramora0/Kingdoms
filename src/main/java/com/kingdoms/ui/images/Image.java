package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

public class Image {
  PImage image;

  public Image(PImage image) {
    this.image = image;
  }

  public void display(PApplet canvas, float x, float y) {
    canvas.image(image, x, y);
  }

  public boolean isUseless() {
    return false; // TODO: This should return true if image is null, but ColorImage uses null
                  // Image :(
  }
}
