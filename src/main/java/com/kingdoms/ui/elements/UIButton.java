package com.kingdoms.ui.elements;

import com.kingdoms.events.EventBus.Subscribe;

import processing.core.PApplet;

public class UIButton extends UIElement {
  private int size;
  private String text;
  private Runnable listener;

  public UIButton(String text, float x, float y, int size, Runnable listener) {
    super(x, y);
    this.text = text;
    this.size = size;
    this.listener = listener;

    setDimensions(text, size, 10f);
  }

  public void display(PApplet canvas) {
    canvas.textSize(size);

    float x = getX(), y = getY();

    canvas.fill(255);
    canvas.rect(x, y, width, height);

    canvas.fill(0);
    canvas.text(text, x, y);
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    if (isInBounds(canvas.mouseX, canvas.mouseY)) {
      listener.run();
    }
  }
}