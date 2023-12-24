package com.kingdoms.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Network {
  private BufferedReader in;
  private PrintWriter out;

  public Client(String ip) {
    try {
      Socket socket = new Socket(ip, Network.port); // Connect to the server

      // Input and output streams for communication
      this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);

      // Send a message to the server
      out.println("Hello from the client!");

      // Read the server's response
      String response = in.readLine();
      System.out.println("Server response: " + response);

      // Don't close the streams or socket here
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sendMessages() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendMessages'");
  }
}