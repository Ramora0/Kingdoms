package com.kingdoms.world;

import java.util.List;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.buildings.Building;
import com.kingdoms.world.troops.Troop;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Tile implements JSONSerializable, JSONReferenceSerializable<Tile> {
  List<Troop> troops;

  public Tile(int x, int y, boolean isWater) {
    this.x = x;
    this.y = y;
    this.isWater = isWater;
  }

  // HELPER \\

  @Deprecated
  public Tile() {
  }

  int x, y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  boolean isWater;

  public boolean isLand() {
    return !isWater;
  }

  Building building;

  public Building getBuilding() {
    return building;
  }

  public boolean hasBuilding() {
    return building != null;
  }

  // GAMEPLAY \\

  public void nextTurn() {
    if (hasBuilding()) {
      building.nextTurn();
    }
  }

  // UI \\

  public void display(PApplet canvas) {
    canvas.stroke(0);
    canvas.fill(isWater ? Colors.color(100, 150, 255) : Colors.color(50, 255, 50));
    WorldDisplayScene.square(canvas, x, y);

    if (hasBuilding()) {
      building.display(canvas);
    }
  }

  UIContainer container;

  public void addUI() {
    UIText biomeText = (UIText) new UIText(isWater ? "Water" : "Land",
        () -> WorldDisplayScene.displayX(x + 0.5), () -> WorldDisplayScene.displayY(y), 20);

    container = new UIContainer(0, biomeText);

    if (hasBuilding()) {
      UIText buildingText = (UIText) new UIText(building.getType().toString(),
          () -> WorldDisplayScene.displayX(x + 0.5), () -> WorldDisplayScene.displayY(y),
          20).above(biomeText, 5f);
      container.addChild(buildingText);
    }

    UI.currentScene.addElement(container);
  }

  public void removeUI() {
    UI.currentScene.removeElement(container);
    container = null;
  }

  // WRITE FUNCTIONS \\

  @Deprecated
  public void build(Building building) {
    if (hasBuilding())
      throw new RuntimeException("Tile (" + x + ", " + y + ") already has a building!");
    this.building = building;
  }

  // JSON FUNCTIONS \\

  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    json.setBoolean("isWater", isWater);
    if (hasBuilding()) {
      json.setJSONObject("building", building.toJSON());
    }
    // Save list of troops
    json.setJSONArray("troops", JSONSerializable.toJSONArray(troops));
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    x = json.getInt("x");
    y = json.getInt("y");
    isWater = json.getBoolean("isWater");
    if (json.hasKey("building")) {
      building = Building.createFromJSON(json.getJSONObject("building"));
      building.setTile(this);
    }
    troops = JSONSerializable.createFromJSONArray(json.getJSONArray("troops"), Troop.class);
  }

  @Override
  public JSONObject toReferenceJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    return json;
  }

  @Override
  public Tile fromReferenceJSON(JSONObject json) {
    int x = json.getInt("x"), y = json.getInt("y");
    return World.tiles[x][y];
  }
}
