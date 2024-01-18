package com.kingdoms.network.instructions;

import java.util.List;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.world.tiles.Tile;
import com.kingdoms.world.troops.Troop;
import com.kingdoms.world.troops.Troop.TroopType;

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

    TroopType troopType = TroopType.valueOf(json.getJSONObject("troop").getString("type"));
    troop = JSONReferenceSerializable.getFromJSON(json.getJSONObject("troop"), troopType.clazz);

    path = JSONReferenceSerializable.getFromJSONArray(json.getJSONArray("path"), Tile.class);
  }
}
