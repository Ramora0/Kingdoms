package com.kingdoms.ui.elements;

import processing.core.PApplet;

public class UIDynamicText extends UILabel {
  public interface GetText {
    String text();
  }

  private GetText getText;

  public UIDynamicText(GetText getText, float x, float y, int size) {
    super(getText.text(), x, y, size);
    this.getText = getText;
  }

  @Override
  public String getText() {
    return getText.text();
  }

  @Override
  public void display(PApplet canvas) {
    setDimensions(getText(), size);
    super.display(canvas);
  }
}
