package com.kingdoms.world.buildings;

import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.ui.images.Image;
import com.kingdoms.ui.images.ImageManager;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

public class Farm extends Building {
  boolean hasHouse = true;

  public Farm(Tile tile, Player player) {
    super(BuildingType.FARM, tile, player);
    if (World.hasAdjacentTile(tile.getX(), tile.getY(),
        (other) -> other.hasBuilding() && other.getBuilding().getType() == BuildingType.FARM
            && other.getBuilding().getPlayer() == player))
      hasHouse = MathUtils.chance(0.7);
  }

  @Deprecated
  public Farm() {
    super(BuildingType.FARM);
  }

  public Sprite getSprite() {
    return new Sprite(new Image(hasHouse
        ? ImageManager.getTeamImage("images/buildings/farmHouse.png", player.getColor())
        : ImageManager.getImage("images/buildings/farm.png")));
  }

  @Override
  public void doUpdate() {
    player.addResources(100);
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setBoolean("hasHouse", hasHouse);
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
    hasHouse = json.getBoolean("hasHouse");
  }
}
