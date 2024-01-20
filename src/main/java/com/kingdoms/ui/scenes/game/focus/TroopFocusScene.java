package com.kingdoms.ui.scenes.game.focus;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIElement;
import com.kingdoms.ui.elements.UIGroup;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.TroopMovingScene;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;
import com.kingdoms.world.troops.Troop;

public class TroopFocusScene extends FocusScene {
  Tile tile;

  public TroopFocusScene(Tile focus) {
    super(FocusType.TROOP, focus);

    List<Troop> troops = focus.getTroops();

    if (troops.size() == 0) {
      throw new IllegalArgumentException("Tile must have troops to focus on");
    }

    List<UIGroup> groups = new ArrayList<>();
    for (Troop troop : troops) {
      UIElement below = groups.size() > 0 ? groups.get(groups.size() - 1) : getRightTab();
      UIText troopLabel = (UIText) new UIText(troop.toString(), 10, 10, 30).below(below, 10).setLeft();

      if (troop.getPlayer() == World.me) {
        UIButton move = (UIButton) new UIButton("Move", 10, 10, 30, () -> {
          UI.changeScene(new TroopMovingScene(troop));
        }).rightOf(troopLabel, 10).below(below, 10);
        groups.add(new UIGroup(troopLabel, move));
      } else {
        groups.add(new UIGroup(troopLabel));
      }
    }

    // System.out.println(JSONSerializable.toJSONArray(troops).toString());

    addContainer(groups.toArray(new UIGroup[0]));
  }
}
