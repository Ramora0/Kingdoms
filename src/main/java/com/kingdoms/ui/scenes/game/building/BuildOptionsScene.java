package com.kingdoms.ui.scenes.game.building;

import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;

public class BuildOptionsScene extends Scene {
  public BuildOptionsScene() {
    super();

    UIButton cityButton = (UIButton) new UIButton("City", 10, 10, 40,
        () -> UI.changeScene(new BuildScene(BuildOption.CITY)))
        .setTopLeft();
    UIButton farmButton = (UIButton) new UIButton("Farm", 10, 60, 40,
        () -> UI.changeScene(new BuildScene(BuildOption.FARM)))
        .setTopLeft().below(cityButton, 10);

    elements.add(cityButton);
    elements.add(farmButton);
  }

}
