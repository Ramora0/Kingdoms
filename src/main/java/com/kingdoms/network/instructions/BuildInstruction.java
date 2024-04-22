package com.kingdoms.network.instructions;

import com.kingdoms.world.Player;
import com.kingdoms.world.buildings.BuildingType;
import com.kingdoms.world.tiles.Tile;

import processing.data.JSONObject;

/**
 * BuildInstruction represents an instruction sent to World to build a building
 */
public class BuildInstruction extends Instruction {
  BuildingType option; // TODO: Should include a reference to a building instead of just a command

  public Player player;
  public Tile tile;

  public BuildInstruction(BuildingType option, Player player, Tile tile) {
    super(InstructionType.BUILD);
    this.option = option;

    this.player = player;
    this.tile = tile;
  }

  @Deprecated
  public BuildInstruction() {
  }

  public boolean canBuild() {
    return option.canBuildAt(player, tile);
  }

  public void build() {
    option.buildAt(player, tile);
  }

  @Override
  public JSONObject toJSON() {
    JSONObject json = super.mainToJSON();
    json.setString("option", option.toString());
    json.setJSONObject("player", player.toReferenceJSON());
    json.setJSONObject("tile", tile.toReferenceJSON());
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
    option = BuildingType.valueOf(json.getString("option"));
    player = Player.fromReferenceJSON(json.getJSONObject("player"));
    tile = Tile.fromReferenceJSON(json.getJSONObject("tile"));
  }
}
