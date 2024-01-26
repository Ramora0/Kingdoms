package com.kingdoms.ui.images;

import java.util.function.Predicate;

import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.core.PApplet;
import processing.core.PImage;

public class BorderImage extends Image {
  int color;

  public BorderImage(int x, int y, Predicate<Tile> neighbor, int color) {
    super(null);
    image = getBorder(neighbor.test(World.getTile(x + 1, y)), neighbor.test(World.getTile(x, y - 1)),
        neighbor.test(World.getTile(x - 1, y)), neighbor.test(World.getTile(x, y + 1)));
    this.color = color;
  }

  public void display(PApplet canvas, float x, float y) {
    canvas.tint(color);
    super.display(canvas, x, y);
    canvas.noTint();
  }

  /**
   * Gets the border image from the given borders where true indicates a border
   */
  public static PImage getBorder(boolean right, boolean top, boolean left, boolean bottom) {
    short border = (short) (15 - ((right ? 1 : 0) << 3 | (top ? 1 : 0) << 2 | (left ? 1 : 0) << 1 | (bottom ? 1 : 0)));
    if (border == 0)
      return null;
    return ImageManager.getImage("images/borders/border" + border + ".png");
  }

  public boolean isUseless() {
    return image == null;
  }
}
