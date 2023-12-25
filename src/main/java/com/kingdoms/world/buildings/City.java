package com.kingdoms.world.buildings;

import com.kingdoms.ui.scenes.MainScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;

import processing.core.PApplet;

public class City extends Building {
  public City(Tile tile, Player player) {
    super(tile, player);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(player.getColor());
    float scale = MainScene.scale;
    int x = tile.getX(), y = tile.getY();
    canvas.square(scale * (x - World.WORLD_SIZE / 2) * 50, scale * (y - World.WORLD_SIZE / 2) * 50, scale * 50);
    // TODO: Abstract this display code to some sort of display class
  }

}
