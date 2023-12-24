package com.kingdoms.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventBus {
  public interface EventListener {
    void update(Object data);
  }

  private static Map<String, Map<String, EventListener>> events = new HashMap<>();

  public static void subscribe(String eventType, String listenerId, EventListener listener) {
    Map<String, EventListener> eventListeners = events.get(eventType);
    if (eventListeners == null) {
      eventListeners = new HashMap<>();
      events.put(eventType, eventListeners);
    }
    // if (eventListeners.containsKey(listenerId)) {
    // throw new IllegalArgumentException("Duplicate listener ID: " + listenerId);
    // }
    eventListeners.put(listenerId, listener);
  }

  public static void unsubscribe(String eventType, String listenerId) {
    Map<String, EventListener> eventListeners = events.get(eventType);
    if (eventListeners != null) {
      eventListeners.remove(listenerId);
    }
  }

  public static void notify(String eventType) {
    notify(eventType, null);
  }

  public static void notify(String eventType, Object data) {
    Map<String, EventListener> eventListeners = events.get(eventType);
    if (eventListeners != null) {
      for (EventListener listener : eventListeners.values()) {
        listener.update(data);
      }
    } else {
      throw new IllegalArgumentException("No such event type: " + eventType);
    }
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface Subscribe {
  }

  public static void register(Object obj) {
    for (Method method : obj.getClass().getMethods()) {
      Subscribe annotation = method.getAnnotation(Subscribe.class);
      if (annotation == null)
        continue;
      String eventName = method.getName();
      String listenerId = getOgString(obj);
      subscribe(eventName, listenerId, (data) -> {
        try {
          if (method.getParameterCount() == 0)
            method.invoke(obj);
          else if (method.getParameterCount() == 1)
            method.invoke(obj, data);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
  }

  public static void unregister(Object obj) {
    for (Method method : obj.getClass().getMethods()) {
      Subscribe annotation = method.getAnnotation(Subscribe.class);
      if (annotation != null) {
        String eventName = method.getName();
        String listenerId = getOgString(obj);
        unsubscribe(eventName, listenerId);
      }
    }
  }

  public static String getOgString(Object obj) {
    return obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
  }
}