package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;

public class ShallowWater extends Tile {
  public ShallowWater(int x, int y) {
    super(x, y, Biome.SHALLOW_WATER, true);
  }

  @Override
  public Sprite getSprite() {
    return new Sprite(new ColorImage(Colors.SHALLOW_WATER),
        new BorderImage(x, y, (tile) -> tile.isWater, Colors.BEACH));
  }
}
