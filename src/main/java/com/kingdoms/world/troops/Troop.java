package com.kingdoms.world.troops;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.Updateable;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Troop extends Updateable implements JSONSerializable, JSONReferenceSerializable<Troop> {
  public enum TroopType {
    SOLDIER(Soldier.class);

    public final Class<? extends Troop> clazz;

    TroopType(Class<? extends Troop> troopClass) {
      this.clazz = troopClass;
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

  @Override
  public void doUpdate() {
    System.out.println("Starting doUpdate for troop: " + this);

    if (path == null || path.isEmpty()) {
      System.out.println("Early return condition met. path: " + path);
      return;
    }

    System.out.println("Moving troop from tile: " + tile + " to tile: " + path.get(0));
    Tile.move(this, tile, path.get(0));
    tile = path.get(0);
    path.remove(0);

    System.out.println("Starting combat with troops at tile: " + tile);
    Troop.combat(tile.getTroops(player), tile.getTroops(World.other(player)));
    tile.pruneTroops();

    if (tile.hasBuilding() && tile.getBuilding().getPlayer() != player) {
      System.out.println("Destroying building at tile: " + tile);
      tile.destroyBuilding();
    }

    System.out.println("Finished doUpdate for troop: " + this);
  }

  public static void combat(List<Troop> a, List<Troop> b) {
    int aCount = a.stream().mapToInt(t -> t.count).sum();
    int bCount = b.stream().mapToInt(t -> t.count).sum();

    if (aCount == 0 || bCount == 0) {
      System.out.println("No combat");
      return;
    }

    int aIndex = 0;
    int bIndex = 0;

    while (aIndex < a.size() && bIndex < b.size()) {
      Troop aTroop = a.get(aIndex);
      Troop bTroop = b.get(bIndex);

      if (aTroop.count > bTroop.count) {
        aTroop.count -= bTroop.count;
        bTroop.count = 0;
        bIndex++;
      } else if (aTroop.count < bTroop.count) {
        bTroop.count -= aTroop.count;
        aTroop.count = 0;
        aIndex++;
      } else {
        aTroop.count = 0;
        bTroop.count = 0;
        aIndex++;
        bIndex++;
      }
    }
  }

  public void display(PApplet canvas) {
    canvas.fill(player.getColor());
    canvas.circle(WorldDisplayScene.worldDisplayX(tile.getX() + 0.5),
        WorldDisplayScene.worldDisplayY(tile.getY() + 0.5),
        (float) Math.sqrt(count));
  }

  // JSON \\
  /** Helper method to load from JSON when you don't know the type of troop */
  public static Troop createFromJSON(JSONObject json) {
    TroopType type = TroopType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.clazz);
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
    path = JSONReferenceSerializable.getFromJSONArray(json.getJSONArray("path"), Tile.class); // This aint workin
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
