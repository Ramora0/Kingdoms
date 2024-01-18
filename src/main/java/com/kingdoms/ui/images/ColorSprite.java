package com.kingdoms.ui.images;

import processing.core.PApplet;

public class ColorSprite extends Sprite {
  int color;

  public ColorSprite(int color) {
    super(null);
    this.color = color;
  }

  @Override
  public void display(PApplet canvas, float x, float y) {
    canvas.noStroke();
    canvas.fill(color);
    canvas.rect(x, y, 16, 16);
  }
}
