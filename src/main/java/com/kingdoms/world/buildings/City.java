package com.kingdoms.world.buildings;

import com.kingdoms.ui.images.ColorSprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.data.JSONObject;

public class City extends Building {
  public City(Tile tile, Player player) {
    super(BuildingType.CITY, tile, player);
  }

  @Deprecated
  public City() {
    super(BuildingType.CITY);
  }

  public void init() {
    sprite = new ColorSprite(player.getColor());
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
