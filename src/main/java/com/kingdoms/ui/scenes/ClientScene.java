package com.kingdoms.ui.scenes;

import com.kingdoms.network.Client;
import com.kingdoms.network.Network;
import com.kingdoms.ui.Scene;

public class ClientScene extends Scene {
  public ClientScene() {
    super();
    Network.setNetwork(new Client("192.168.1.176"));
  }
}
