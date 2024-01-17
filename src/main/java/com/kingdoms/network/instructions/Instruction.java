package com.kingdoms.network.instructions;

import com.kingdoms.helpers.json.JSONSerializable;

import processing.data.JSONObject;

public abstract class Instruction implements JSONSerializable {
  public enum InstructionType {
    BUILD(BuildInstruction.class),
    SET_TROOP_PATH(SetTroopPathInstruction.class);

    public final Class<? extends Instruction> clazz;

    InstructionType(Class<? extends Instruction> clazz) {
      this.clazz = clazz;
    }
  }

  InstructionType type;

  public Instruction(InstructionType type) {
    this.type = type;
  }

  @Deprecated
  public Instruction() {
  }

  public JSONObject mainToJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", type.toString());
    return json;
  }

  public void mainFromJSON(JSONObject json) {
    type = InstructionType.valueOf(json.getString("type"));
  }

  public static Instruction createFromJSON(JSONObject json) {
    InstructionType type = InstructionType.valueOf(json.getString("type"));
    return JSONSerializable.createFromJSON(json, type.clazz);
  }
}
