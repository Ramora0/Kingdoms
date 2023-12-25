package com.kingdoms.ui.elements;

public class UIText extends UILabel {
  private String text;

  public UIText(String text, int x, int y, int size) {
    super(x, y, size);
    this.text = text;
  }

  @Override
  public String getText() {
    return text;
  }
}