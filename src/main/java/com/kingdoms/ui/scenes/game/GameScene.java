package com.kingdoms.ui.scenes.game;

import com.kingdoms.helpers.events.EventBus.Subscribe;
import com.kingdoms.network.Network;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.scenes.game.building.BuildOptionsScene;
import com.kingdoms.ui.scenes.game.focus.BuildingFocusScene;
import com.kingdoms.ui.scenes.game.focus.TroopFocusScene;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;

public class GameScene extends WorldDisplayScene {
  public GameScene() {
    super();
    // TODO: Make dynamic button
    UIButton nextTurn = (UIButton) new UIButton("Next Turn", 10, 10, 30, () -> Network.network.nextTurn()).setTopLeft();
    UIButton build = (UIButton) new UIButton("Build", 10, 60, 30, () -> UI.changeScene(new BuildOptionsScene()))
        .setTopLeft().below(nextTurn, 10);

    addElement(nextTurn);
    addElement(build);

    addResourceDisplay(this);
  }

  public static void addResourceDisplay(Scene scene) {
    UIText resources = (UIText) new UIText(() -> "Resources: " + World.me.getResources(), 1190, 10, 30)
        .setRight().setTop();
    UIText otherResources = (UIText) new UIText(() -> "Other: " + World.other.getResources(), 1190, 50, 30)
        .setRight().below(resources, 0);
    UIContainer resourcesContainer = new UIContainer(3, resources, otherResources);

    scene.addElement(resourcesContainer);
  }

  @Subscribe
  public void mousePressed(Object data) {
    PApplet canvas = (PApplet) data;

    Tile tile = WorldDisplayScene.getHighlightedTile(canvas);
    if (tile != null) {
      if (tile.getBuilding() != null) {
        UI.changeScene(new BuildingFocusScene(tile));
      } else if (tile.getTroops().size() > 0) {
        UI.changeScene(new TroopFocusScene(tile));
      }
    }
  }
}
