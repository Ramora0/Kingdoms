package com.kingdoms.ui.elements;

import processing.core.PApplet;

public class UIContainer extends UIGroup {
  float padding;

  public UIContainer(float padding, UIElement... elements) {
    super(elements);
    this.padding = padding;

    x = () -> {
      float minX = Float.MAX_VALUE;
      for (UIElement element : children) {
        minX = Math.min(minX, element.getX());
      }
      return minX - padding;
    };
    y = () -> {
      float minY = Float.MAX_VALUE;
      for (UIElement element : children) {
        minY = Math.min(minY, element.getY());
      }
      return minY - padding;
    };
    width = () -> {
      float maxX = Float.MIN_VALUE;
      for (UIElement element : children) {
        maxX = Math.max(maxX, element.getX() + element.getWidth());
      }
      return maxX - x.get() + padding;
    };
    height = () -> {
      float maxY = Float.MIN_VALUE;
      for (UIElement element : children) {
        maxY = Math.max(maxY, element.getY() + element.getHeight());
      }
      return maxY - y.get() + padding;
    };
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(255);
    canvas.rect(x.get(), y.get(), width.get(), height.get(), 10);
    super.display(canvas);
  }
}
