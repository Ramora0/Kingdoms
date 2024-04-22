package com.kingdoms.network.instructions;

import java.util.List;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.world.tiles.Tile;
import com.kingdoms.world.troops.Troop;

import processing.data.JSONObject;

public class SetTroopPathInstruction extends Instruction {
  List<Tile> path;
  Troop troop;

  public SetTroopPathInstruction(Troop troop, List<Tile> path) {
    super(InstructionType.SET_TROOP_PATH);
    this.troop = troop;
    this.path = path;
  }

  @Deprecated
  public SetTroopPathInstruction() {
    super();
  }

  public void setPath() {
    if (path.contains(null)) {
      throw new IllegalArgumentException(
          "Path cannot contain null tiles " + JSONSerializable.toJSONArray(path).toString());
    }
    troop.setPath(path);
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setJSONObject("troop", troop.toReferenceJSON());
    json.setJSONArray("path", JSONReferenceSerializable.toJSONArray(path));
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);

    String troopTypeString = json.getJSONObject("troop").getString("type");
    if (troopTypeString == null)
      throw new IllegalArgumentException(
          "Troop type cannot be null for troop " + json.getJSONObject("troop").toString());
    troop = Troop.fromReferenceJSON(json.getJSONObject("troop"));

    path = JSONReferenceSerializable.fromReferenceJSONArray(json.getJSONArray("path"), Tile.class);
  }
}
