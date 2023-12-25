package com.kingdoms.ui.elements;

public class UIDynamicText extends UILabel {
  private interface GetText {
    String text();
  }

  private GetText getText;

  public UIDynamicText(GetText getText, int x, int y, int size) {
    super(x, y, size);
    this.getText = getText;
  }

  @Override
  public String getText() {
    return getText.text();
  }
}
