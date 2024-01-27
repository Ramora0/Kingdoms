package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

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

  public boolean isUseless() {
    return image == null;
  }
}
