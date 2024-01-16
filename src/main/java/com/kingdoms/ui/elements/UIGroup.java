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
