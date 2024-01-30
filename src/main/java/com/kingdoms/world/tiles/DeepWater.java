package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;

public class DeepWater extends Tile {
  public DeepWater(int x, int y) {
    super(x, y, Biome.DEEP_WATER, true);
  }
  
  @Deprecated
  public DeepWater() {}

  @Override
  public Sprite getSprite() {
    return new Sprite(new ColorImage(Colors.DEEP_WATER),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.SHALLOW_WATER), Colors.SHALLOW_WATER),
        new BorderImage(x, y, (tile) -> tile.isWater(), Colors.CLIFF));
  }
}
