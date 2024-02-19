package com.kingdoms.world.buildings;

import com.kingdoms.world.Player;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;

/** BuildOption represents a kind of building to build */
public enum BuildingType {
  // TODO: Just move this stuff to the actual classes
  CITY(City.class, 500) {
    @Override
    public boolean canBuildAt(Player player, Tile tile) {
      return !tile.isWater();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, Tile tile) {
      tile.build(new City(tile, player));
    }
  },
  FARM(Farm.class, 300) {
    @Override
    public boolean canBuildAt(Player player, Tile tile) {
      if (tile.isWater())
        return false;

      return World.hasAdjacentTile(tile.getX(), tile.getY(), (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, Tile tile) {
      FARM.preBuild(player, tile);
      tile.build(new Farm(tile, player));
    }
  },
  TRAINING_CAMP(TrainingCamp.class, 500) {
    @Override
    public boolean canBuildAt(Player player, Tile tile) {
      if (tile.isWater())
        return false;

      return World.hasAdjacentTile(tile.getX(), tile.getY(), (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    public void buildAt(Player player, Tile tile) {
      TRAINING_CAMP.preBuild(player, tile);
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

  public abstract boolean canBuildAt(Player player, Tile tile);

  public abstract void buildAt(Player player, Tile tile);

  private void preBuild(Player player, Tile tile) {
    player.addResources(-cost);
  }
};
