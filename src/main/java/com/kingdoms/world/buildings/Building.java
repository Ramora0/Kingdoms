package com.kingdoms.world.buildings;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.WorldElement;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Building extends WorldElement implements JSONSerializable {
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

  public void display(PApplet canvas) {
    super.display(canvas, WorldDisplayScene.worldDisplayX(tile.getX()), WorldDisplayScene.worldDisplayY(tile.getY()));
  }

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
    player = Player.fromReferenceJSON(json.getJSONObject("player"));
    tile = Tile.fromReferenceJSON(json.getJSONObject("tile"));
  }

  public static Building createFromJSON(JSONObject json) {
    BuildingType type = BuildingType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.clazz);
  }

  @Override
  public String toString() {
    return type.toString();
  }
}
