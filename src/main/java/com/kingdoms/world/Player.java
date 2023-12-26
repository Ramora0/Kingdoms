package com.kingdoms.world;

import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public class Player implements JSONSerializable {
  String id;
  int color;
  int resources;

  public Player(String id, int color) {
    this.id = id;
    this.color = color;
    resources = 1000;
  }

  @Deprecated
  public Player() {
  }

  public int getColor() {
    return color;
  }

  public int getResources() {
    return resources;
  }

  public void addResources(int amount) {
    resources += amount;
  }

  public String getID() {
    return id;
  }

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
}
