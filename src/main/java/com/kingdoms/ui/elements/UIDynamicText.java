package com.kingdoms.ui.elements;

import processing.core.PApplet;

public class UIDynamicText extends UILabel {
  private interface GetText {
    String text();
  }

  private GetText getText;

  public UIDynamicText(GetText getText, int size) {
    super(size);
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
