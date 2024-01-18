package com.kingdoms.world;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.world.buildings.Building.BuildingType;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

public class Player implements JSONSerializable, JSONReferenceSerializable<Player> {
  String id;

  public String getID() {
    return id;
  }

  int color;

  public int getColor() {
    return color;
  }

  int resources;

  public int getResources() {
    return resources;
  }

  public void addResources(int amount) {
    resources += amount;
  }

  public Player(String id, int color) {
    this.id = id;
    this.color = color;
    resources = 1000;
  }

  @Deprecated
  public Player() {
  }

  public int getCityCount() {
    int count = 0;
    for (Tile[] row : World.tiles) {
      for (Tile tile : row) {
        if (tile.getBuilding() != null && tile.getBuilding().getPlayer() == this
            && tile.getBuilding().getType() == BuildingType.CITY)
          count++;
      }
    }
    return count;
  }

  // JSON \\

  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("color", color);
    json.setInt("resources", resources);
    json.setString("id", id);
    return json;
  }

  public void fromJSON(JSONObject json) {
    color = json.getInt("color");
    resources = json.getInt("resources");
    id = json.getString("id");
  }

  @Override
  public JSONObject toReferenceJSON() {
    JSONObject json = new JSONObject();
    json.setString("id", id);
    return json;
  }

  @Override
  public Player fromReferenceJSON(JSONObject json) {
    return World.getPlayer(json.getString("id"));
  }
}
