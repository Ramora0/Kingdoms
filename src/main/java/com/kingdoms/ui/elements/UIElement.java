package com.kingdoms.ui.elements;

import java.util.function.Supplier;

import com.kingdoms.Kingdoms;
import com.kingdoms.helpers.events.EventBus;
import com.kingdoms.helpers.ui.StableSupplier;

import processing.core.PApplet;

/**
 * Represents an element of UI. Includes x, y, width, and height managers.
 */
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

  public void setX(float x) {
    this.x = new StableSupplier<Float>(x);
  }

  public boolean staticX() {
    return x instanceof StableSupplier;
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

  public void setY(float y) {
    this.y = new StableSupplier<Float>(y);
  }

  public boolean staticY() {
    return y instanceof StableSupplier;
  }

  Supplier<Float> width, height;

  public float getWidth() {
    return width.get();
  }

  public void setWidth(float width) {
    this.width = new StableSupplier<Float>(width);
  }

  public float getHeight() {
    return height.get();
  }

  public void setHeight(float height) {
    this.height = new StableSupplier<Float>(height);
  }

  public void setBounds(float x, float y, float width, float height) {
    this.x = new StableSupplier<Float>(x);
    this.y = new StableSupplier<Float>(y);
    this.width = new StableSupplier<Float>(width);
    this.height = new StableSupplier<Float>(height);
  }

  // When you need to manually calculate x, y, width, and height or they dont
  // exist
  public UIElement() {
    EventBus.register(this);
  }

  // When the width and height are static and to be calculated
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

  public UIElement below(UIElement element, float margin) {
    if (element.staticY())
      y = new StableSupplier<>(element.y.get() + element.getHeight() + margin);
    else
      y = () -> element.y.get() + element.getHeight() + margin;
    setTop();
    return this;
  }

  public UIElement above(UIElement element, float margin) {
    if (element.staticY())
      y = new StableSupplier<>(element.y.get() - margin);
    else
      y = () -> element.y.get() - margin;
    setBottom();
    return this;
  }

  public UIElement rightOf(UIElement element, float margin) {
    if (element.staticX())
      x = new StableSupplier<>(element.x.get() + element.getWidth() + margin);
    else
      x = () -> element.x.get() + element.getWidth() + margin;
    setLeft();
    return this;
  }

  public UIElement leftOf(UIElement element, float margin) {
    if (element.staticX())
      x = new StableSupplier<>(element.x.get() - margin);
    else
      x = () -> element.x.get() - margin;
    setRight();
    return this;
  }

  protected void setDimensions(float width, float height, float padding) {
    this.width = new StableSupplier<>(width + 2 * padding);
    this.height = new StableSupplier<>(height + 2 * padding);
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

  public boolean isIn(float x, float y) {
    return x >= getX() && x <= getX() + width.get() && y >= getY() && y <= getY() + height.get();
  }

  public boolean shouldDie() {
    return false;
  }

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
