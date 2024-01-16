package com.kingdoms.ui.scenes.game.focus;

import com.kingdoms.ui.elements.UIText;
import com.kingdoms.world.Tile;

public class BuildingFocusScene extends FocusScene {
  public BuildingFocusScene(Tile focus) {
    super(FocusType.BUILDING, focus);

    if (focus.getBuilding() == null) {
      throw new IllegalArgumentException("Tile must have a building to focus on");
    }

    UIText title = (UIText) new UIText(tile.getBuilding().toString(), 150, 10, 30).below(buildingTab, 20);

    addContainer(title);
  }
}
