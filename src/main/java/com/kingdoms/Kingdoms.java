package com.kingdoms;

import com.kingdoms.events.EventBus;
import com.kingdoms.ui.UI;

import processing.core.PApplet;

public class Kingdoms extends PApplet {
  public static PApplet canvas;

  public void settings() {
    size(1200, 800);
    // smooth(0);
  }

  public void setup() {
    Kingdoms.canvas = this;

    UI.init();

    // Test
    // World.generateWorld(100);
    // UI.changeScene(new MainScene());
  }

  public void draw() {
    background(255);
    textAlign(CENTER, CENTER);
    // translate(600, 400);
    UI.display(canvas);
  }

  public void mousePressed() {
    EventBus.notify("mousePressed", canvas);
  }

  public static void main(String[] passedArgs) {
    String[] processingArgs = { "Kingdoms" };
    Kingdoms mySketch = new Kingdoms();
    PApplet.runSketch(processingArgs, mySketch);
  }
}
