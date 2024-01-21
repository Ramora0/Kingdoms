package com.kingdoms.ui.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kingdoms.helpers.events.EventBus;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIElement;

import processing.core.PApplet;

public abstract class Scene {
  private List<UIElement> elements;

  public Scene() {
    EventBus.register(this);
    elements = new ArrayList<UIElement>();
  }

  public void display(PApplet canvas) {
    canvas.strokeWeight(1);
    Iterator<UIElement> iterator = elements.iterator();
    while (iterator.hasNext()) {
      UIElement element = iterator.next();
      if (element.shouldDie()) {
        iterator.remove();
      } else {
        element.display(canvas);
      }
    }
  }

  public void addElement(UIElement element) {
    elements.add(element);
  }

  public void addElement(UIContainer container) {
    elements.add(container);
    elements.addAll(container.getChildren());
  }

  public void removeElement(UIElement element) {
    element.kill();
    elements.remove(element);
  }

  public void removeElement(UIContainer element) {
    element.kill();
    elements.remove(element);
    for (UIElement child : element.getChildren()) {
      elements.remove(child);
    }
  }

  public void kill() {
    for (UIElement element : elements) {
      element.kill();
    }
    EventBus.unregister(this);
  }
}
