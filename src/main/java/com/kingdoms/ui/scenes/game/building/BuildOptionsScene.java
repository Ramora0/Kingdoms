package com.kingdoms.ui.scenes.game.building;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.StringManager;
import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIContainer;
import com.kingdoms.ui.elements.UIGroup;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.scenes.game.GameScene;

public class BuildOptionsScene extends Scene {
  public BuildOptionsScene() {
    super();

    BuildOption[] options = BuildOption.values();
    List<UIGroup> buttons = new ArrayList<>();
    for (BuildOption option : options) {
      UIButton button = (UIButton) new UIButton(StringManager.enumToString(option), 10, 10, 30,
          () -> UI.changeScene(new BuildScene(option))).setTopLeft();
      UIText text = (UIText) new UIText("Cost: " + option.getCost(), 10, 30, 30).rightOf(button, 10);
      if (!buttons.isEmpty()) {
        button.below(buttons.get(buttons.size() - 1), 10);
        text.below(buttons.get(buttons.size() - 1), 10);
      }
      buttons.add(new UIGroup(button, text));
    }

    UIGroup buildButtons = new UIGroup(buttons);

    UIContainer resourceDisplay = GameScene.addResourceDisplay(this);

    UIButton close = (UIButton) new UIButton("X", 1190, 10, 50, () -> UI.changeScene(new GameScene()))
        .setTop().rightOf(resourceDisplay, 10);

    addElement(close);
    addElement(buildButtons);
  }
}
