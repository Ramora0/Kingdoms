package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Image;
import com.kingdoms.ui.images.ImageManager;
import com.kingdoms.ui.images.Sprite;

public class Forest extends Tile {
  public Forest(int x, int y) {
    super(x, y, Biome.FOREST, false);
  }

  @Override
  public Sprite getSprite() {
    Sprite sprite = new Sprite(new ColorImage(Colors.FOREST));

    if (MathUtils.chance(0.3))
      sprite.addImage(new Image(ImageManager.getImage("images/nature/grass.png")));

    sprite.addImages(new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.SHALLOW_WATER), Colors.BEACH),
        new BorderImage(x, y, (tile) -> !tile.getBiome().equals(Biome.DEEP_WATER), Colors.CLIFF));
    return sprite;
  }
}
