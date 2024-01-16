package com.kingdoms.ui.scenes.game;

import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.world.Tile;

public class BuildingFocusScene extends WorldDisplayScene {
  Tile tile;

  public BuildingFocusScene(Tile focus) {
    super();

    this.tile = focus;

    UIText title = new UIText(tile.getBuilding().toString(), 150, 10, 30).setTop();

    UIContainer container = new UIContainer(6);
    addElement(container);
  }
}
