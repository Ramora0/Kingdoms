package com.kingdoms.world.buildings;

import com.kingdoms.ui.images.ImageCache;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.data.JSONObject;

public class Farm extends Building {
  public Farm(Tile tile, Player player) {
    super(BuildingType.FARM, tile, player);
  }

  @Deprecated
  public Farm() {
    super(BuildingType.FARM);
  }

  public void init() {
    sprite = new Sprite(ImageCache.getImage("images/farm1.png"));
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
