package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to remove a layer the working layer. This is 'triggered' with the
 * 'remove' command.
 */
public class RemoveLayer implements ICommand {

  /**
   * Constructs a RemoveLayer object.
   */
  public RemoveLayer() {
    //empty
  }

  @Override
  public void apply(IModel model) {
    model.deleteLayer();
  }
}
