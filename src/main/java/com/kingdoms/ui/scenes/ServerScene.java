package com.kingdoms.ui.scenes;

import com.kingdoms.network.Network;
import com.kingdoms.network.Server;
import com.kingdoms.ui.Scene;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIText;

import processing.core.PApplet;

public class ServerScene extends Scene {
  Server server;

  public ServerScene() {
    super();
    this.server = new Server();
    Network.setNetwork(server);
    elements.add(new UIText("Waiting for client...", 600, 300, 40));

    elements.add(new UIText("IP: " + server.getIP(), 600, 300, 40));
  }

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    server.waitForClient(); // Make so this runs only once
    UI.changeScene(new StartScene());
  }
}
