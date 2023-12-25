package com.kingdoms.ui.elements;

import com.kingdoms.Kingdoms;
import com.kingdoms.events.EventBus;

import processing.core.PApplet;

public abstract class UIElement {
  float x, y;
  float width, height;

  public UIElement() {
    EventBus.register(this);
  }

  public UIElement setLeft(float x) {
    this.x = x;
    return this;
  }

  public UIElement setTop(float y) {
    this.y = y;
    return this;
  }

  public UIElement setTopLeft(float x, float y) {
    setLeft(x);
    setTop(y);
    return this;
  }

  public UIElement setCenterX(float x) {
    this.x = x - width / 2;
    return this;
  }

  public UIElement setCenterY(float y) {
    this.y = y - height / 2;
    return this;
  }

  public UIElement setCenter(float x, float y) {
    setCenterX(x);
    setCenterY(y);
    return this;
  }

  public UIElement setRight(float x) {
    this.x = x - width;
    return this;
  }

  public UIElement setBottom(float y) {
    this.y = y - height;
    return this;
  }

  public UIElement setBottomRight(float x, float y) {
    setRight(x);
    setBottom(y);
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

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
