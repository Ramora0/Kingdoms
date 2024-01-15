package com.kingdoms.ui.scenes.network;

import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIText;

public class OpeningScene extends Scene {
  public OpeningScene() {
    super();
    elements.add(new UIText("Choose host or join", 600, 100, 40));
    elements.add(new UIButton("Host", 600, 300, 40, () -> UI.changeScene(new ServerScene())));
    elements.add(new UIButton("Join", 600, 400, 40, () -> UI.changeScene(new ClientScene())));
  }
}
