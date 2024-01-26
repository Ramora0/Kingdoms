package com.kingdoms.world.troops;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.WorldElement;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public abstract class Troop extends WorldElement implements JSONSerializable, JSONReferenceSerializable<Troop> {
  public enum TroopType {
    SOLDIER(Soldier.class);

    public final Class<? extends Troop> clazz;

    TroopType(Class<? extends Troop> troopClass) {
      this.clazz = troopClass;
    }
  }

  String id;

  public String getID() {
    return id;
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

  List<Tile> path = new ArrayList<>();

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
    id = player.newTroopID();
  }

  @Deprecated
  public Troop() {
  }

  public void absorb(Troop troop) {
    count += troop.count;
  }

  @Override
  public void doUpdate() {
    if (path.isEmpty()) {
      return;
    }

    Tile fromTile = tile;
    tile = path.remove(0);
    Tile.move(this, fromTile, tile);
  }

  public static void combat(List<Troop> a, List<Troop> b) {
    int aCount = a.stream().mapToInt(t -> t.count).sum();
    int bCount = b.stream().mapToInt(t -> t.count).sum();

    if (aCount == 0 || bCount == 0) {
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

    System.out.println("Finished combat between troops: " + a + " and " + b);
  }

  // TODO: Fix this at some point
  @Override
  public Sprite getSprite() {
    throw new UnsupportedOperationException("Shouldn't be displaying troops like this");
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
    json.setString("id", id);
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = TroopType.valueOf(json.getString("type"));
    count = json.getInt("count");
    player = JSONReferenceSerializable.getFromJSON(json.getJSONObject("player"), Player.class);
    tile = JSONReferenceSerializable.getFromJSON(json.getJSONObject("tile"), Tile.class);
    path = JSONReferenceSerializable.getFromJSONArray(json.getJSONArray("path"), Tile.class);
    id = json.getString("id");
    if (path.contains(null)) {
      throw new IllegalArgumentException("Path contains a null tile on loading from JSON " + json.getJSONArray("path"));
    }
  }

  public JSONObject toReferenceJSON() {
    JSONObject json = new JSONObject();
    json.setString("id", id);
    json.setJSONObject("tile", tile.toReferenceJSON());
    return json;
  }

  public Troop fromReferenceJSON(JSONObject json) {
    String id = json.getString("id");
    List<Troop> troops = JSONReferenceSerializable.getFromJSON(json.getJSONObject("tile"), Tile.class).getTroops();
    for (Troop troop : troops) {
      if (troop.getID().equals(id)) {
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
