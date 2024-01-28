package com.kingdoms.world.tiles;

import com.kingdoms.helpers.canvas.Colors;
import com.kingdoms.ui.images.BorderImage;
import com.kingdoms.ui.images.ColorImage;
import com.kingdoms.ui.images.Sprite;

public class Plains extends Tile {
  public Plains(int x, int y) {
    super(x, y, false);
  }

  @Override
  public Sprite getSprite() {
    return new Sprite(new ColorImage(Colors.PLAINS),
        new BorderImage(x, y, (tile) -> !tile.isWater, Colors.BEACH));
  }
}
