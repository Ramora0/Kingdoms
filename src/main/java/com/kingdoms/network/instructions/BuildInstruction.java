package com.kingdoms.network.instructions;

import com.kingdoms.world.Player;
import com.kingdoms.world.World;

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

  public boolean canBuild() {
    return option.canBuildAt(x, y);
  }

  public void build() {
    option.buildAt(player, x, y);
  }

  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setString("option", option.toString());
    json.setString("player", player.getID());
    json.setInt("x", x);
    json.setInt("y", y);
    return json;
  }

  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
    option = BuildOption.valueOf(json.getString("option"));
    player = World.getPlayer(json.getString("player"));
    x = json.getInt("x");
    y = json.getInt("y");
  }
}
