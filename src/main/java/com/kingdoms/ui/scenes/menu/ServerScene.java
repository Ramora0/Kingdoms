package com.kingdoms.ui.scenes.menu;

import com.kingdoms.network.Network;
import com.kingdoms.network.Server;
import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.Scene;

import processing.core.PApplet;

public class ServerScene extends Scene {
  Server server;

  public ServerScene() {
    super();
    this.server = new Server();
    Network.setNetwork(server);
    addElement(new UIText("Waiting for client...", 600, 300, 40));

    String ip = server.getIP();
    if (ip == null)
      ip = "localhost";
    addElement(new UIText("IP: " + ip, 600, 400, 40));
  }

  boolean once = false;

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);
    if (!once) {
      once = true;
      return;
    }
    server.waitForClient();
    Network.network.initializeWorld();
  }
}
