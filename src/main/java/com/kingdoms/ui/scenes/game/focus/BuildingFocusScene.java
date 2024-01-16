package com.kingdoms.ui.scenes.game.focus;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.GameScene;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.Tile;

public class BuildingFocusScene extends WorldDisplayScene {
  Tile tile;

  public BuildingFocusScene(Tile focus) {
    super();

    this.tile = focus;

    if (tile.getBuilding() == null) {
      return;
    }

    UIButton buildingTab = ((UIButton) new UIButton("Building", 10, 10, 30, () -> {
    }).setTopLeft()).disable();
    UIButton troopTab = (UIButton) new UIButton("Troops", 10, 10, 30, () -> UI.changeScene(new TroopFocusScene(tile)))
        .setTop().rightOf(buildingTab, 10);

    UIText title = (UIText) new UIText(tile.getBuilding().toString(), 150, 10, 30).below(troopTab, 20);

    UIContainer container = new UIContainer(6, title);
    container.setX(0);
    container.setWidth(300);
    container.below(buildingTab, 0);

    UIButton close = (UIButton) new UIButton("X", 10, 10, 50, () -> UI.changeScene(new GameScene()))
        .setTop().rightOf(container, 5).below(troopTab, 0);

    addElement(buildingTab);
    addElement(troopTab);
    addElement(container);
    addElement(close);
  }
}
