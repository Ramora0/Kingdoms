package com.kingdoms.world;

import com.kingdoms.helpers.Colors;
import com.kingdoms.helpers.JSONSerializable;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Tile implements JSONSerializable {
  int x, y;
  boolean isWater;

  public Tile(int x, int y, boolean isWater) {
    this.x = x;
    this.y = y;
    this.isWater = isWater;
  }

  public Tile(JSONObject json) {
    fromJSON(json);
  }

  public void display(PApplet canvas) {
    canvas.fill(isWater ? Colors.color(100, 150, 255) : Colors.color(50, 255, 50));
    canvas.rect(x * 10, y * 10, 10, 10);
  }

  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    json.setBoolean("isWater", isWater);
    return json;
  }

  public void fromJSON(JSONObject json) {
    x = json.getInt("x");
    y = json.getInt("y");
    isWater = json.getBoolean("isWater");
  }
}
