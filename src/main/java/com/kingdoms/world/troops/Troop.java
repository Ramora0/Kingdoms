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

  public TroopType getType() {
    return type;
  }

  Player player;
  Tile tile;
  int count;

  public int getCount() {
    return count;
  }

  public Troop(TroopType type, Tile tile, Player player, int count) {
    this.type = type;
    this.tile = tile;
    this.player = player;
    this.count = count;
  }

  public void absorb(Troop troop) {
    count += troop.count;
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

  @Override
  public String toString() {
    return count + " " + type.toString() + "s";
  }
}
