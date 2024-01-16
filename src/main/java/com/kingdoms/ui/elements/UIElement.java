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
        return x.get() - width.get() / 2;
      case RIGHT:
        return x.get() - width.get();
    }
    return Float.NaN;
  }

  public float getY() {
    switch (yAlignment) {
      case TOP:
        return y.get();
      case CENTER:
        return y.get() - height.get() / 2;
      case BOTTOM:
        return y.get() - height.get();
    }
    return Float.NaN;
  }

  Supplier<Float> width, height;

  public float getWidth() {
    return width.get();
  }

  public float getHeight() {
    return height.get();
  }

  // When you need to manually calculate x, y, width, and height
  public UIElement() {
    this.x = new StaticSupplier<>(Float.NaN);
    this.y = new StaticSupplier<>(Float.NaN);
    this.width = new StaticSupplier<>(Float.NaN);
    this.height = new StaticSupplier<>(Float.NaN);

    EventBus.register(this);
  }

  // When the width and height are static and to be calculated
  public UIElement(Supplier<Float> x, Supplier<Float> y) {
    this.x = x;
    this.y = y;
    this.width = new StaticSupplier<>(Float.NaN);
    this.height = new StaticSupplier<>(Float.NaN);

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

  public UIElement below(UIElement element, float margin) {
    try {
      getStatic(y).set(element.y.get() + element.getHeight() + margin);
      setTop();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement above(UIElement element, float margin) {
    try {
      getStatic(y).set(element.y.get() - margin);
      setBottom();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement rightOf(UIElement element, float margin) {
    try {
      getStatic(x).set(element.x.get() + element.getWidth() + margin);
      setLeft();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  public UIElement leftOf(UIElement element, float margin) {
    try {
      getStatic(x).set(element.x.get() - margin);
      setRight();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  protected void setDimensions(float width, float height, float padding) {
    try {
      this.width = new StaticSupplier<>(width + 2 * padding);
      this.height = new StaticSupplier<>(height + 2 * padding);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void setDimensions(String text, float size, float padding) {
    PApplet canvas = Kingdoms.canvas;

    canvas.textSize(size);

    float textWidth = canvas.textWidth(text);
    float textHeight = canvas.textAscent();

    setDimensions(textWidth, textHeight, padding);
  }

  protected float getWidth(String text, float size, float padding) {
    PApplet canvas = Kingdoms.canvas;
    canvas.textSize(size);

    float textWidth = canvas.textWidth(text);
    return textWidth + 2 * padding;
  }

  protected float getHeight(float size, float padding) {
    PApplet canvas = Kingdoms.canvas;
    canvas.textSize(size);

    float textHeight = canvas.textAscent();
    return textHeight + 2 * padding;
  }

  public <T> StaticSupplier<T> getStatic(Supplier<T> supplier) {
    if (supplier instanceof StaticSupplier) {
      return (StaticSupplier<T>) supplier;
    }
    throw new IllegalArgumentException("Supplier is not a StaticSupplier");
  }

  public boolean isInBounds(float x, float y) {
    return x >= getX() && x <= getX() + width.get() && y >= getY() && y <= getY() + height.get();
  }

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
