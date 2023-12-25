package com.kingdoms.ui.elements;

import processing.core.PApplet;

public abstract class UILabel extends UIElement {
  protected float size;

  public abstract String getText();

  public UILabel(String tempLabel, float x, float y, float size) {
    super(x, y);
    this.size = size;

    setDimensions(tempLabel, size);
  }

  public void display(PApplet canvas) {
    float x = getX(), y = getY();

    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(getText(), x, y);
  }
}