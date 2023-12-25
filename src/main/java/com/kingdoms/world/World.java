package com.kingdoms.world;

import com.kingdoms.helpers.Colors;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.network.Instruction;
import com.kingdoms.network.Network;

import processing.data.JSONObject;

public class World {
  public static int WORLD_SIZE;
  public static Tile[][] tiles;

  public static Player me;
  public static Player other;

  public static void generateWorld(int size) {
    WORLD_SIZE = size;
    tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y] = new Tile(x, y, MathUtils.chance(0.5));
      }
    }

    me = new Player(Colors.color(0, 0, 255));
    other = new Player(Colors.color(255, 0, 0));
  }

  public static void display(processing.core.PApplet canvas) {
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].display(canvas);
      }
    }
  }

  public static void receiveInstruction(Instruction instruction) {
    Network.network.receiveInstruction(instruction);

    // TODO: Implement instruction handling
  }

  public static void nextTurn() {
    // TODO: Implement next turn logic
  }

  public static JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("WORLD_SIZE", WORLD_SIZE);

    JSONObject tilesJSON = new JSONObject();
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tilesJSON.setJSONObject("tile:" + x + "," + y, tiles[x][y].toJSON());
      }
    }
    json.setJSONObject("tiles", tilesJSON);

    json.setJSONObject("me", me.toJSON());
    json.setJSONObject("other", other.toJSON());
    return json;
  }

  public static void fromJSON(JSONObject json) {
    WORLD_SIZE = json.getInt("WORLD_SIZE");
    JSONObject tilesJSON = json.getJSONObject("tiles");
    tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        JSONObject tileJSON = tilesJSON.getJSONObject("tile:" + x + "," + y);
        tiles[x][y] = JSONSerializable.createFromJSON(tileJSON, Tile.class);
      }
    }

    me = new Player(json.getJSONObject("me"));
    other = new Player(json.getJSONObject("other"));
  }
}
