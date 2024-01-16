package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import processing.data.JSONArray;
import processing.data.JSONObject;

public interface JSONSerializable {
  // TODO: Unit tests to make sure classes extending JSONSerializable have no
  // argument constructors

  public JSONObject toJSON();

  public void fromJSON(JSONObject json);

  static JSONArray toJSONArray(List<? extends JSONSerializable> array) {
    JSONArray jsonArray = new JSONArray();
    for (JSONSerializable element : array) {
      jsonArray.append(element.toJSON());
    }
    return jsonArray;
  }

  /**
   * Creates an instance of the specified class from the provided JSON object.
   * 
   * <p>
   * Note: The class specified by the clazz parameter must have a public
   * no-argument constructor.
   *
   * @param <T>   the type of the object to create. This type must extend
   *              JSONSerializable.
   * @param json  the JSON object to convert into an instance of T.
   * @param clazz the class object of the type T.
   * @return an instance of T initialized with the data from the JSON object.
   */
  static <T extends JSONSerializable> T createFromJSON(JSONObject json, Class<T> clazz) {
    try {
      T instance = clazz.getDeclaredConstructor().newInstance();
      if (json != null) {
        instance.fromJSON(json);
      }
      return instance;
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      return null;
    } catch (NoSuchMethodException e) {
      System.err.println("The class " + clazz.getName() + " does not have a no-argument constructor.");
      e.printStackTrace();
      return null;
    }
  }

  static <T extends JSONSerializable> List<T> createFromJSONArray(JSONArray json, Class<T> clazz) {
    List<T> list = new ArrayList<>();
    for (int i = 0; i < json.size(); i++) {
      list.add(createFromJSON(json.getJSONObject(i), clazz));
    }
    return list;
  }
}
