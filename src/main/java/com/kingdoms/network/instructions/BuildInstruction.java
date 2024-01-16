package com.kingdoms.network.instructions;

import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.world.Player;

import processing.data.JSONObject;

/**
 * BuildInstruction represents an instruction sent to World to build a building
 */
public class BuildInstruction extends Instruction {
  BuildOption option;

  public Player player;
  public int x, y;

  public BuildInstruction(BuildOption option, Player player, int x, int y) {
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

  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setString("option", option.toString());
    json.setJSONObject("player", player.toReferenceJSON());
    json.setInt("x", x);
    json.setInt("y", y);
    return json;
  }

  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
    option = BuildOption.valueOf(json.getString("option"));
    player = JSONReferenceSerializable.getFromReferenceJSON(json.getJSONObject("player"), Player.class);
    x = json.getInt("x");
    y = json.getInt("y");
  }
}
