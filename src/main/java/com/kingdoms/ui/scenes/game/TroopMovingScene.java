package com.kingdoms.ui.scenes.game;

import java.util.ArrayList;
import java.util.List;

import com.kingdoms.helpers.events.EventBus.Subscribe;
import com.kingdoms.network.instructions.SetTroopPathInstruction;
import com.kingdoms.ui.UI;
import com.kingdoms.ui.elements.UIButton;
import com.kingdoms.world.World;
import com.kingdoms.world.tiles.Tile;
import com.kingdoms.world.troops.Troop;

import processing.core.PApplet;

public class TroopMovingScene extends WorldDisplayScene {
  List<Tile> path;
  Troop troop;

  public TroopMovingScene(Troop troop) {
    super();

    path = new ArrayList<>(troop.getPath());

    this.troop = troop;

    UIButton cancel = (UIButton) new UIButton("Cancel", 5, 5, 30, () -> UI.changeScene(new GameScene())).setTopLeft();
    UIButton reset = (UIButton) new UIButton("Reset", 5, 40, 30, () -> path = new ArrayList<>())
        .below(cancel, 10).setLeft();
    UIButton confirm = (UIButton) new UIButton("Confirm", 5, 75, 30, () -> {
      // troop.setPath(path);
      World.receiveInstruction(new SetTroopPathInstruction(troop, path));
      UI.changeScene(new GameScene());
    }).below(reset, 10).setLeft();

    addElement(cancel);
    addElement(reset);
    addElement(confirm);
  }

  @Override
  public void display(PApplet canvas) {
    super.display(canvas);

    path.add(0, troop.getTile());
    for (int i = 0; i < path.size() - 1; i++) {
      Tile a = path.get(i);
      Tile b = path.get(i + 1);

      canvas.line((float) a.getX() + 0.5f, (float) a.getY() + 0.5f, (float) b.getX() + 0.5f, (float) b.getY() + 0.5f);
    }
    path.remove(0);
  }

  @Subscribe
  public void mousePressed(Object data) {
    PApplet canvas = (PApplet) data;
    Tile tile = WorldDisplayScene.getHighlightedTile(canvas);

    if (tile != null && (path.size() == 0 ? Tile.neighbors(troop.getTile(), tile)
        : Tile.neighbors(path.get(path.size() - 1), tile)) && !path.contains(tile)) {
      path.add(tile);
    }
  }
}
