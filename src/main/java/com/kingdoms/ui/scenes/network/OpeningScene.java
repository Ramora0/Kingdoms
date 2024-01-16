package com.kingdoms.ui.scenes.network;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.Scene;

public class OpeningScene extends Scene {
  public OpeningScene() {
    super();
    addElement(new UIText("Choose host or join", 600, 100, 40));
    addElement(new UIButton("Host", 600, 300, 40, () -> UI.changeScene(new ServerScene())));
    addElement(new UIButton("Join", 600, 400, 40, () -> UI.changeScene(new ClientScene())));
  }
}
