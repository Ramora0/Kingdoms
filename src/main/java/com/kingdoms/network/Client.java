package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.json.JSON;
import com.kingdoms.network.instructions.Instruction;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.game.GameScene;
import com.kingdoms.world.World;

public class Client extends Network {
  List<Instruction> instructions = new ArrayList<Instruction>();

  static final String ip = "192.168.1.47";

  public Client() {
    try {
      socket = new Socket(ip, Network.port);

      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initializeWorld() {
    readWorldData();

    // Confirm with server that data was received error-free
    UI.changeScene(new GameScene());
  }

  @Override
  public void nextTurn() {
    out.println(NetworkMessages.NEXT_TURN);

    System.out.println("Waiting for next turn...");
    waitForText(NetworkMessages.NEXT_TURN);

    System.out.println("Sending instructions...");
    out.println(instructions.size());
    for (Instruction instruction : instructions) {
      out.println(JSON.stringify(instruction));
    }
    instructions.clear();

    readWorldData();

    Network.cleanupNextTurn();
  }

  @Override
  public void receiveInstruction(Instruction instruction) {
    instructions.add(instruction);
    System.out.println("Received instruction: " + instruction.toJSON().toString());
  }

  // CLIENT METHODS\\

  public void readWorldData() {
    System.out.println("Waiting for world data...");
    waitForText(NetworkMessages.SENDING_WORLD_DATA);

    try {
      String worldData = in.readLine();
      World.fromJSON(JSON.parse(worldData), true);
    } catch (IOException e) {
      e.printStackTrace();
    }

    out.println(NetworkMessages.WORLD_DATA_RECEIVED);
    System.out.println("World data processed!");
  }
}