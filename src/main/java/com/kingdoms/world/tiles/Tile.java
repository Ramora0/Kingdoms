package com.kingdoms.world.tiles;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.json.JSONReferenceSerializable;
import com.kingdoms.helpers.json.JSONSerializable;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Player;
import com.kingdoms.world.World;
import com.kingdoms.world.WorldElement;
import com.kingdoms.world.buildings.Building;
import com.kingdoms.world.troops.Troop;

import processing.core.PApplet;
import processing.data.JSONObject;

//TODO: Please seperate this out into classes based on biome!!!
public class Tile extends WorldElement implements JSONSerializable, JSONReferenceSerializable<Tile> {
  public static final int TILE_WIDTH = 16;

  List<Troop> troops;

  public void addTroops(Troop troop) {
    if (troop.getPath().isEmpty()) {
      for (Troop other : troops) {
        if (other.getPath().isEmpty() && other.getType() == troop.getType() && other.getPlayer() == troop.getPlayer()) {
          other.absorb(troop);
          return;
        }
      }
    }
    troops.add(troop);
  }

  public List<Troop> getTroops() {
    return troops;
  }

  public List<Troop> getTroops(Player player) {
    return troops.stream().filter(t -> t.getPlayer() == player).toList();
  }

  public void pruneTroops() {
    troops.removeIf(t -> t.getCount() <= 0);
  }

  int x, y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  boolean isWater;

  public boolean isLand() {
    return !isWater;
  }

  Building building;

  public Building getBuilding() {
    return building;
  }

  public boolean hasBuilding() {
    return building != null;
  }

  public void destroyBuilding() {
    building = null;
  }

  public Tile(int x, int y, boolean isWater) {
    this.x = x;
    this.y = y;
    this.isWater = isWater;

    troops = new ArrayList<Troop>();
  }

  // HELPERS \\

  @Deprecated
  public Tile() {
  }

  public static boolean neighbors(Tile a, Tile b) {
    return Math.abs(a.x - b.x) <= 1 && Math.abs(a.y - b.y) <= 1;
  }

  // GAMEPLAY \\

  @Override
  public void doUpdate() {
    throw new UnsupportedOperationException("This isn't how 'tile' should be updated");
  }

  public static void move(Troop troop, Tile from, Tile to) {
    from.troops.remove(troop);
    to.addTroops(troop);
  }

  public void updateBuildings() {
    if (hasBuilding()) {
      building.update();
    }
  }

  public void updateTroops() {
    for (int i = troops.size() - 1; i >= 0; i--) {
      Troop troop = troops.get(i);
      troop.update();
    }
  }

  public void checkCombat() {
    Troop.combat(getTroops(World.me), getTroops(World.other));
    pruneTroops();

    if (hasBuilding()) {
      for (Troop troop : troops) {
        if (troop.getPlayer() != building.getPlayer()) {
          destroyBuilding();
          break;
        }
      }
    }
  }

  public void unupdate() {
    if (hasBuilding()) {
      building.unupdate();
    }
    for (int i = troops.size() - 1; i >= 0; i--) {
      Troop troop = troops.get(i);
      troop.unupdate();
    }
  }

  // UI \\

  public Sprite getSprite() {
    return new Sprite(new ColorImage(isWater ? Colors.color(100, 150, 255) : Colors.color(22, 178, 56)),
        // TODO: Create a custome TilePredicate class that automatically returns false
        // if the tile is null
        new BorderImage(x, y, (tile) -> tile == null || this.isWater == tile.isWater, Colors.color(227, 208, 114)));
  }

  public void display(PApplet canvas) {
    super.display(canvas, WorldDisplayScene.worldDisplayX(x), WorldDisplayScene.worldDisplayY(y));

    if (hasBuilding()) {
      building.display(canvas);
    }

    for (Troop troop : troops) {
      troop.display(canvas);
    }
  }

  UIContainer container;

  public void addUI() {
    if (!hasBuilding())
      return;

    UIText buildingText = (UIText) new UIText(building.getType().toString(),
        () -> WorldDisplayScene.displayX(x + 0.5), () -> WorldDisplayScene.displayY(y), 20);
    container = new UIContainer(0, buildingText);

    UI.currentScene.addElement(container);
  }

  public void removeUI() {
    if (container == null)
      return;
    UI.currentScene.removeElement(container);
    container = null;
  }

  // WRITE FUNCTIONS \\

  @Deprecated
  public void build(Building building) {
    if (hasBuilding())
      throw new RuntimeException("Tile (" + x + ", " + y + ") already has a building!");
    this.building = building;
  }

  // JSON FUNCTIONS \\

  @Override
  public JSONObject toJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    json.setBoolean("isWater", isWater);
    if (hasBuilding()) {
      json.setJSONObject("building", building.toJSON());
    }
    // Save list of troops
    json.setJSONArray("troops", JSONSerializable.toJSONArray(troops));
    return json;
  }

  @Override
  public void fromJSON(JSONObject json) {
    x = json.getInt("x");
    y = json.getInt("y");
    isWater = json.getBoolean("isWater");
    if (json.hasKey("building")) {
      building = Building.createFromJSON(json.getJSONObject("building"));
      building.setTile(this);
    }
    troops = new ArrayList<>();
    for (int i = 0; i < json.getJSONArray("troops").size(); i++) {
      JSONObject troopJSON = json.getJSONArray("troops").getJSONObject(i);
      Troop troop = Troop.createFromJSON(troopJSON);
      troops.add(troop);
      troop.setTile(this);
    }
  }

  @Override
  public JSONObject toReferenceJSON() {
    JSONObject json = new JSONObject();
    json.setInt("x", x);
    json.setInt("y", y);
    return json;
  }

  @Override
  public Tile fromReferenceJSON(JSONObject json) {
    int x = json.getInt("x"), y = json.getInt("y");
    return World.tiles[x][y];
  }
}
