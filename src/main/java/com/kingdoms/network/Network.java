package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.kingdoms.network.instructions.Instruction;

public abstract class Network {
  protected Socket socket;
  protected BufferedReader in;
  protected PrintWriter out;

  protected boolean waitForText(String text) {
    try {
      String line;
      while ((line = in.readLine()) != null) {
        if (line.equals(text)) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public abstract void initializeWorld();

  public abstract void nextTurn();

  public abstract void receiveInstruction(Instruction instruction);

  // STATIC CLASS\\
  public static final int port = 12345;

  public static Network network;

  public static boolean opponentReady;

  // public static void nextTurn() {
  // // Send that I am ready
  // // Listen for opponent to be ready
  // // Launch network.nextTurn, which will run different logic for the
  // client/server
  // }

  public static void setNetwork(Network network) {
    Network.network = network;
  }

  /** Cleanup function for after all data is sent between clients */
  public static void cleanupNextTurn() {
    opponentReady = false;
  }
}
