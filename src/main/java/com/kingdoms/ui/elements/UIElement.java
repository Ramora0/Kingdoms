package com.kingdoms.ui.elements;

import com.kingdoms.Kingdoms;
import com.kingdoms.events.EventBus;
import com.kingdoms.ui.elements.managers.Managers.PositionManager;

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

  protected PositionManager position;

  public float getX() {
    switch (xAlignment) {
      case LEFT:
        return position.baseGetX();
      case CENTER:
        return position.baseGetX() - width / 2;
      case RIGHT:
        return position.baseGetX() - width;
    }
    return Float.NaN;
  }

  public float getY() {
    switch (yAlignment) {
      case TOP:
        return position.baseGetY();
      case CENTER:
        return position.baseGetY() - height / 2;
      case BOTTOM:
        return position.baseGetY() - height;
    }
    return Float.NaN;
  }

  float width, height;

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public UIElement(PositionManager position) {
    this.position = position;
    
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
