package com.kingdoms.helpers;

import processing.data.JSONObject;

public interface JSONSerializable {
  public JSONObject toJSON();

  public void fromJSON(JSONObject json);
}
