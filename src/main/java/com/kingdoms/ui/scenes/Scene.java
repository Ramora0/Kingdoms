package com.kingdoms.ui.scenes;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.events.EventBus;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIElement;

import processing.core.PApplet;

/**
 * Represents a scene, which is a collection of UIElements to display
 */
public abstract class Scene {
  private List<UIElement> elements;

  public Scene() {
    EventBus.register(this);
    elements = new ArrayList<UIElement>();
  }

  public void display(PApplet canvas) {
    canvas.strokeWeight(1);
    for (int i = 0; i < elements.size(); i++) {
      UIElement element = elements.get(i);
      element.display(canvas);
      if (element.shouldDie()) {
        element.kill();
        // This could be an issue because it is removing more than one element, however
        // since the elements of a container are generally after the container itself,
        // this shouldn't be a problem
        removeElement(element);
        i--;
      }
    }
  }

  public void addElement(UIElement element) {
    if (element instanceof UIContainer) {
      addContainer((UIContainer) element);
      return;
    }

    elements.add(element);
  }

  public void addContainer(UIContainer container) {
    elements.add(container);
    elements.addAll(container.getChildren());
  }

  public void removeElement(UIElement element) {
    if (element instanceof UIContainer) {
      removeContainer((UIContainer) element);
      return;
    }

    element.kill();
    elements.remove(element);
  }

  public void removeContainer(UIContainer element) {
    element.kill();
    elements.remove(element);
    for (UIElement child : element.getChildren()) {
      child.kill(); // this should probs be here
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
