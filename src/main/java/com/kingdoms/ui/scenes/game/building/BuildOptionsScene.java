package com.kingdoms.ui.scenes.game.building;

import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.scenes.game.GameScene;

public class BuildOptionsScene extends Scene {
  public BuildOptionsScene() {
    super();

    UIButton cityButton = (UIButton) new UIButton("City", 10, 10, 40,
        () -> UI.changeScene(new BuildScene(BuildOption.CITY)))
        .setTopLeft();
    UIButton farmButton = (UIButton) new UIButton("Farm", 10, 60, 40,
        () -> UI.changeScene(new BuildScene(BuildOption.FARM)))
        .setTopLeft().below(cityButton, 10);
    UIButton trainingCampButton = (UIButton) new UIButton("Training Camp", 10, 60, 40,
        () -> UI.changeScene(new BuildScene(BuildOption.TRAINING_CAMP)))
        .setTopLeft().below(farmButton, 10);

    UIButton close = (UIButton) new UIButton("X", 1190, 10, 50, () -> UI.changeScene(new GameScene()))
        .setTop().setRight();

    addElement(close);
    addElement(cityButton);
    addElement(farmButton);
    addElement(trainingCampButton);
  }
}
