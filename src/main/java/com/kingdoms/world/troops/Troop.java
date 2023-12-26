package com.kingdoms.world.troops;

import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public abstract class Troop implements JSONSerializable {

  public enum TroopType {
    SOLDIER(Soldier.class);

    public final Class<? extends Troop> troopClass;

    TroopType(Class<? extends Troop> troopClass) {
      this.troopClass = troopClass;
    }
  }

  TroopType type;

  public Troop(TroopType type) {
    this.type = type;
  }

  public static Troop createFromJSON(JSONObject json) {
    TroopType type = TroopType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.troopClass);
  }
}
