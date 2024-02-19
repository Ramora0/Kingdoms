package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import processing.data.JSONArray;
import processing.data.JSONObject;

public interface JSONReferenceSerializable<T extends JSONReferenceSerializable<T>> {
  public JSONObject toReferenceJSON();

  static JSONArray toJSONArray(List<? extends JSONReferenceSerializable<?>> array) {
    JSONArray jsonArray = new JSONArray();
    for (JSONReferenceSerializable<?> element : array) {
      jsonArray.append(element.toReferenceJSON());
    }
    return jsonArray;
  }

  static <T extends JSONReferenceSerializable<?>> T fromReferenceJSON(JSONObject json, Class<T> clazz) {
    try {
      Method method = clazz.getDeclaredMethod("fromReferenceJSON", JSONObject.class);
      if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
        throw new NoSuchMethodException("The method fromReferenceJSON in class " + clazz.getName() + " is not static");
      }
      return (T) method.invoke(null, json);
    } catch (IllegalAccessException | InvocationTargetException e) {
      System.out.println("Exception trying to instantiate " + clazz.getName() + " from JSON.");
      e.printStackTrace();
      return null;
    } catch (NoSuchMethodException e) {
      System.err.println("The class " + clazz.getName() + " does not have a no-argument constructor.");
      e.printStackTrace();
      return null;
    }
  }

  static <T extends JSONReferenceSerializable<T>> List<T> fromReferenceJSONArray(JSONArray json, Class<T> clazz) {
    List<T> list = new ArrayList<>();
    for (int i = 0; i < json.size(); i++) {
      list.add(fromReferenceJSON(json.getJSONObject(i), clazz));
    }
    return list;
  }
}
