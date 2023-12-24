package com.kingdoms.ui.scenes;

import com.kingdoms.network.Client;
import com.kingdoms.network.Network;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIText;

import processing.core.PApplet;

public class ClientScene extends Scene {
  Client client;

  public ClientScene() {
    super();
    elements.add(new UIText("Connecting to server...", 600, 300, 40));
  }

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    this.client = new Client("192.168.1.176");
    Network.setNetwork(client);
    UI.changeScene(new StartScene());
  }
}