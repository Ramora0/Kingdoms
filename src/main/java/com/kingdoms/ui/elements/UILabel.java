package com.kingdoms.ui.elements;

import processing.core.PApplet;

public abstract class UILabel extends UIElement {
  private int x;
  private int y;
  protected float size;

  public abstract String getText();

  public UILabel(float size) {
    super();
    this.size = size;

    setDimensions(getText(), size);
  }

  public void display(PApplet canvas) {
    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(getText(), x, y);
  }
}