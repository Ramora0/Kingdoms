package com.kingdoms.ui.scenes;

import com.kingdoms.helpers.math.Vector;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.world.World;

import processing.core.PApplet;

public abstract class WorldDisplayScene extends Scene {
  public void display(PApplet canvas) {
    displayWorld(canvas);
    super.display(canvas);
  }

  static Vector offset = new Vector(0, 0);

  public static float scale = 1;

  public static void mouseDragged(Object data) {
    if (!(UI.currentScene instanceof WorldDisplayScene)) {
      return;
    }

    PApplet canvas = (PApplet) data;
    offset.add(new Vector(canvas.mouseX - canvas.pmouseX, canvas.mouseY - canvas.pmouseY));
  }

  public static void mouseWheel(Object data) {
    if (!(UI.currentScene instanceof WorldDisplayScene)) {
      return;
    }

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
