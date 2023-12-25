package com.kingdoms.ui.scenes;

import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIText;

public class OpeningScene extends Scene {
  public OpeningScene() {
    super();
    elements.add(new UIText("Choose host or join", 40).setCenter(600, 100));
    elements.add(new UIButton("Host", 40, () -> UI.changeScene(new ServerScene())).setCenter(600, 300));
    elements.add(new UIButton("Join", 40, () -> UI.changeScene(new ClientScene())).setCenter(600, 400));
  }
}
