package com.kingdoms.ui.elements;

import java.util.function.Supplier;

import processing.core.PApplet;

public class UIDynamicText extends UILabel {
  private Supplier<String> text;

  public UIDynamicText(Supplier<String> text, float x, float y, int size) {
    super(text.get(), x, y, size);
    this.text = text;
  }

  @Override
  public String getText() {
    return text.get();
  }

  @Override
  public void display(PApplet canvas) {
    setDimensions(getText(), size);
    super.display(canvas);
  }
}
