package com.kingdoms.ui.scenes.game.building;

import com.kingdoms.helpers.canvas.Constants;
import com.kingdoms.helpers.events.EventBus.Subscribe;
import com.kingdoms.network.instructions.BuildInstruction;
import com.kingdoms.ui.Keys;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UINotification;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.GameScene;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.World;
import com.kingdoms.world.buildings.BuildingType;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;

public class BuildScene extends WorldDisplayScene {
  BuildingType option;

  public BuildScene(BuildingType option) {
    super();
    this.option = option;
    String buildingName = option.toString().substring(0, 1).toUpperCase()
        + option.toString().substring(1).toLowerCase();
    addElement(new UIText("Building: " + buildingName, 10, 10, 30).setTopLeft());

    UIContainer resourceDisplay = GameScene.addResourceDisplay(this);

    addElement(
        new UIButton("X", Constants.width - 10, 10, 30, () -> UI.changeScene(new GameScene())).setTop()
            .leftOf(resourceDisplay, 10));
  }

  public void display(PApplet canvas) {
    super.display(canvas);

    Tile tile = WorldDisplayScene.getHighlightedTile(canvas);

    if (tile == null)
      return;

    canvas.pushStyle();
    canvas.stroke(World.me.getColor());
    canvas.strokeWeight(3);
    if (!option.canBuildAt(World.me, tile.getX(), tile.getY()))
      canvas.noFill();
    else
      canvas.fill(World.me.getColor(), 100);
    canvas.square(tile.getX(), tile.getY(), 1);
    canvas.popStyle();
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    int x = (int) WorldDisplayScene.coordX(canvas.mouseX);
    int y = (int) WorldDisplayScene.coordY(canvas.mouseY);
    if (option.canBuildAt(World.me, x, y)) {
      World.receiveInstruction(new BuildInstruction(option, World.me, x, y));
      // System.out.println("Tile: " + World.toJSON());
      if (!Keys.keyPressed[Keys.SHIFT])
        UI.changeScene(new GameScene());
    } else {
      addElement(new UINotification("Cannot build here!", canvas.mouseX, canvas.mouseY));
    }
  }
}
