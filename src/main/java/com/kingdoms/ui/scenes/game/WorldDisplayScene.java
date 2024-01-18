package com.kingdoms.ui.scenes.game;

import com.kingdoms.helpers.math.Vector;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.shaders.Shaders;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;

import processing.core.PApplet;

// Represents a scene that displays the world
public abstract class WorldDisplayScene extends Scene {
  static Tile hoveredTile;
  static Vector offset = new Vector(0, 0);

  public static float scale = 4;

  public void display(PApplet canvas) {
    canvas.background(100, 150, 255);

    World.display(canvas);
    Shaders.setOffset(offset.toPVector());
    Shaders.setScale(scale);
    Shaders.applyScaleShader(canvas);

    int x = (int) coordX(canvas.mouseX), y = (int) coordY(canvas.mouseY);

    Tile on = World.getTile(x, y);
    if (on != null) {
      canvas.noFill();
      canvas.stroke(0, 0, 0);
      canvas.square(displayX(x), displayY(y), Tile.TILE_WIDTH * (float) scale);
    }

    if (on != null && on != hoveredTile) {
      if (hoveredTile != null)
        hoveredTile.removeUI();
      hoveredTile = on;
      hoveredTile.addUI();
    } else if (hoveredTile != null && on == null) {
      hoveredTile.removeUI();
      hoveredTile = null;
    }

    super.display(canvas);
  }

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

  public static float displayX(double x) {
    return (float) (scale * x + offset.x) * Tile.TILE_WIDTH;
  }

  public static float displayY(double y) {
    return (float) (scale * y + offset.y) * Tile.TILE_WIDTH;
  }

  public static double coordX(double x) {
    return (x / Tile.TILE_WIDTH - offset.x) / scale;
  }

  public static double coordY(double y) {
    return (y / Tile.TILE_WIDTH - offset.y) / scale;
  }

  public static Tile getHighlightedTile(PApplet canvas) {
    return World.getTile((int) Math.floor(coordX(canvas.mouseX)), (int) Math.floor(coordY(canvas.mouseY)));
  }

  // public static void square(PApplet canvas, double x, double y) {
  // canvas.square(displayX(x), displayY(y), (float) 1);
  // }

  // public static void circle(PApplet canvas, double x, double y, double
  // diameter) {
  // canvas.circle(displayX(x), displayY(y), (float) (scale * diameter));
  // }

  // public static void line(PApplet canvas, double x1, double y1, double x2,
  // double y2) {
  // canvas.line(displayX(x1), displayY(y1), displayX(x2), displayY(y2));
  // }
}
