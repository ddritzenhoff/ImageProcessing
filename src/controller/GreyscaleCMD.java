package controller;

import cs3500.imageprocessing.model.Greyscale;
import cs3500.imageprocessing.model.IModel2;

/**
 * Represents a function-object to greyscale an IPixelImage within a layer.
 * This is 'triggered' with the 'greyscale' command.
 */
public class GreyscaleCMD implements ICommand {

  /**
   * Constructs a GreyscaleCMD object.
   */
  public GreyscaleCMD() {}

  @Override
  public void go(IModel2 model) {
    model.applyTransformation(new Greyscale());
  }
}
