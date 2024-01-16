package com.kingdoms.ui.elements.managers.position;

import com.kingdoms.ui.elements.UIElement;
import com.kingdoms.ui.elements.managers.Managers.PositionManager;

public class StaticPositionManager implements PositionManager {
  UIElement of;
  float x, y;

  public StaticPositionManager(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public float baseGetX() {
    return x;
  }

  @Override
  public float baseGetY() {
    return y;
  }

  public void below(UIElement element, float padding) {
    y = element.getY() + element.getHeight() + padding;
    of.setTop();
  }

  public void above(UIElement element, float padding) {
    y = element.getY() - padding;
    of.setBottom();
  }

  public void left(UIElement element, float padding) {
    x = element.getX() - padding;
    of.setRight();
  }

  public void right(UIElement element, float padding) {
    x = element.getX() + element.getWidth() + padding;
    of.setLeft();
  }
}
