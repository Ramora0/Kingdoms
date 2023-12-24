package com.kingdoms.world;

import com.kingdoms.helpers.MathUtils;

import processing.data.JSONObject;

public class World {
  public static int WORLD_SIZE;
  static Tile[][] tiles;

  public static void generateWorld(int size) {
    WORLD_SIZE = size;
    tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y] = new Tile(x, y, MathUtils.chance(0.5));
      }
    }
  }

  public static void display(processing.core.PApplet canvas) {
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].display(canvas);
      }
    }
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
    return json;
  }

  public static void fromJSON(JSONObject json) {
    WORLD_SIZE = json.getInt("WORLD_SIZE");
    JSONObject tilesJSON = json.getJSONObject("tiles");
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].fromJSON(tilesJSON.getJSONObject("tile:" + x + "," + y));
      }
    }
  }
}
