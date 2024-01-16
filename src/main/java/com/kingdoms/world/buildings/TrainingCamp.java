package com.kingdoms.world.buildings;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;
import com.kingdoms.world.troops.Soldier;

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
    WorldDisplayScene.square(canvas, tile.getX(), tile.getY());
  }

  @Override
  public void nextTurn() {
    tile.addTroops(new Soldier(tile, World.me, 10));
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
