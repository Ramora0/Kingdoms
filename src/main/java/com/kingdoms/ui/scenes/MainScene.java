package com.kingdoms.ui.scenes;

import com.kingdoms.ui.Scene;
import com.kingdoms.world.World;

import processing.core.PApplet;

public class MainScene extends Scene {
  public MainScene() {
    super();
  }

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    World.display(canvas);
  }
}
