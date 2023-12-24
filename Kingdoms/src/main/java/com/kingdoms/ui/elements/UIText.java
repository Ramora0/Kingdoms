package com.kingdoms.ui.elements;

import processing.core.PApplet;

public class UIText extends UIElement {
  private int x;
  private int y;
  private int size;
  private String text;

  public UIText(String text, int x, int y, int size) {
    super();
    this.x = x;
    this.y = y;
    this.text = text;
    this.size = size;
  }

  public void display(PApplet canvas) {
    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(text, x, y);
  }
}