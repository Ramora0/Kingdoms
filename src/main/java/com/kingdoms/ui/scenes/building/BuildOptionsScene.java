package com.kingdoms.ui.scenes.building;

import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;

public class BuildOptionsScene extends Scene {
  public BuildOptionsScene() {
    super();

    elements.add(
        new UIButton("City", 10, 10, 40, () -> UI.changeScene(new BuildScene(BuildOption.CITY)))
            .setTopLeft());
    elements.add(
        new UIButton("Farm", 10, 60, 40, () -> UI.changeScene(new BuildScene(BuildOption.FARM)))
            .setTopLeft());
  }

}
