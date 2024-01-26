package com.kingdoms.ui.images;

import processing.core.PApplet;

public class ColorImage extends Image {
  int color;

  public ColorImage(int color) {
    super(null); // TODO: Please use a PGraphics
    this.color = color;
  }

  @Override
  public void display(PApplet canvas, float x, float y) {
    canvas.noStroke();
    canvas.fill(color);
    canvas.rect(x, y, 16, 16);
  }
}
