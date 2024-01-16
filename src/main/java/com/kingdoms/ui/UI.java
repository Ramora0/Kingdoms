package com.kingdoms.ui;

import com.kingdoms.ui.scenes.Scene;
import com.kingdoms.ui.scenes.network.OpeningScene;

import processing.core.PApplet;

public class UI {
  public static Scene currentScene;

  public static void init() {
    currentScene = new OpeningScene();
  }

  public static void changeScene(Scene scene) { // Perhaps subscribe / unsubscribe WorldDisplayScene events here?
    currentScene.kill();
    currentScene = scene;
    // System.out.println("Changed scene to " + scene.getClass().getName());
  }

  public static void display(PApplet canvas) {
    currentScene.display(canvas);
  }
}
