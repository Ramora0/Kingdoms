package com.kingdoms.world.buildings;

import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

public class City extends Building {
  public City(Tile tile, Player player) {
    super(BuildingType.CITY, tile, player);
  }

  @Deprecated
  public City() {
    super(BuildingType.CITY);
  }

  public Sprite getSprite() {
    return new Sprite(new ColorImage(player.getColor()));
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
