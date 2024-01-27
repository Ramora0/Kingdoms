package com.kingdoms.ui.images;

import com.kingdoms.world.tiles.Tile;

public class ColorImage extends Image {
  public ColorImage(int color, int width, int height) {
    super(ImageManager.getColorImage(color, width, height));
  }

  public ColorImage(int color) {
    this(color, Tile.TILE_WIDTH, Tile.TILE_WIDTH);
  }
}
