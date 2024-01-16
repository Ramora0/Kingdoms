package com.kingdoms.ui.scenes.game.focus;

import java.util.List;

import com.kingdoms.ui.elements.UIText;
import com.kingdoms.world.Tile;
import com.kingdoms.world.troops.Troop;

public class TroopFocusScene extends FocusScene {
  Tile tile;

  public TroopFocusScene(Tile focus) {
    super(FocusType.TROOP, focus);

    List<Troop> troops = focus.getTroops();

    if (troops.size() == 0) {
      throw new IllegalArgumentException("Tile must have troops to focus on");
    }

    UIText[] groups = new UIText[troops.size()];
    groups[0] = (UIText) new UIText(troops.get(0).toString(), 10, 10, 30).below(buildingTab, 10).setLeft();
    for (int i = 1; i < troops.size(); i++) {
      groups[i] = (UIText) new UIText(troops.get(i).toString(), 10, 10, 30).below(groups[i - 1], 10).setLeft();
    }

    addContainer(groups);
  }
}
