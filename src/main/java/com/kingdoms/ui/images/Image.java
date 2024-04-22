package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image that can be displayed on the screen. Recursively
 * storing images to allow for transformations
 */
public class Image {
  Image image;

  public Image(PImage image) {
    this.image = new RawImage(image);
  }

  public Image(Image image) {
    this.image = image;
  }

  public Image() {
    this.image = null;
  }

  public void display(PApplet canvas, float x, float y) {
    image.display(canvas, x, y);
  }

  /**
   * Returns whether the image is null, i.e. empty borders
   */
  public boolean isUseless() {
    return image == null;
  }
}
