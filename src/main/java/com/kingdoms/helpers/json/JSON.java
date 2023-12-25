package com.kingdoms.helpers.json;

import processing.data.JSONObject;

public class JSON {
  public static String stringify(JSONSerializable json) {
    return stringify(json.toJSON());
  }

  public static String stringify(JSONObject json) {
    String string = json.toString();
    string = string.replaceAll("\n", "");
    return string;
  }

  public static JSONObject parse(String string) {
    return JSONObject.parse(string);
  }
}
