package com.kingdoms.ui.images;

import processing.core.PApplet;
import processing.core.PImage;

public class Sprite {
  PImage image;

  public Sprite(PImage image) {
    this.image = image;
  }

  public void display(PApplet canvas, float x, float y) {
    canvas.image(image, x, y);
  }
}
