package com.kingdoms.world;

import com.kingdoms.helpers.Colors;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.scenes.WorldDisplayScene;
import com.kingdoms.world.buildings.Building;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Tile implements JSONSerializable {
  Building building;

  public boolean hasBuilding() {
    return building != null;
  }

  public void build(Building building) {
    if (hasBuilding())
      throw new RuntimeException("Tile (" + x + ", " + y + ") already has a building!");
    this.building = building;
  }

  int x, y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  boolean isWater;

  public Tile(int x, int y, boolean isWater) {
    this.x = x;
    this.y = y;
    this.isWater = isWater;
  }

  @Deprecated
  public Tile() {
  }

  public boolean isLand() {
    return !isWater;
  }

  public void display(PApplet canvas) {
    canvas.fill(isWater ? Colors.color(100, 150, 255) : Colors.color(50, 255, 50));
    WorldDisplayScene.square(canvas, x, y);

    if (hasBuilding()) {
      building.display(canvas);
    }
  }

  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    json.setBoolean("isWater", isWater);
    if (hasBuilding()) {
      json.setJSONObject("building", building.toJSON());
    }
    return json;
  }

  public void fromJSON(JSONObject json) {
    x = json.getInt("x");
    y = json.getInt("y");
    isWater = json.getBoolean("isWater");
    if (json.hasKey("building")) {
      building = Building.createFromJSON(json.getJSONObject("building"));
      building.setTile(this);
    }
  }
}
