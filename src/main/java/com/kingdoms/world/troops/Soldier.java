package com.kingdoms.world.troops;

import com.kingdoms.world.Player;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

public class Soldier extends Troop {
  public Soldier(Tile tile, Player player, int count) {
    super(TroopType.SOLDIER, tile, player, count);
  }

  @Deprecated
  public Soldier() {
    super();
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
