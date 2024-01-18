package com.kingdoms.world.buildings;

import com.kingdoms.helpers.canvas.Colors;
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
    super(BuildingType.FARM);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(Colors.color(247, 182, 62));
    canvas.stroke(player.getColor());
    canvas.square(tile.getX(), tile.getY(), 1);
  }

  @Override
  public void doUpdate() {
    player.addResources(100);
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
