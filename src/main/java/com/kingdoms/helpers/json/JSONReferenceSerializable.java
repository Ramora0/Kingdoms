package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;

import processing.data.JSONObject;

public interface JSONReferenceSerializable<T extends JSONReferenceSerializable<T>> {
  public JSONObject toReferenceJSON();

  public T fromReferenceJSON(JSONObject json);

  static <T extends JSONReferenceSerializable<T>> T getFromReferenceJSON(JSONObject json, Class<T> clazz) {
    try {
      T instance = clazz.getDeclaredConstructor().newInstance();
      return instance.fromReferenceJSON(json);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      return null;
    } catch (NoSuchMethodException e) {
      System.err.println("The class " + clazz.getName() + " does not have a no-argument constructor.");
      e.printStackTrace();
      return null;
    }
  }
}
