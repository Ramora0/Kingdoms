package com.kingdoms.world.troops;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Troop implements JSONSerializable, JSONReferenceSerializable<Troop> {
  public enum TroopType {
    SOLDIER(Soldier.class);

    public final Class<? extends Troop> troopClass;

    TroopType(Class<? extends Troop> troopClass) {
      this.troopClass = troopClass;
    }
  }

  TroopType type;

  public TroopType getType() {
    return type;
  }

  Player player;

  public Player getPlayer() {
    return player;
  }

  Tile tile;

  public Tile getTile() {
    return tile;
  }

  public void setTile(Tile tile) {
    this.tile = tile;
  }

  List<Tile> path;

  public List<Tile> getPath() {
    return path;
  }

  public void setPath(List<Tile> path) {
    this.path = path;
  }

  int count;

  public int getCount() {
    return count;
  }

  public Troop(TroopType type, Tile tile, Player player, int count) {
    this.type = type;
    this.tile = tile;
    this.player = player;
    this.count = count;
    path = new ArrayList<>();
  }

  @Deprecated
  public Troop() {
  }

  public void absorb(Troop troop) {
    count += troop.count;
  }

  public void nextTurn() {
    if (path != null && path.size() > 0) {
      Tile.move(this, tile, path.get(0));
      tile = path.get(0);
      path.remove(0);
    }
  }

  public void display(PApplet canvas) {
    canvas.fill(player.getColor());
    WorldDisplayScene.circle(canvas, tile.getX() + 0.5f, tile.getY() + 0.5f, Math.sqrt(count) / 10);
  }

  // JSON \\
  /** Helper method to load from JSON when you don't know the type of troop */
  public static Troop createFromJSON(JSONObject json) {
    TroopType type = TroopType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.troopClass);
  }

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", type.toString());
    json.setInt("count", count);
    json.setJSONObject("player", player.toReferenceJSON());
    json.setJSONObject("tile", tile.toReferenceJSON());
    json.setJSONArray("path", JSONReferenceSerializable.toJSONArray(path));
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = TroopType.valueOf(json.getString("type"));
    count = json.getInt("count");
    player = JSONReferenceSerializable.getFromJSON(json.getJSONObject("player"), Player.class);
    tile = JSONReferenceSerializable.getFromJSON(json.getJSONObject("tile"), Tile.class);
    if (tile == null) {
      System.out.println("God dammit why is tile null");
    }
    path = JSONReferenceSerializable.getFromJSONArray(json.getJSONArray("path"), Tile.class);
  }

  public JSONObject toReferenceJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", type.toString());
    json.setJSONObject("tile", tile.toReferenceJSON());
    return json;
  }

  public Troop fromReferenceJSON(JSONObject json) {
    type = TroopType.valueOf(json.getString("type"));
    List<Troop> troops = JSONReferenceSerializable.getFromJSON(json.getJSONObject("tile"), Tile.class).getTroops();
    for (Troop troop : troops) {
      if (troop.getType() == type) {
        return troop;
      }
    }
    throw new RuntimeException(
        "Troop of type " + type.toString() + " not found on tile " + json.getJSONObject("tile").toString() + "!");
  }

  @Override
  public String toString() {
    return count + " " + type.toString() + "s";
  }
}
