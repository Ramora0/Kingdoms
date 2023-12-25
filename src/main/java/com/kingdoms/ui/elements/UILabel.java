package com.kingdoms.ui.elements;

import processing.core.PApplet;

public abstract class UILabel extends UIElement {
  private int x;
  private int y;
  private int size;

  public abstract String getText();

  public UILabel(int x, int y, int size) {
    super();
    this.x = x;
    this.y = y;
    this.size = size;
  }

  public void display(PApplet canvas) {
    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(getText(), x, y);
  }
}