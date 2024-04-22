package com.kingdoms.world;

import com.kingdoms.ui.images.Sprite;

import processing.core.PApplet;

/**
 * Represents anything in the world with a sprite and update method; includes
 * buildings, troops, tiles, etc.
 * 
 */
public abstract class WorldElement {
  Sprite sprite;
  boolean updated;

  public abstract Sprite getSprite();

  public void display(PApplet canvas, float x, float y) {
    if (sprite == null)
      sprite = getSprite();
    sprite.display(canvas, x, y);
  }

  protected abstract void _update();

  public void update() {
    if (updated) {
      return;
    }
    _update();
    updated = true;
  }

  public void unupdate() {
    updated = false;
  }
}
