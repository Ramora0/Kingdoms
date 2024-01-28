package com.kingdoms.world.buildings;

import com.kingdoms.world.Player;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

/** BuildOption represents a kind of building to build */
public enum BuildingType {
  // TODO: Just move this stuff to the actual classes
  CITY(City.class, 500) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = CITY.getTileIfAllowed(player, x, y);
      if (tile == null)
        return false;
      return !tile.isWater();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, int x, int y) {
      CITY.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new City(tile, player));
    }
  },
  FARM(Farm.class, 300) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = FARM.getTileIfAllowed(player, x, y);

      if (tile == null || tile.isWater())
        return false;

      return World.hasAdjacentTile(x, y, (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, int x, int y) {
      FARM.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new Farm(tile, player));
    }
  },
  TRAINING_CAMP(TrainingCamp.class, 500) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = TRAINING_CAMP.getTileIfAllowed(player, x, y);

      if (tile == null || tile.isWater())
        return false;

      return World.hasAdjacentTile(x, y, (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, int x, int y) {
      TRAINING_CAMP.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new TrainingCamp(tile, player));
    }
  };

  private int cost;

  public int getCost() {
    return cost;
  }

  public Class<? extends Building> clazz;

  BuildingType(Class<? extends Building> clazz, int cost) {
    this.clazz = clazz;
    this.cost = cost;
  }

  public abstract boolean canBuildAt(Player player, int x, int y);

  /** Only used by enums for common checks */
  private Tile getTileIfAllowed(Player player, int x, int y) {
    if (player.getResources() < cost)
      return null;

    Tile tile = World.getTile(x, y);
    if (tile == null || tile.hasBuilding())
      return null;
    return tile;
  }

  public abstract void buildAt(Player player, int x, int y);

  private void preBuild(Player player, int x, int y) {
    player.addResources(-cost);
  }
};
