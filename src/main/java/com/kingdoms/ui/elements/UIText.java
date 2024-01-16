package com.kingdoms.ui.elements;

import java.util.function.Supplier;

import com.kingdoms.helpers.StaticSupplier;

import processing.core.PApplet;

public class UIText extends UIElement { // TODO: Really should have a box around it
  protected float padding = 7;
  protected float size;

  Supplier<String> text;

  public UIText(String text, float x, float y, float size) {
    super(new StaticSupplier<>(x), new StaticSupplier<>(y));
    this.text = new StaticSupplier<>(text);
    this.size = size;

    setDimensions(text, size);
  }

  public UIText(Supplier<String> text, float x, float y, float size) {
    super(new StaticSupplier<>(x), new StaticSupplier<>(y));
    this.text = text;
    this.size = size;

    this.width = () -> getWidth(text.get(), size, padding);
    this.height = new StaticSupplier<Float>(getHeight(size, padding));
  }

  public UIText(String text, Supplier<Float> x, Supplier<Float> y, float size) {
    super(x, y);
    this.text = new StaticSupplier<>(text);
    this.size = size;

    setDimensions(text, size);
  }

  public void setPadding(float padding) {
    this.padding = padding;
  }

  public void setDimensions(String text, float size) {
    setDimensions(text, size, padding);
  }

  public void display(PApplet canvas) {
    float x = getX(), y = getY();

    canvas.textSize(size);
    canvas.fill(0);
    canvas.text(text.get(), x + width.get() / 2, y + height.get() / 2 - padding);
  }
}