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
  CITY(500) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = CITY.getTileIfAllowed(player, x, y);
      if (tile == null)
        return false;
      return tile.isLand();
    }

    @Override
    protected void buildAt(Player player, int x, int y) {
      Tile tile = World.tiles[x][y];
      tile.build(new City(tile, player));
    }
  },
  FARM(100) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = FARM.getTileIfAllowed(player, x, y);

      if (tile == null)
        return false;

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
      return tile.isLand();
    }

    @Override
    protected void buildAt(Player player, int x, int y) {
      Tile tile = World.tiles[x][y];
      tile.build(new Farm(tile, player));
    }
  };

  private int cost;

  BuildOption(int cost) {
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

  public abstract boolean canBuildAt(Player player, int x, int y);

  /** Only used by enums for common checks */
  private Tile getTileIfAllowed(Player player, int x, int y) {
    if (!World.in(x, y))
      return null;
    if (player.getResources() < cost)
      return null;

    Tile tile = World.tiles[x][y];
    if (tile.hasBuilding())
      return null;
    return tile;
  }

  protected abstract void buildAt(Player player, int x, int y);
};
