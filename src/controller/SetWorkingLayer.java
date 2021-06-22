package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to add change the working layer to the specified layer. This is
 * 'triggered' with the 'current' command.
 */
public class SetWorkingLayer implements ICommand {

  protected final String layerName;

  /**
   * Constructs a SetWorkingLayer object.
   *
   * @param layerName the name of the layer to be set to the current working one.
   */
  public SetWorkingLayer(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void apply(IModel model) {
    model.setWorkingLayer(this.layerName);
  }
}
