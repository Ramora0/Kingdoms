package com.kingdoms.ui.scenes;

import com.kingdoms.network.Network;
import com.kingdoms.ui.Scene;

public class StartScene extends Scene {
  public StartScene() {
    super();
    Network.network.initializeWorld();
  }
}
