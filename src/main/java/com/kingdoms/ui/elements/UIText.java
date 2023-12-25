package com.kingdoms.ui.elements;

public class UIText extends UILabel {
  private String text;

  public UIText(String text, float x, float y, int size) {
    super(text, x, y, size);
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }
}