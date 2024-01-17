package com.kingdoms.ui.shaders;

import processing.core.PApplet;
import processing.core.PVector;
import processing.opengl.PShader;

public class Shaders {
  static PShader scaleShader;

  public static void loadShaders(PApplet canvas) {
    scaleShader = canvas.loadShader("scale_shader.frag");
    scaleShader.set("resolution", new PVector((float) canvas.width, (float) canvas.height));
    scaleShader.set("scaleFactor", 1f);
  }

  public static void setScale(float scale) {
    scaleShader.set("scaleFactor", scale);
  }

  public static void applyScaleShader(PApplet canvas) {
    scaleShader.set("resolution", new PVector((float) canvas.width, (float) canvas.height));
    canvas.filter(scaleShader);
  }

  public static void resetShader(PApplet canvas) {
    canvas.resetShader();
  }
}
