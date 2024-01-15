package com.kingdoms.ui.scenes.game;

import com.kingdoms.network.Network;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIDynamicText;
import com.kingdoms.ui.scenes.game.building.BuildOptionsScene;
import com.kingdoms.world.World;

public class GameScene extends WorldDisplayScene {
  public GameScene() {
    super();
    // TODO: Make dynamic button
    elements.add(new UIButton("Next Turn", 10, 10, 40, () -> Network.network.nextTurn()).setTopLeft());
    elements.add(new UIButton("Build", 10, 60, 40, () -> UI.changeScene(new BuildOptionsScene())).setTopLeft());

    elements.add(new UIDynamicText(() -> "Me: " + World.me.getResources(), 1190, 10, 30).setRight().setTop());
    elements.add(new UIDynamicText(() -> "Other: " + World.other.getResources(), 1190, 50, 30).setRight().setTop());
  }
}
