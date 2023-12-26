package com.kingdoms.world.buildings;

import com.kingdoms.helpers.Colors;
import com.kingdoms.ui.scenes.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Farm extends Building {
  public Farm(Tile tile, Player player) {
    super(BuildingType.FARM, tile, player);
  }

  @Deprecated
  public Farm() {
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(Colors.color(247, 182, 62));
    WorldDisplayScene.square(canvas, tile.getX(), tile.getY());
    canvas.fill(player.getColor());
    WorldDisplayScene.circle(canvas, tile.getX() + 0.5, tile.getY() + 0.5, 0.2);
  }

  @Override
  public void nextTurn() {
    player.addResources(100);
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
  }
}
