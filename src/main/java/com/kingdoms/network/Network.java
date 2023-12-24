package com.kingdoms.network;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.JSONSerializable;

public abstract class Network {
  List<Message> queue;

  public Network() {
    queue = new ArrayList<Message>();
  }

  public class Message {
    public String type;
    public JSONSerializable data;

    public Message(String type, JSONSerializable data) {
      this.type = type;
      this.data = data;
    }
  }

  public abstract void sendMessages();

  public abstract void initializeWorld();

  // STATIC CLASS\\
  public static final int port = 12345;

  public static Network network;

  public static void setNetwork(Network network) {
    Network.network = network;
  }
}
