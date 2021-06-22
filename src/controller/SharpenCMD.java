package controller;

import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.Sharpen;

/**
 * Represents a function-object to sharpen the IPixelImage within the current working layer. This is
 * 'triggered' with the 'sharpen' command.
 */
public class SharpenCMD implements ICommand {

  /**
   * Constructs a SharpenCMD object.
   */
  public SharpenCMD() {
    //none
  }

  @Override
  public void apply(IModel model) {
    model.applyTransformation(new Sharpen());
  }
}
