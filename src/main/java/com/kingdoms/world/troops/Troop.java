package com.kingdoms.world.troops;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

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

  Player player;
  Tile tile;
  int count;

  public Troop(TroopType type) {
    this.type = type;
  }

  public static Troop createFromJSON(JSONObject json) {
    TroopType type = TroopType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.troopClass);
  }

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", type.toString());
    json.setInt("count", count);
    json.setJSONObject("player", player.toReferenceJSON());
    json.setJSONObject("tile", tile.toReferenceJSON());
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = TroopType.valueOf(json.getString("type"));
    count = json.getInt("count");
    player = JSONReferenceSerializable.getFromReferenceJSON(json.getJSONObject("player"), Player.class);
    tile = JSONReferenceSerializable.getFromReferenceJSON(json.getJSONObject("tile"), Tile.class);
  }
}