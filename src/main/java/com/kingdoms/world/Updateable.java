package com.kingdoms.world;

public abstract class Updateable {
  boolean updated;

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
