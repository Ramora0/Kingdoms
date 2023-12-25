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
    return 0;
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
    return 0;
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

  protected void setDimensions(float width, float height) {
    this.width = width;
    this.height = height;
  }

  protected void setDimensions(String text, float size) {
    float padding = 10;
    PApplet canvas = Kingdoms.canvas;

    canvas.textSize(size);

    float textWidth = canvas.textWidth(text) + padding;
    float textHeight = canvas.textAscent() + canvas.textDescent() + padding;

    setDimensions(textWidth, textHeight);
  }

  public boolean isInBounds(float x, float y) {
    return x >= getX() && x <= getX() + width && y >= getY() && y <= getY() + height;
  }

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
