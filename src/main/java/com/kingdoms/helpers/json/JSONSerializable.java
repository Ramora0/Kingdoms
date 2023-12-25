package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;

import processing.data.JSONObject;

public interface JSONSerializable {
  public JSONObject toJSON();

  public void fromJSON(JSONObject json);

  static <T extends JSONSerializable> T createFromJSON(JSONObject json, Class<T> clazz) {
    try {
      T instance = clazz.getDeclaredConstructor().newInstance();
      if (json != null) {
        instance.fromJSON(json);
      }
      return instance;
    } catch (InstantiationException | IllegalAccessException
        | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace(); // Handle the exception based on your needs
      return null;
    }
  }
}
