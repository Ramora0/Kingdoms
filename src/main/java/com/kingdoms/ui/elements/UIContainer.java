package com.kingdoms.ui.elements;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class UIContainer extends UIElement {
  List<UIElement> children;
  float padding;

  public UIContainer(float padding, UIElement... elements) {
    super(() -> 0f, () -> 0f);
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

    this.children = new ArrayList<>();
    for (UIElement element : elements) {
      this.children.add(element);
    }
  }

  @Override
  public void display(PApplet canvas) {
    throw new UnsupportedOperationException("Unimplemented method 'display'");
  }

}
