package com.kingdoms.world.buildings;

import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.ui.images.ImageManager;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

public class Farm extends Building {
  public Farm(Tile tile, Player player) {
    super(BuildingType.FARM, tile, player);
  }

  @Deprecated
  public Farm() {
    super(BuildingType.FARM);
  }

  public void initSprite() {
    sprite = new Sprite(ImageManager.getImage("images/farm" + MathUtils.random(new int[] { 0, 1 }) + ".png"));
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
