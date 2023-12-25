package com.kingdoms.network;

import com.kingdoms.helpers.json.JSON;
import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public class Instruction implements JSONSerializable {
  public String name;
  public JSONObject data;

  public Instruction(String name, JSONObject data) {
    this.name = name;
    this.data = data;
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setString("name", name);
    json.setJSONObject("data", JSON.parse(JSON.stringify(data)));
    return json;
  }

  public void fromJSON(JSONObject json) {
    name = json.getString("name");
    data = json.getJSONObject("data");
  }
}
