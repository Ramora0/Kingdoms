package com.kingdoms.ui.elements;

import java.util.function.Supplier;

public class UIText extends UILabel {
  private String text;

  public UIText(String text, float x, float y, int size) {
    super(text, x, y, size);
    this.text = text;
  }

  public UIText(String text, Supplier<Float> x, Supplier<Float> y, int size) {
    super(text, x, y, size);
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }
}