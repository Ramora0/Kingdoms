package com.kingdoms.ui.scenes;

import com.kingdoms.network.Network;
import com.kingdoms.network.Server;
import com.kingdoms.ui.Scene;

public class ServerScene extends Scene {
  public ServerScene() {
    super();
    Network.setNetwork(new Server());
  }
}
