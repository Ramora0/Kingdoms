package com.kingdoms;

import com.jogamp.opengl.GLProfile;
import com.kingdoms.helpers.canvas.Constants;
import com.kingdoms.helpers.events.EventBus;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.scenes.game.GameScene;
import com.kingdoms.ui.shaders.Shaders;
import com.kingdoms.world.World;

import processing.core.PApplet;
import processing.event.MouseEvent;

public class Kingdoms extends PApplet {
  public static PApplet canvas;

  public void settings() {
    GLProfile.initSingleton();
    size(1200, 800, P2D);
  }

  public void setup() {
    Kingdoms.canvas = this;

    Constants.setDimensions(width, height);

    Shaders.loadShaders(canvas);

    EventBus.subscribeStaticMethods();
    UI.init();

    // TODO: Have this reference a testing variable so I can turn that off
    World.generateWorld(50);
    // World.tiles[10][10].build(new Farm(World.tiles[10][10], World.me));
    // World.tiles[10][10].addTroops(new Soldier(World.tiles[10][10], World.me,
    // 10));
    UI.changeScene(new GameScene());
  }

  public void draw() {
    background(255);
    textAlign(CENTER, CENTER);
    UI.display(canvas);
  }

  public void mouseWheel(MouseEvent event) {
    EventBus.notify("mouseWheel", event.getCount());
  }

  public void mousePressed() {
    EventBus.notify("mousePressed", canvas);
  }

  public void mouseDragged() {
    EventBus.notify("mouseDragged", canvas);
  }

  public void keyPressed() {
    EventBus.notify("keyPressed", canvas);
  }

  public void keyDragged() {
    EventBus.notify("keyDragged", canvas);
  }

  public static void main(String[] passedArgs) {
    String[] processingArgs = { "Kingdoms" };
    Kingdoms mySketch = new Kingdoms();
    PApplet.runSketch(processingArgs, mySketch);
  }
}
