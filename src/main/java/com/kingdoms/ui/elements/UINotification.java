package com.kingdoms.ui.elements;

import com.kingdoms.helpers.StaticSupplier;

public class UINotification extends UIContainer {
  final float upPixels = 100f / 1000;

  long startTime;
  float ogY;

  public UINotification(String notification, float x, float y) {
    super(7);

    startTime = System.currentTimeMillis();
    ogY = y;
    UIText text = new UIText(notification, new StaticSupplier<Float>(x),
        () -> ogY + (startTime - System.currentTimeMillis()) * upPixels, 20);
    children.add(text);
  }

  @Override
  public boolean shouldDie() {
    return System.currentTimeMillis() - startTime > 10000;
  }
}
