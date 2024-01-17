package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import processing.data.JSONArray;
import processing.data.JSONObject;

public interface JSONReferenceSerializable<T extends JSONReferenceSerializable<T>> {
  public JSONObject toReferenceJSON();

  public T fromReferenceJSON(JSONObject json);

  static JSONArray toJSONArray(List<? extends JSONReferenceSerializable<?>> array) {
    JSONArray jsonArray = new JSONArray();
    for (JSONReferenceSerializable<?> element : array) {
      jsonArray.append(element.toReferenceJSON());
    }
    return jsonArray;
  }

  static <T extends JSONReferenceSerializable<?>> T getFromJSON(JSONObject json, Class<T> clazz) {
    try {
      T instance = clazz.getDeclaredConstructor().newInstance();
      return (T) instance.fromReferenceJSON(json);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      return null;
    } catch (NoSuchMethodException e) {
      System.err.println("The class " + clazz.getName() + " does not have a no-argument constructor.");
      e.printStackTrace();
      return null;
    }
  }

  static <T extends JSONReferenceSerializable<T>> List<T> getFromJSONArray(JSONArray json, Class<T> clazz) {
    List<T> list = new ArrayList<>();
    for (int i = 0; i < json.size(); i++) {
      list.add(getFromJSON(json.getJSONObject(i), clazz));
    }
    return list;
  }
}
