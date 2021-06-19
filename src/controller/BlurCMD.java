package controller;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to create blur an IPixelImage within the working layer.
 * This is 'triggered' with the 'blur' command.
 */
public class BlurCMD implements ICommand {

  /**
   * Constructs a BlurCMD object.
   */
  public BlurCMD() {}

  @Override
  public void go(IModel model) {
    model.applyTransformation(new Blur());
  }
}
