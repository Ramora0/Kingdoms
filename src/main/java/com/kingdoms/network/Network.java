package com.kingdoms.network;

import java.util.ArrayList;
import java.util.List;

public abstract class Network {
  public static final int port = 12345;

  public static Network network;

  public static void setNetwork(Network network) {
    Network.network = network;
  }

  List<Message> queue;

  public Network() {
    queue = new ArrayList<Message>();
  }

  public class Message {
    public String type;
    public Object[] data;

    public Message(String type, Object[] data) {
      this.type = type;
      this.data = data;
    }
  }

  public abstract void sendMessages();
}
