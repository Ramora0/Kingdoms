package com.kingdoms.ui;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.events.EventBus;
import com.kingdoms.ui.elements.UIElement;

import processing.core.PApplet;

public abstract class Scene {
  protected List<UIElement> elements;

  public Scene() {
    EventBus.register(this);
    elements = new ArrayList<UIElement>();
  }

  public void display(PApplet canvas) {
    for (UIElement element : elements) {
      element.display(canvas);
    }
  }

  public void kill() {
    for (UIElement element : elements) {
      element.kill();
    }
    EventBus.unregister(this);
  }
}
