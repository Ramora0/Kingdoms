package com.kingdoms.world.buildings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.kingdoms.helpers.math.MathUtils;
import com.kingdoms.ui.images.ImageManager;
import com.kingdoms.ui.images.Sprite;
import com.kingdoms.world.Player;
import com.kingdoms.world.tiles.Tile;

import processing.core.PImage;
import processing.data.JSONObject;

public class Farm extends Building {
  public Farm(Tile tile, Player player) {
    super(BuildingType.FARM, tile, player);
  }

  @Deprecated
  public Farm() {
    super(BuildingType.FARM);
  }

  public void initSprite() {
    List<Supplier<PImage>> images = new ArrayList<>();
    images.add(() -> ImageManager.getImage("images/buildings/farm1.png"));
    images.add(() -> ImageManager.getTeamImage("images/buildings/farm0.png", player.getColor()));
    sprite = new Sprite(MathUtils.random(images).get());
  }

  @Override
  public void doUpdate() {
    player.addResources(100);
  }

  @Override
  public JSONObject toJSON() {
    return super.mainToJSON();
  }

  @Override
  public void fromJSON(JSONObject json) {
    super.mainFromJSON(json);
  }
}
