package com.kingdoms.world;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.network.Network;
import com.kingdoms.network.instructions.BuildInstruction;
import com.kingdoms.network.instructions.Instruction;
import com.kingdoms.network.instructions.SetTroopPathInstruction;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.menu.EndScene;
import com.kingdoms.world.buildings.City;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;
import processing.data.JSONObject;

public class World {
  public static int WORLD_SIZE;
  public static Tile[][] tiles;

  public static void generateWorld(int size) { // Only run on server
    WORLD_SIZE = size;
    tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        float height = MathUtils.noise(x * 0.4f, y * 0.5f) - 0.5f;
        // height -= Math.pow(MathUtils.distance(x, y, WORLD_SIZE / 2, WORLD_SIZE / 2) -
        // 5, 3) / 125;
        height += 0.5f - MathUtils.distance(x, y, WORLD_SIZE / 2, WORLD_SIZE / 2) / 10;
        tiles[x][y] = new Tile(x, y, height < 0);
      }
    }

    me = new Player("serverPlayer", Colors.color(0, 0, 255));
    other = new Player("clientPlayer", Colors.color(255, 0, 0));

    for (int x = WORLD_SIZE / 2; x < WORLD_SIZE; x++) {
      if (World.tiles[x + 1][WORLD_SIZE / 2].isLand())
        continue;
      tiles[x][WORLD_SIZE / 2].build(new City(tiles[x][WORLD_SIZE / 2], me));
      break;
    }

    for (int x = WORLD_SIZE / 2 - 1; x >= 0; x--) {
      if (World.tiles[x - 1][WORLD_SIZE / 2].isLand())
        continue;
      tiles[x][WORLD_SIZE / 2].build(new City(tiles[x][WORLD_SIZE / 2], other));
      break;
    }
  }

  public static Player me;
  public static Player other;

  public static Player getPlayer(String id) {
    if (me.getID().equals(id)) {
      return me;
    } else if (other.getID().equals(id)) {
      return other;
    } else {
      throw new IllegalArgumentException("No player with id \"" + id + "\" exists");
    }
  }

  public static Player other(Player player) {
    return player == me ? other : me;
  }

  public static void receiveInstruction(Instruction instruction) {
    Network.network.receiveInstruction(instruction);

    if (instruction instanceof BuildInstruction) {
      BuildInstruction buildInstruction = (BuildInstruction) instruction;

      if (buildInstruction.canBuild()) {
        buildInstruction.build();
      } else {
        throw new IllegalArgumentException("Cannot build instruction: " + buildInstruction.toJSON().toString());
      }
    } else if (instruction instanceof SetTroopPathInstruction) {
      SetTroopPathInstruction setTroopPathInstruction = (SetTroopPathInstruction) instruction;

      setTroopPathInstruction.setPath();
    }
  }

  public static void nextTurn() {
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].nextTurn();
      }
    }
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].unupdate();
      }
    }

    Player victor = checkForWin();
    if (victor != null) {
      if (victor == me) {
        UI.changeScene(new EndScene(true));
      } else {
        UI.changeScene(new EndScene(false));
      }
    }
  }

  static Player checkForWin() {
    if (me.getCityCount() == 0) {
      return other;
    } else if (other.getCityCount() == 0) {
      return me;
    }
    return null;
  }

  public static void display(PApplet canvas) {
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tiles[x][y].display(canvas);
      }
    }
  }

  // HELPER METHODS \\

  public interface TilePredicate {
    boolean test(Tile tile);
  }

  public static boolean hasAdjacentTile(int x, int y, TilePredicate predicate) {
    for (int dx = -1; dx <= 1; dx++)
      for (int dy = -1; dy <= 1; dy++)
        if (World.in(x + dx, y + dy) && predicate.test(tiles[x + dx][y + dy]))
          return true;
    return false;
  }

  public static Tile getTile(int x, int y) {
    return in(x, y) ? tiles[x][y] : null;
  }

  public static boolean in(int x, int y) {
    return x >= 0 && x < WORLD_SIZE && y >= 0 && y < WORLD_SIZE;
  }

  // JSON METHODS \\

  public static JSONObject toJSON() { // Almost exclusively run on server
    JSONObject json = new JSONObject();
    Player victor = checkForWin();

    if (victor != null) {
      json.setJSONObject("victor", victor.toReferenceJSON());
      return json;
    }

    json.setInt("WORLD_SIZE", WORLD_SIZE);

    JSONObject tilesJSON = new JSONObject();
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        tilesJSON.setJSONObject("tile:" + x + "," + y, tiles[x][y].toJSON());
      }
    }
    json.setJSONObject("tiles", tilesJSON);

    json.setJSONObject("me", me.toJSON()); // Maybe have this depend on Server/Client? But probs also works if we jsut
                                           // switch it when loading
    json.setJSONObject("other", other.toJSON());
    return json;
  }

  public static void fromJSON(JSONObject json) { // Almost exclusively run on client
    me = JSONSerializable.createFromJSON(json.getJSONObject("me"), Player.class);
    other = JSONSerializable.createFromJSON(json.getJSONObject("other"), Player.class);

    if (json.hasKey("victor")) {
      Player victor = JSONReferenceSerializable.getFromJSON(json.getJSONObject("victor"), Player.class);
      if (victor == me) {
        UI.changeScene(new EndScene(true));
      } else {
        UI.changeScene(new EndScene(false));
      }
      return;
    }

    WORLD_SIZE = json.getInt("WORLD_SIZE");
    JSONObject tilesJSON = json.getJSONObject("tiles");
    tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
    for (int x = 0; x < WORLD_SIZE; x++) {
      for (int y = 0; y < WORLD_SIZE; y++) {
        JSONObject tileJSON = tilesJSON.getJSONObject("tile:" + x + "," + y);
        tiles[x][y] = JSONSerializable.createFromJSON(tileJSON, Tile.class);
      }
    }
  }
}
