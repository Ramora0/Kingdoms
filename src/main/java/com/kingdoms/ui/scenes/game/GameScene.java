package com.kingdoms.ui.scenes.game;

import com.kingdoms.network.Network;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.game.building.BuildOptionsScene;
import com.kingdoms.world.World;

public class GameScene extends WorldDisplayScene {
  public GameScene() {
    super();
    // TODO: Make dynamic button
    UIButton nextTurn = (UIButton) new UIButton("Next Turn", 10, 10, 30, () -> Network.network.nextTurn()).setTopLeft();
    UIButton build = (UIButton) new UIButton("Build", 10, 60, 30, () -> UI.changeScene(new BuildOptionsScene()))
        .setTopLeft().below(nextTurn, 10);

    addElement(nextTurn);
    addElement(build);
    addElement(new UIText(() -> "Me: " + World.me.getResources(), 1190, 10, 30).setRight().setTop());
    addElement(new UIText(() -> "Other: " + World.other.getResources(), 1190, 50, 30).setRight().setTop());
  }
}
