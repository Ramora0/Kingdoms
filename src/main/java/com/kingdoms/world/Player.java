package com.kingdoms.world;

import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public class Player implements JSONSerializable {
  int color;
  int resources;

  public Player(int color) {
    this.color = color;
    resources = 500;
  }

  public Player(JSONObject json) {
    fromJSON(json);
  }

  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("color", color);
    json.setInt("resources", resources);
    return json;
  }

  public void fromJSON(JSONObject json) {
    color = json.getInt("color");
    resources = json.getInt("resources");
  }
}
