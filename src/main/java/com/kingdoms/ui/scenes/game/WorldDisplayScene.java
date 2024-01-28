package com.kingdoms.ui.scenes.game;

import com.kingdoms.Kingdoms;
import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.math.Vector;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.shaders.Shaders;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;

// Represents a scene that displays the world
public abstract class WorldDisplayScene extends Scene {
  static Tile hoveredTile;
  static Vector offset = new Vector(0, 0);

  public static float scale = 4;

  public void display(PApplet canvas) {
    canvas.background(Colors.SHALLOW_WATER);

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

    float oldScale = scale;
    double mx = coordX(Kingdoms.canvas.mouseX), my = coordY(Kingdoms.canvas.mouseY);
    scale *= Math.pow(2, ((Integer) data) / 50.0);
    offset.x += mx * Tile.TILE_WIDTH * (oldScale - scale);
    offset.y += my * Tile.TILE_WIDTH * (oldScale - scale);
  }

  /**
   * Converst raw world coordinate into tile scaled, pre shader display coordinate
   */
  public static float worldDisplayX(double x) {
    return (float) (x * Tile.TILE_WIDTH);
  }

  public static float worldDisplayY(double y) {
    return (float) (y * Tile.TILE_WIDTH);
  }

  /** Converts raw world coordinate to full display coordinate */
  public static float displayX(double x) {
    return (float) (scale * x * Tile.TILE_WIDTH + offset.x);
  }

  public static float displayY(double y) {
    return (float) (scale * y * Tile.TILE_WIDTH + offset.y);
  }

  public static double coordX(double x) {
    return (x - offset.x) / (scale * Tile.TILE_WIDTH);
  }

  public static double coordY(double y) {
    return (y - offset.y) / (scale * Tile.TILE_WIDTH);
  }

  public static Tile getHighlightedTile(PApplet canvas) {
    return World.getTile((int) Math.floor(coordX(canvas.mouseX)), (int) Math.floor(coordY(canvas.mouseY)));
  }
}
