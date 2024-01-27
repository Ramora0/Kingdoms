package com.kingdoms.ui.images;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Sprite {
  List<Image> images;

  public Sprite(Image... images) {
    this.images = new ArrayList<>(List.of(images));

    for (int i = this.images.size() - 1; i >= 0; i--) {
      if (this.images.get(i).isUseless())
        this.images.remove(i);
    }
  }

  public void addImage(Image image) {
    if (!image.isUseless())
      images.add(image);
  }

  public void display(PApplet canvas, float x, float y) {
    for (Image image : images) {
      image.display(canvas, x, y);
    }
  }
}
