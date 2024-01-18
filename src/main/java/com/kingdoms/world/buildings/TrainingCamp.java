package com.kingdoms.world.buildings;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.images.ColorSprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.troops.Soldier;
import com.kingdoms.world.troops.Troop;

import processing.data.JSONObject;

public class TrainingCamp extends Building {
  public TrainingCamp(Tile tile, Player player) {
    super(BuildingType.TRAINING_CAMP, tile, player);
  }

  @Deprecated
  public TrainingCamp() {
    super(BuildingType.TRAINING_CAMP);
  }

  public void init() {
    // sprite = new Sprite(ImageCache.getImage("images/farm1"));
    sprite = new ColorSprite(Colors.color(150));
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
