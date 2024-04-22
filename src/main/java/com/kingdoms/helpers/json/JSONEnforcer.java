package com.kingdoms.helpers.json;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import processing.data.JSONObject;

public class JSONEnforcer { // TODO: Check classes implementing JSONSerializable have a no-argument
                            // constructor, and ones that directly implement it have a static
                            // createFromJSON()
  public static void enforce() throws RuntimeException {
    List<Class<? extends JSONReferenceSerializable<?>>> classesImplementingInterface = getClassesImplementingInterface(
        JSONReferenceSerializable.class);
    boolean error = false;

    for (Class<? extends JSONReferenceSerializable<?>> clazz : classesImplementingInterface) {
      try {
        System.out.println("Checking " + clazz.getName());
        enforceFromReferenceJSON(clazz);
      } catch (Exception e) {
        e.printStackTrace();
        error = true;
      }
    }

    if (error)
      throw new RuntimeException("JSONEnforcer failed");
  }

  public static void enforceFromReferenceJSON(Class<? extends JSONReferenceSerializable<?>> clazz) throws Exception {
    try {
      Method method = clazz.getDeclaredMethod("fromReferenceJSON", JSONObject.class);
      if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
        throw new Exception("The method fromReferenceJSON in class " + clazz.getName() + " is not static");
      }
    } catch (NoSuchMethodException e) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        try {
          Method method = superClass.getDeclaredMethod("fromReferenceJSON", JSONObject.class);
          if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
            throw new Exception(
                "The method fromReferenceJSON in superclass " + superClass.getName() + " is not static");
          }
        } catch (NoSuchMethodException e2) {
          throw new Exception("Neither class " + clazz.getName() + " nor its superclass " + superClass.getName()
              + " implement fromReferenceJSON method");
        }
      } else {
        throw new Exception(
            "Class " + clazz.getName() + " does not implement fromReferenceJSON method and has no superclass");
      }
    }
  }

  public static <T> List<Class<? extends T>> getClassesImplementingInterface(Class<? extends T> interfaceClass) {
    List<Class<? extends T>> classesImplementingInterface = new ArrayList<>();
    try {
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      String path = "com/kingdoms";
      Enumeration<URL> resources = classLoader.getResources(path);
      while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        File directory = new File(resource.getFile());
        searchDirectory(directory, path, interfaceClass, classesImplementingInterface);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return classesImplementingInterface;
  }

  public static <T> void searchDirectory(File directory, String path, Class<? extends T> interfaceClass,
      List<Class<? extends T>> classesImplementingInterface) {
    if (directory.exists()) {
      String[] files = directory.list();
      if (files != null) {
        for (String file : files) {
          File subFile = new File(directory, file);
          if (subFile.isDirectory()) {
            searchDirectory(subFile, path + '.' + file, interfaceClass, classesImplementingInterface);
          } else if (file.endsWith(".class")) {
            String className = path.replace('/', '.') + '.' + file.substring(0, file.length() - 6);
            try {
              Class<?> clazz = Class.forName(className);
              if (interfaceClass.isAssignableFrom(clazz) && !clazz.isInterface()) {
                classesImplementingInterface.add((Class<? extends T>) clazz);
              }
            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
}
