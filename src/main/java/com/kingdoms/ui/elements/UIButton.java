package com.kingdoms.ui.elements;

import com.kingdoms.events.EventBus.Subscribe;

import processing.core.PApplet;

public class UIButton extends UIText {
  private Runnable listener;

  public UIButton(String text, float x, float y, int size, Runnable listener) {
    super(text, x, y, size);

    this.listener = listener;
  }

  @Override
  public void display(PApplet canvas) {
    float x = getX(), y = getY();

    if (isInBounds(canvas.mouseX, canvas.mouseY)) {
      float mouseIncrease = 3f;
      canvas.fill(230);
      canvas.rect(x - mouseIncrease, y - mouseIncrease, width + mouseIncrease * 2, height + mouseIncrease * 2, 10);
    } else {
      canvas.fill(255);
      canvas.rect(x, y, width, height, 10);
    }

    super.display(canvas);
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    if (isInBounds(canvas.mouseX, canvas.mouseY)) {
      listener.run();
    }
  }
}