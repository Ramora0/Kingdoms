package com.kingdoms.network.instructions;

import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public abstract class Instruction implements JSONSerializable {
  public enum InstructionType {
    BUILD
  }

  InstructionType type;

  public Instruction(InstructionType type) {
    this.type = type;
  }

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", type.toString());
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = InstructionType.valueOf(json.getString("type"));
  }
}
