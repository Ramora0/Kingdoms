package com.kingdoms.ui.elements;

import com.kingdoms.helpers.events.EventBus.Subscribe;

import processing.core.PApplet;

public class UIButton extends UIText {
  private Runnable onClick;

  public UIButton(String text, float x, float y, int size, Runnable onClick) {
    super(text, x, y, size);

    this.onClick = onClick;
  }

  public UIButton disable() {
    onClick = null;
    return this;
  }

  @Override
  public void display(PApplet canvas) {
    float x = getX(), y = getY();

    if (onClick == null) {
      canvas.fill(230);
      canvas.rect(x, y, width.get(), height.get(), 10);
    } else {
      if (isInBounds(canvas.mouseX, canvas.mouseY)) {
        float mouseIncrease = 3f;
        canvas.fill(230);
        canvas.rect(x - mouseIncrease, y - mouseIncrease, width.get() + mouseIncrease * 2,
            height.get() + mouseIncrease * 2, 10);
      } else {
        canvas.fill(255);
        canvas.rect(x, y, width.get(), height.get(), 10);
      }
    }

    super.display(canvas);
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    if (isInBounds(canvas.mouseX, canvas.mouseY) && onClick != null) {
      onClick.run();
    }
  }
}