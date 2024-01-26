package com.kingdoms.world;

import com.kingdoms.ui.images.Sprite;

import processing.core.PApplet;

public abstract class WorldElement {
  Sprite sprite;
  boolean updated;

  public abstract Sprite getSprite();

  public void display(PApplet canvas, float x, float y) {
    if (sprite == null)
      sprite = getSprite();
    sprite.display(canvas, x, y);
  }

  public abstract void doUpdate();

  public void update() {
    if (updated) {
      return;
    }
    doUpdate();
    updated = true;
  }

  public void unupdate() {
    updated = false;
  }
}
