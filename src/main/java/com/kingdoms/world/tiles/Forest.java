package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;

public class Forest extends Tile {
  public Forest(int x, int y) {
    super(x, y, Biome.FOREST, false);
  }

  @Override
  public Sprite getSprite() {
    return new Sprite(new ColorImage(Colors.FOREST),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.SHALLOW_WATER), Colors.BEACH),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.DEEP_WATER), Colors.CLIFF));
  }
}
