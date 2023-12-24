package com.kingdoms.ui.scenes;

import com.kingdoms.network.Network;
import com.kingdoms.network.Server;
import com.kingdoms.ui.Scene;
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

  boolean once = false;

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    if (!once) {
      server.waitForClient();
      Network.network.initializeWorld();
      once = true;
      return;
    }
    throw new RuntimeException("This should never happen");
  }
}
