package com.kingdoms.ui.elements;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class UIGroup extends UIElement {
  List<UIElement> children;

  public List<UIElement> getChildren() {
    return children;
  }

  public void addChild(UIElement element) {
    children.add(element);
  }

  public UIGroup(UIElement... elements) {
    this.children = new ArrayList<>(List.of(elements));
    padding(0);
  }

  public void padding(float padding) {

    x = () -> {
      float minX = Float.MAX_VALUE;
      for (UIElement element : children) {
        minX = Math.min(minX, element.getX()) - padding;
      }
      return minX;
    };
    y = () -> {
      float minY = Float.MAX_VALUE;
      for (UIElement element : children) {
        minY = Math.min(minY, element.getY()) - padding;
      }
      return minY;
    };
    width = () -> {
      float maxX = Float.MIN_VALUE;
      for (UIElement element : children) {
        maxX = Math.max(maxX, element.getX() + element.getWidth());
      }
      return maxX - x.get() + 2 * padding;
    };
    height = () -> {
      float maxY = Float.MIN_VALUE;
      for (UIElement element : children) {
        maxY = Math.max(maxY, element.getY() + element.getHeight());
      }
      return maxY - y.get() + 2 * padding;
    };
  }

  @Override
  public void display(PApplet canvas) {
    for (UIElement element : children) {
      element.display(canvas);
    }
  }

  @Override
  public void kill() {
    for (UIElement element : children) {
      element.kill();
    }
    super.kill();
  }
}
