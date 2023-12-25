package com.kingdoms.world.buildings;

import com.kingdoms.ui.scenes.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;

public class City extends Building {
  public City(Tile tile, Player player) {
    super(tile, player);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(player.getColor());
    WorldDisplayScene.square(canvas, tile.getX(), tile.getY());
  }
}
