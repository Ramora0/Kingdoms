package com.kingdoms.world.buildings;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.troops.Soldier;
import com.kingdoms.world.troops.Troop;

import processing.core.PApplet;
import processing.data.JSONObject;

public class TrainingCamp extends Building {
  public TrainingCamp(Tile tile, Player player) {
    super(BuildingType.TRAINING_CAMP, tile, player);
  }

  @Deprecated
  public TrainingCamp() {
    super(BuildingType.TRAINING_CAMP);
  }

  @Override
  public void display(PApplet canvas) {
    canvas.fill(Colors.color(220));
    canvas.stroke(player.getColor());
    canvas.square(tile.getX(), tile.getY(), 1);
  }

  @Override
  public void doUpdate() {
    Troop troop = new Soldier(tile, player, 10);
    tile.addTroops(troop);
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
