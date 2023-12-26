package com.kingdoms.world.troops;

import processing.data.JSONObject;

public class Soldier extends Troop {
  Soldier() {
    super(TroopType.SOLDIER);
  }

  @Override
  public JSONObject toJSON() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'toJSON'");
  }

  @Override
  public void fromJSON(JSONObject json) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'fromJSON'");
  }
}
