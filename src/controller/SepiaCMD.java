package controller;

import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.Sepia;

/**
 * Represents a function-object to add the sepia transoformation to the image within the layer. This
 * is 'triggered' with the 'sepia' command.
 */
public class SepiaCMD implements ICommand {

  /**
   * Constructs a SepiaCMD object.
   */
  public SepiaCMD() {
    //none
  }

  @Override
  public void apply(IModel model) {
    model.applyTransformation(new Sepia());
  }
}
