package com.kingdoms.ui.elements;

import java.util.function.Supplier;

import com.kingdoms.Kingdoms;
import com.kingdoms.events.EventBus;
import com.kingdoms.helpers.StaticSupplier;

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

  protected Supplier<Float> x, y;

  public float getX() {
    switch (xAlignment) {
      case LEFT:
        return x.get();
      case CENTER:
        return x.get() - width / 2;
      case RIGHT:
        return x.get() - width;
    }
    return Float.NaN;
  }

  public float getY() {
    switch (yAlignment) {
      case TOP:
        return y.get();
      case CENTER:
        return y.get() - height / 2;
      case BOTTOM:
        return y.get() - height;
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

  public UIElement(Supplier<Float> x, Supplier<Float> y) {
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
    try {
      getStatic(y).set(element.y.get() + element.getHeight() + padding);
      setTop();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement above(UIElement element, float padding) {
    try {
      getStatic(y).set(element.y.get() - padding);
      setBottom();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement rightOf(UIElement element, float padding) {
    try {
      getStatic(x).set(element.x.get() + element.getWidth() + padding);
      setLeft();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement leftOf(UIElement element, float padding) {
    try {
      getStatic(x).set(element.x.get() - padding);
      setRight();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public <T> StaticSupplier<T> getStatic(Supplier<T> supplier) {
    if (supplier instanceof StaticSupplier) {
      return (StaticSupplier<T>) supplier;
    }
    throw new IllegalArgumentException("Supplier is not a StaticSupplier");
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
