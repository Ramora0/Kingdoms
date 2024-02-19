package com.kingdoms.network.instructions;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.world.Player;
import com.kingdoms.world.buildings.BuildingType;

import processing.data.JSONObject;

/**
 * BuildInstruction represents an instruction sent to World to build a building
 */
public class BuildInstruction extends Instruction {
  BuildingType option; // TODO: Should include a reference to a building instead of just a command

  public Player player;
  public int x, y; // TODO: Change to tile and store as a tile reference

  public BuildInstruction(BuildingType option, Player player, int x, int y) {
    super(InstructionType.BUILD);
    this.option = option;

    this.player = player;
    this.x = x;
    this.y = y;
  }

  @Deprecated
  public BuildInstruction() {
  }

  public boolean canBuild() {
    return option.canBuildAt(player, x, y);
  }

  public void build() {
    option.buildAt(player, x, y);
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setString("option", option.toString());
    json.setJSONObject("player", player.toReferenceJSON());
    json.setInt("x", x);
    json.setInt("y", y);
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
    option = BuildingType.valueOf(json.getString("option"));
    player = Player.fromReferenceJSON(json.getJSONObject("player"));
    x = json.getInt("x");
    y = json.getInt("y");
  }
}
