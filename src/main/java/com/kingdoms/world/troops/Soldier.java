package com.kingdoms.world.troops;

import processing.data.JSONObject;

public class Soldier extends Troop {
  Soldier() {
    super(TroopType.SOLDIER);
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
