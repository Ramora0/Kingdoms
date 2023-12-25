package com.kingdoms.helpers.json;

import java.lang.reflect.InvocationTargetException;

import processing.data.JSONObject;

public interface JSONSerializable {
  // TODO: Unit tests to make sure classes extending JSONSerializable have no
  // argument constructors

  public JSONObject toJSON();

  public void fromJSON(JSONObject json);

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
      e.printStackTrace(); // Handle the exception based on your needs
      return null;
    } catch (NoSuchMethodException e) {
      System.err.println("The class " + clazz.getName() + " does not have a no-argument constructor.");
      e.printStackTrace();
      return null;
    }
  }
}
