package com.kingdoms.ui.scenes;

import com.kingdoms.network.Network;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIDynamicText;
import com.kingdoms.world.World;

public class MainScene extends WorldDisplayScene {
  public MainScene() {
    super();
    elements.add(new UIButton("Next Turn", 10, 10, 40, () -> Network.network.nextTurn()).setTopLeft());
    // elements.add(new UIButton("Build", 10, 60, 40, () ->
    // UI.change).setTopLeft());

    elements.add(new UIDynamicText(() -> "Me: " + World.me.getResources(), 1190, 10, 30).setRight().setTop());
    elements.add(new UIDynamicText(() -> "Other: " + World.other.getResources(), 1190, 50, 30).setRight().setTop());
  }
}
