package com.kingdoms.ui.scenes.game.focus;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIElement;
import com.kingdoms.ui.scenes.game.GameScene;
import com.kingdoms.ui.scenes.game.WorldDisplayScene;
import com.kingdoms.world.tiles.Tile;

public abstract class FocusScene extends WorldDisplayScene {
  enum FocusType {
    BUILDING, TROOP
  };

  Tile tile;
  private UIButton buildingTab;
  private UIButton troopTab;

  public UIButton getRightTab() {
    return troopTab != null ? troopTab : buildingTab;
  }

  FocusScene(FocusType type, Tile tile) {
    super();

    this.tile = tile;

    if (tile.getBuilding() != null) {
      buildingTab = (UIButton) new UIButton("Building", 10, 10, 30,
          type == FocusType.BUILDING ? null : () -> UI.changeScene(new BuildingFocusScene(tile))).setTopLeft();
      addElement(buildingTab);
    }
    if (tile.getTroops().size() > 0) {
      troopTab = (UIButton) new UIButton("Troops", 10, 10, 30,
          type == FocusType.TROOP ? null : () -> UI.changeScene(new TroopFocusScene(tile)))
          .setTop();
      if (buildingTab != null)
        troopTab.rightOf(buildingTab, 5);
      else
        troopTab.setTopLeft();
      addElement(troopTab);
    }
  }

  protected void addContainer(UIElement... elements) {
    UIContainer container = new UIContainer(6, elements);
    container.setX(0);
    container.setWidth(300);
    container.below(getRightTab(), 0);

    UIButton close = (UIButton) new UIButton("X", 10, 10, 50, () -> UI.changeScene(new GameScene()))
        .setTop().rightOf(container, 5).below(getRightTab(), 0);

    addElement(container);
    addElement(close);
  }
}
