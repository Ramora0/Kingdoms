package com.kingdoms.ui.images;

import java.util.function.Predicate;

import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

import processing.core.PImage;

public class BorderImage extends Image {
  public BorderImage(int x, int y, Predicate<Tile> neighbor, int color) {
    super();
    Predicate<Tile> nTest = (tile) -> tile == null || neighbor.test(tile);
    PImage borderImage = getBorder(nTest.test(World.getTile(x + 1, y)), nTest.test(World.getTile(x, y - 1)),
        nTest.test(World.getTile(x - 1, y)), nTest.test(World.getTile(x, y + 1)));
    image = borderImage == null ? null : new TintedImage(borderImage, color);
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
