package com.kingdoms.world.buildings;

import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Building implements JSONSerializable {
  public enum BuildingType {
    CITY
  }

  BuildingType type;

  Player player;
  Tile tile;

  public void setTile(Tile tile) {
    this.tile = tile;
  }

  public Building(BuildingType type, Tile tile, Player player) {
    this.type = type;
    this.tile = tile;
    this.player = player;
  }

  @Deprecated
  public Building() {
  }

  public abstract void display(PApplet canvas);

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setString("player", player.getID());
    json.setInt("x", tile.getX());
    json.setInt("y", tile.getY());
    json.setString("type", type.toString());
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = BuildingType.valueOf(json.getString("type"));
    player = World.getPlayer(json.getString("player"));
    tile = World.tiles[json.getInt("x")][json.getInt("y")];
  }

  public static Building createFromJSON(JSONObject json) {
    BuildingType type = BuildingType.valueOf(json.getString("type"));
    switch (type) {
      case CITY:
        return JSONSerializable.createFromJSON(json, City.class);
      default:
        throw new IllegalArgumentException("Building type \"" + type + "\" is not supported");
    }
  }
}
