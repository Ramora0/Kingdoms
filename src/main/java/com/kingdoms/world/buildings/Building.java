package com.kingdoms.world.buildings;

import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;

public abstract class Building {
  Player player;
  Tile tile;

  public Building(Tile tile, Player player) {
    this.tile = tile;
    this.player = player;
  }

  public abstract void display(PApplet canvas);
}
