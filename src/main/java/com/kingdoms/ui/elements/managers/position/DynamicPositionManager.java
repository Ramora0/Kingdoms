package com.kingdoms.ui.elements.managers.position;

import java.util.function.Supplier;

import com.kingdoms.ui.elements.managers.Managers.PositionManager;

public class DynamicPositionManager implements PositionManager {
  Supplier<Float> x, y;

  public DynamicPositionManager(Supplier<Float> x, Supplier<Float> y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public float baseGetX() {
    return x.get();
  }

  @Override
  public float baseGetY() {
    return y.get();
  }
}
