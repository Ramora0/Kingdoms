package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import com.kingdoms.helpers.json.JSON;
import com.kingdoms.helpers.json.JSONSerializable;
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
    World.generateWorld(20); // Generate world

    sendWorldData();

    System.out.println("Client is ready!"); // Move to the game
    UI.changeScene(new MainScene());
  }

  @Override
  public void nextTurn() {
    out.println(NetworkMessages.NEXT_TURN);

    waitForText(NetworkMessages.NEXT_TURN);

    try {
      int instructionCount = Integer.parseInt(in.readLine());
      for (int i = 0; i < instructionCount; i++) {
        Instruction instruction = JSONSerializable.createFromJSON(JSON.parse(in.readLine()), Instruction.class);
        World.receiveInstruction(instruction);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    World.nextTurn();

    sendWorldData();

    Network.cleanupNextTurn();
  }

  @Override
  public void receiveInstruction(Instruction instruction) {
  }

  // SERVER METHODS\\
  private void sendWorldData() {
    out.println(NetworkMessages.SENDING_WORLD_DATA);
    out.println(JSON.stringify(World.toJSON()));

    waitForText(NetworkMessages.WORLD_DATA_RECEIVED); // Confirm with client that data was received error-free
  }

  public String getIP() {
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
          InetAddress inetAddress = inetAddresses.nextElement();
          if (!inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(":") == -1) {
            return inetAddress.getHostAddress();
          }
        }
      }
      return null;
    } catch (SocketException e) {
      e.printStackTrace();
      return null;
    }
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