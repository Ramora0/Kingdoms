package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Image;
import com.kingdoms.ui.images.ImageManager;
import com.kingdoms.ui.images.Sprite;

public class Plains extends Tile {
  public Plains(int x, int y) {
    super(x, y, Biome.PLAINS, false);
  }
  
  @Deprecated
  public Plains() {}

  @Override
  public Sprite getSprite() {
    Sprite sprite = new Sprite(new ColorImage(Colors.PLAINS));

    if (MathUtils.chance(0.1))
      sprite.addImage(new Image(ImageManager.getImage("images/nature/grass.png")));

    sprite.addImages(
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.SHALLOW_WATER), Colors.BEACH),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.DEEP_WATER), Colors.CLIFF),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.FOREST), Colors.FOREST));
    return sprite;
  }
}
