package com.kingdoms.ui.scenes;

import com.kingdoms.helpers.JSON;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.world.World;

import processing.core.PApplet;

public class MainScene extends Scene {
  public MainScene() {
    super();
    System.out.println(JSON.stringify(World.tiles[0][0]));
    elements.add(new UIText("Hola", 600, 50, 40));
  }

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    World.display(canvas);
    System.out.println(canvas.frameRate);
  }
}
