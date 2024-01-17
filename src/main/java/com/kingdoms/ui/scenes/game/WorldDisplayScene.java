package com.kingdoms.ui.scenes.game;

import com.kingdoms.helpers.math.Vector;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;

import processing.core.PApplet;

// Represents a scene that displays the world
public abstract class WorldDisplayScene extends Scene {
  static Tile hoveredTile;
  static Vector offset = new Vector(0, 0);

  public static float scale = 50;

  public void display(PApplet canvas) {
    int x = (int) coordX(canvas.mouseX), y = (int) coordY(canvas.mouseY);

    Tile on = World.getTile(x, y);
    if (on != null && on != hoveredTile) {
      if (hoveredTile != null)
        hoveredTile.removeUI();
      hoveredTile = on;
      hoveredTile.addUI();
    } else if (hoveredTile != null && on == null) {
      hoveredTile.removeUI();
      hoveredTile = null;
    }

    World.display(canvas);
    super.display(canvas);
  }

  public static void mouseDragged(Object data) {
    if (!(UI.currentScene instanceof WorldDisplayScene)) {
      return;
    }

    PApplet canvas = (PApplet) data;
    offset.add(new Vector(canvas.mouseX - canvas.pmouseX, canvas.mouseY - canvas.pmouseY).div(scale));
  }

  public static void mouseWheel(Object data) {
    if (!(UI.currentScene instanceof WorldDisplayScene)) {
      return;
    }

    scale *= Math.pow(2, ((Integer) data) / 100.0);
  }

  /** Takes a world x coordinate and converts it to a canvas x coordinate */
  public static float displayX(double x) {
    return (float) (scale * x + offset.x * scale + 600);
  }

  /** Takes a world y coordinate and converts it to a canvas y coordinate */
  public static float displayY(double y) {
    return (float) (scale * y + offset.y * scale + 400);
  }

  /** Takes a canvas x coordinate and converts it to a world x coordinate */
  public static double coordX(float x) {
    return (x - offset.x * scale - 600) / scale;
  }

  /** Takes a canvas y coordinate and converts it to a world y coordinate */
  public static double coordY(float y) {
    return (y - offset.y * scale - 400) / scale;
  }

  public static Tile getHighlightedTile(PApplet canvas) {
    return World.getTile((int) Math.floor(coordX(canvas.mouseX)), (int) Math.floor(coordY(canvas.mouseY)));
  }

  public static void square(PApplet canvas, double x, double y) {
    canvas.strokeWeight(scale / 100);
    canvas.square(displayX(x), displayY(y), (float) scale);
  }

  public static void circle(PApplet canvas, double x, double y, double diameter) {
    canvas.circle(displayX(x), displayY(y), (float) (scale * diameter));
  }

  public static void line(PApplet canvas, double x1, double y1, double x2, double y2) {
    canvas.line(displayX(x1), displayY(y1), displayX(x2), displayY(y2));
  }
}
