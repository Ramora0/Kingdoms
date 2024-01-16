package com.kingdoms.ui.elements;

import com.kingdoms.Kingdoms;
import com.kingdoms.events.EventBus;

import processing.core.PApplet;

public abstract class UIElement {
  public enum XAlignment {
    LEFT, CENTER, RIGHT
  };

  XAlignment xAlignment = XAlignment.CENTER;

  enum YAlignment {
    TOP, CENTER, BOTTOM
  }

  YAlignment yAlignment = YAlignment.CENTER;

  private float x, y;
  float width, height;

  public float getX() {
    switch (xAlignment) {
      case LEFT:
        return x;
      case CENTER:
        return x - width / 2;
      case RIGHT:
        return x - width;
    }
    return Float.NaN;
  }

  public float getY() {
    switch (yAlignment) {
      case TOP:
        return y;
      case CENTER:
        return y - height / 2;
      case BOTTOM:
        return y - height;
    }
    return Float.NaN;
  }

  public UIElement(float x, float y) {
    this.x = x;
    this.y = y;
    EventBus.register(this);
  }

  public UIElement setLeft() {
    xAlignment = XAlignment.LEFT;
    return this;
  }

  public UIElement setTop() {
    yAlignment = YAlignment.TOP;
    return this;
  }

  public UIElement setTopLeft() {
    setLeft();
    setTop();
    return this;
  }

  public UIElement setRight() {
    xAlignment = XAlignment.RIGHT;
    return this;
  }

  public UIElement setBottom() {
    yAlignment = YAlignment.BOTTOM;
    return this;
  }

  public UIElement setBottomRight() {
    setRight();
    setBottom();
    return this;
  }

  public UIElement below(UIElement element, float padding) {
    y = element.getY() + element.height + padding;
    setTop();
    return this;
  }

  public UIElement above(UIElement element, float padding) {
    y = element.getY() - padding;
    setBottom();
    return this;
  }

  public UIElement left(UIElement element, float padding) {
    x = element.getX() - padding;
    setRight();
    return this;
  }

  public UIElement right(UIElement element, float padding) {
    x = element.getX() + element.width + padding;
    setLeft();
    return this;
  }

  protected void setDimensions(float width, float height, float padding) {
    this.width = width + 2 * padding;
    this.height = height + 2 * padding;
  }

  protected void setDimensions(String text, float size, float padding) {
    PApplet canvas = Kingdoms.canvas;

    canvas.textSize(size);

    float textWidth = canvas.textWidth(text);
    float textHeight = canvas.textAscent() + canvas.textDescent();

    setDimensions(textWidth, textHeight, padding);
  }

  public boolean isInBounds(float x, float y) {
    return x >= getX() && x <= getX() + width && y >= getY() && y <= getY() + height;
  }

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
