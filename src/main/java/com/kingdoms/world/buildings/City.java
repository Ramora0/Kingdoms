package com.kingdoms.world.buildings;

import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public class City extends Building {
  public City(Tile tile, Player player) {
    super(BuildingType.CITY, tile, player);
  }

  @Deprecated
  public City() {
    super(BuildingType.CITY);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(player.getColor());
    WorldDisplayScene.square(canvas, tile.getX(), tile.getY());
  }

  @Override
  public void doUpdate() {
  }

  @Override
  public JSONObject toJSON() {
    return super.mainToJSON();
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
  }
}
