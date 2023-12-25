package com.kingdoms.ui.scenes;

import com.kingdoms.helpers.math.Vector;
import com.kingdoms.network.Network;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.world.World;

import processing.core.PApplet;

public class MainScene extends Scene {
  public MainScene() {
    super();
    elements.add(new UIButton("Next Turn", 100, 50, 40, () -> Network.network.nextTurn()));
  }

  @Override
  public void display(PApplet canvas) {
    displayWorld(canvas);
    super.display(canvas);
  }

  // Static world display
  static Vector offset = new Vector(0, 0);

  public static float scale = 1;

  public static void mouseDragged(Object data) {
    PApplet canvas = (PApplet) data;
    offset.add(new Vector(canvas.mouseX - canvas.pmouseX, canvas.mouseY - canvas.pmouseY));
  }

  public static void mouseWheel(Object data) {
    scale *= Math.pow(2, ((Integer) data) / 100.0);
  }

  public static void displayWorld(PApplet canvas) {
    canvas.pushMatrix();
    canvas.translate(600, 400);
    canvas.translate((float) offset.x, (float) offset.y);

    World.display(canvas);
    canvas.popMatrix();
  }
}
