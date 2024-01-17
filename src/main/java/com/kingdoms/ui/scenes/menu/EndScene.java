package com.kingdoms.ui.scenes.menu;

import com.kingdoms.ui.elements.UIText;
import com.kingdoms.ui.scenes.Scene;

public class EndScene extends Scene {
  boolean won;

  public EndScene(boolean won) {
    super();
    this.won = won;

    if (won)
      addElement(new UIText("You won!", 600, 300, 40));
    else
      addElement(new UIText("You lost!", 600, 300, 40));
  }
}
