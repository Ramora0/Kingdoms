package com.kingdoms.world.buildings;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Building implements JSONSerializable {
  public enum BuildingType {
    CITY(City.class),
    FARM(Farm.class),
    TRAINING_CAMP(TrainingCamp.class),;

    public final Class<? extends Building> buildingClass;

    BuildingType(Class<? extends Building> buildingClass) {
      this.buildingClass = buildingClass;
    }
  }

  BuildingType type;

  public BuildingType getType() {
    return type;
  }

  Player player;

  public Player getPlayer() {
    return player;
  }

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
  public Building(BuildingType type) {
    this.type = type;
  }

  public void nextTurn() {
  }

  public abstract void display(PApplet canvas);

  // JSON METHODS \\

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setJSONObject("player", player.toReferenceJSON());
    json.setJSONObject("tile", tile.toReferenceJSON());
    json.setString("type", type.toString());
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = BuildingType.valueOf(json.getString("type"));
    player = JSONReferenceSerializable.getFromReferenceJSON(json.getJSONObject("player"), Player.class);
    tile = JSONReferenceSerializable.getFromReferenceJSON(json.getJSONObject("tile"), Tile.class);
  }

  public static Building createFromJSON(JSONObject json) {
    BuildingType type = BuildingType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.buildingClass);
  }
}
