package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.MainScene;
import com.kingdoms.world.World;

import processing.data.JSONObject;

public class Client extends Network {
  private BufferedReader in;
  private PrintWriter out;

  public Client(String ip) {
    try {
      Socket socket = new Socket(ip, Network.port);

      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initializeWorld() {
    try {
      String response = in.readLine();
      if (response.equals("world data")) {
        System.out.println("Received world data!");
        String worldData = in.readLine();
        World.fromJSON(JSONObject.parse(worldData));
        out.println("we good");
        UI.changeScene(new MainScene());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendMessages() {
    throw new UnsupportedOperationException("Unimplemented method 'sendMessages'");
  }
}