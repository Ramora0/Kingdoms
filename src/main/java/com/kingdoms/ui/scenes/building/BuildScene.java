package com.kingdoms.ui.scenes.building;

import com.kingdoms.network.instructions.BuildOption;
import com.kingdoms.ui.scenes.WorldDisplayScene;

public class BuildScene extends WorldDisplayScene {
  BuildOption option;

  public BuildScene(BuildOption option) {
    super();
    this.option = option;
  }

  // TODO: click somewhere to send the build instruction
}
