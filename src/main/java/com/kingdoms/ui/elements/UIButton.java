package com.kingdoms.ui.elements;

import com.kingdoms.events.EventBus.Subscribe;

import processing.core.PApplet;

public class UIButton extends UIElement {
  public interface UIButtonListener {
    void onClick();
  }

  private int size;
  private String text;
  private UIButtonListener listener;

  public UIButton(String text, float x, float y, int size, UIButtonListener listener) {
    super(x, y);
    this.text = text;
    this.size = size;
    this.listener = listener;

    setDimensions(text, size);
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
  public void mousePressed(PApplet canvas) { // TODO: Re-write this to use the UIElement's inBounds method
    float padding = 10;
    float textWidth = canvas.textWidth(text) + padding;
    float textHeight = canvas.textAscent() + canvas.textDescent() + padding;
    float x = getX(), y = getY();

    float left = x - textWidth / 2;
    float right = x + textWidth / 2;
    float top = y - textHeight / 2;
    float bottom = y + textHeight / 2;

    if (canvas.mouseX >= left && canvas.mouseX <= right && canvas.mouseY >= top && canvas.mouseY <= bottom) {
      listener.onClick();
    }
  }
}