package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * represents a function-object in which a string command from the user is translated into
 * a model operation.
 */
public interface ICommand {

  /**
   * Performs a function relative to the implementation.
   * @param model the model containing all of the layers and IPixelImages.
   */
  public void go(IModel model);

}
