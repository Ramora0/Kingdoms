package com.kingdoms.ui.elements;

import com.kingdoms.events.EventBus;

import processing.core.PApplet;

public abstract class UIElement {
  public UIElement() {
    EventBus.register(this);
  }

  public abstract void display(PApplet canvas);

  public void kill() {
    EventBus.unregister(this);
  }
}
