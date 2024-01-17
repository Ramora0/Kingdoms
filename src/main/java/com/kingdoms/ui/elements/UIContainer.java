package com.kingdoms.ui.elements;

import processing.core.PApplet;

public class UIContainer extends UIGroup {
  float padding;

  public UIContainer(float padding, UIElement... elements) {
    super(elements);
    this.padding = padding;
    padding(padding);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(255);
    canvas.stroke(0);
    canvas.rect(x.get(), y.get(), width.get(), height.get(), 10);
    super.display(canvas);
  }
}
