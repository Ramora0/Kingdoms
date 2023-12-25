package com.kingdoms.ui.scenes.building;

import com.kingdoms.events.EventBus.Subscribe;
import com.kingdoms.network.instructions.BuildInstruction;
import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.WorldDisplayScene;
import com.kingdoms.world.World;

import processing.core.PApplet;

public class BuildScene extends WorldDisplayScene {
  BuildOption option;

  public BuildScene(BuildOption option) {
    super();
    this.option = option;
    String buildingName = option.toString().substring(0, 1).toUpperCase()
        + option.toString().substring(1).toLowerCase();
    elements.add(new UIText("Building: " + buildingName, 10, 10, 30).setTopLeft());
  }

  public void display(PApplet canvas) {
    super.display(canvas);
  }

  @Subscribe
  public void mousePressed(PApplet canvas) {
    int x = WorldDisplayScene.getXTileCoord(canvas.mouseX);
    int y = WorldDisplayScene.getYTileCoord(canvas.mouseY);
    if (option.canBuildAt(x, y)) {
      World.receiveInstruction(new BuildInstruction(option, World.me, x, y));
    }
  }
}
