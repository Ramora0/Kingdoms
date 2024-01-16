package com.kingdoms.ui.scenes;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.events.EventBus;
import com.kingdoms.ui.elements.UIElement;

import processing.core.PApplet;

public abstract class Scene {
  private List<UIElement> elements;

  public Scene() {
    EventBus.register(this);
    elements = new ArrayList<UIElement>();
  }

  public void display(PApplet canvas) {
    for (UIElement element : elements) {
      element.display(canvas);
    }
  }

  public void addElement(UIElement element) {
    elements.add(element);
  }

  public void removeElement(UIElement element) {
    element.kill();
    elements.remove(element);
  }

  public void kill() {
    for (UIElement element : elements) {
      element.kill();
    }
    EventBus.unregister(this);
  }
}
