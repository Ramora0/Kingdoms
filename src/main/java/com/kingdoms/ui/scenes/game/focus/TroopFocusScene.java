package com.kingdoms.ui.scenes.game.focus;

import java.util.List;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIGroup;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.TroopMovingScene;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;
import com.kingdoms.world.troops.Troop;

public class TroopFocusScene extends FocusScene {
  Tile tile;

  public TroopFocusScene(Tile focus) {
    super(FocusType.TROOP, focus);

    List<Troop> troops = focus.getTroops();

    if (troops.size() == 0) {
      throw new IllegalArgumentException("Tile must have troops to focus on");
    }

    UIGroup[] groups = new UIGroup[troops.size()];
    UIText troopLabel = (UIText) new UIText(troops.get(0).toString(), 10, 10, 30).below(buildingTab, 10).setLeft();
    if (troops.get(0).getPlayer() == World.me) {
      UIButton move = (UIButton) new UIButton("Move", 10, 10, 30, () -> {
        UI.changeScene(new TroopMovingScene(troops.get(0)));
      }).rightOf(troopLabel, 10).below(buildingTab, 10);
      groups[0] = new UIGroup(troopLabel, move);
    } else {
      groups[0] = new UIGroup(troopLabel);
    }
    // for (int i = 1; i < troops.size(); i++) {
    // groups[i] = (UIText) new UIText(troops.get(i).toString(), 10, 10,
    // 30).below(groups[i - 1], 10).setLeft();
    // }

    addContainer(groups);
  }
}
