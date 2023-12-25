package com.kingdoms.world;

import com.kingdoms.helpers.Colors;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.scenes.MainScene;

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

  public void display(PApplet canvas) {
    canvas.fill(isWater ? Colors.color(100, 150, 255) : Colors.color(50, 255, 50));
    float scale = MainScene.scale;
    canvas.square(scale * (x - World.WORLD_SIZE / 2) * 50, scale * (y - World.WORLD_SIZE / 2) * 50, scale * 50);
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
