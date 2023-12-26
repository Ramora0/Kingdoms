package com.kingdoms.network.instructions;

import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;
import com.kingdoms.world.buildings.Building;
import com.kingdoms.world.buildings.Building.BuildingType;
import com.kingdoms.world.buildings.City;
import com.kingdoms.world.buildings.Farm;

/** BuildOption represents a kind of building to build */
public enum BuildOption {
  CITY {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
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
  },
  FARM {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      if (!World.in(x, y))
        return false;
      Tile tile = World.tiles[x][y];

      if (!World.hasAdjacentTile(x, y, (t) -> {
        Building building = t.getBuilding();
        if (building == null)
          return false;
        if (building.getPlayer() != player)
          return false;
        return building.getType() == BuildingType.CITY
            || building.getType() == BuildingType.FARM;
      })) {
        return false;
      }
      return !tile.hasBuilding() && tile.isLand();
    }

    @Override
    protected void buildAt(Player player, int x, int y) {
      Tile tile = World.tiles[x][y];
      tile.build(new Farm(tile, player));
    }
  };

  public abstract boolean canBuildAt(Player player, int x, int y);

  protected abstract void buildAt(Player player, int x, int y);
};
