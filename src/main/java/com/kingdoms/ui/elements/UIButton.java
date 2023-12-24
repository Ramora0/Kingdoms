package com.kingdoms.ui.elements;

import com.kingdoms.events.EventBus.Subscribe;

import processing.core.PApplet;

public class UIButton extends UIElement {
  public interface UIButtonListener {
    void onClick();
  }

  private int x;
  private int y;
  private int size;
  private String text;
  private UIButtonListener listener;

  public UIButton(String text, int x, int y, int size, UIButtonListener listener) {
    super();
    this.x = x;
    this.y = y;
    this.text = text;
    this.size = size;
    this.listener = listener;
  }

  public void display(PApplet canvas) {
    canvas.textSize(size);

    float padding = 10; // padding around the text
    float textWidth = canvas.textWidth(text) + padding;
    float textHeight = canvas.textAscent() + canvas.textDescent() + padding;

    float textX = x;
    float textY = y;

    // Draw the rectangle around the text
    canvas.fill(255);
    canvas.rect(x - textWidth / 2, y - textHeight / 2, textWidth, textHeight);

    // Draw the text
    canvas.fill(0);
    canvas.text(text, textX, textY);
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    float padding = 10;
    float textWidth = canvas.textWidth(text) + padding;
    float textHeight = canvas.textAscent() + canvas.textDescent() + padding;

    float left = x - textWidth / 2;
    float right = x + textWidth / 2;
    float top = y - textHeight / 2;
    float bottom = y + textHeight / 2;

    if (canvas.mouseX >= left && canvas.mouseX <= right && canvas.mouseY >= top && canvas.mouseY <= bottom) {
      listener.onClick();
    }
  }
}