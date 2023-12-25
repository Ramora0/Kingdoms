package com.kingdoms.network.instructions;

import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;
import com.kingdoms.world.buildings.City;

/** BuildOption represents a kind of building to build */
public enum BuildOption {
  CITY {
    @Override
    public boolean canBuildAt(int x, int y) {
      if (!World.in(x, y))
        return false;
      Tile tile = World.tiles[x][y];
      return !tile.hasBuilding() && tile.isLand();
    }

    @Override
    protected void buildAt(Player player, int x, int y) {
      Tile tile = World.tiles[x][y];
      tile.build(new City(tile, player));
    }
  };

  public abstract boolean canBuildAt(int x, int y);

  protected abstract void buildAt(Player player, int x, int y);
};
