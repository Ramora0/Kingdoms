package com.kingdoms.ui.elements;

import processing.core.PApplet;

public abstract class UILabel extends UIElement {
  protected float padding = 7;
  protected float size;

  public abstract String getText();

  public UILabel(String tempLabel, float x, float y, float size) {
    super(x, y);
    this.size = size;

    setDimensions(tempLabel, size, padding);
  }

  public void setPadding(float padding) {
    this.padding = padding;
  }

  public void setDimensions(String text, float size) {
    setDimensions(text, size, padding);
  }

  public void display(PApplet canvas) {
    float x = getX(), y = getY();

    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(getText(), x + width / 2, y + height / 2 - canvas.textDescent() + 3);
  }
}