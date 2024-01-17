package com.kingdoms.network.instructions;

import com.kingdoms.world.Player;
import com.kingdoms.world.Tile;
import com.kingdoms.world.World;
import com.kingdoms.world.buildings.Building;
import com.kingdoms.world.buildings.City;
import com.kingdoms.world.buildings.Farm;
import com.kingdoms.world.buildings.TrainingCamp;

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
    @SuppressWarnings("deprecation")
    protected void buildAt(Player player, int x, int y) {
      CITY.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new City(tile, player));
    }
  },
  FARM(100) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = FARM.getTileIfAllowed(player, x, y);

      if (tile == null || !tile.isLand())
        return false;

      return World.hasAdjacentTile(x, y, (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void buildAt(Player player, int x, int y) {
      FARM.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new Farm(tile, player));
    }
  },
  TRAINING_CAMP(200) {
    @Override
    public boolean canBuildAt(Player player, int x, int y) {
      Tile tile = TRAINING_CAMP.getTileIfAllowed(player, x, y);

      if (tile == null || !tile.isLand())
        return false;

      return World.hasAdjacentTile(x, y, (t) -> {
        Building building = t.getBuilding();
        return building != null && building.getPlayer() == player;
      });
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void buildAt(Player player, int x, int y) {
      TRAINING_CAMP.preBuild(player, x, y);
      Tile tile = World.tiles[x][y];
      tile.build(new TrainingCamp(tile, player));
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
    if (player.getResources() < cost)
      return null;

    Tile tile = World.getTile(x, y);
    if (tile == null || tile.hasBuilding())
      return null;
    return tile;
  }

  protected abstract void buildAt(Player player, int x, int y);

  private void preBuild(Player player, int x, int y) {
    player.addResources(-cost);
  }
};
