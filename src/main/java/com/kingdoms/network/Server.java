package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.kingdoms.helpers.JSON;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.MainScene;
import com.kingdoms.world.World;

public class Server extends Network {
  private ServerSocket serverSocket;

  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public Server() {
    try {
      serverSocket = new ServerSocket(Network.port);
      System.out.println("Server waiting for client on port " + Network.port + "...");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initializeWorld() {
    World.generateWorld(100);
    out.println("world data");
    out.println(JSON.stringify(World.toJSON()));
    try {
      String response = in.readLine();
      if (response.equals("we good")) {
        System.out.println("Client is ready!");
        UI.changeScene(new MainScene());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendMessages() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendMessages'");
  }

  // SERVER METHODS\\
  public String getIP() {
    return serverSocket.getInetAddress().getHostAddress();
  }

  public void waitForClient() {
    if (socket != null)
      throw new RuntimeException("Client already connected!");
    try {
      socket = serverSocket.accept();
      System.out.println("Client has connected!\n");
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}