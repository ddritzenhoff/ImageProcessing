package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to create an empty layer which can then accept an image. This is
 * 'triggered' with the 'create-layer' command.
 */
public class AddLayer implements ICommand {

  protected final String layerName;

  /**
   * Constructs an AddLayer object.
   *
   * @param layerName the name of the layer to be created.
   */
  public AddLayer(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void apply(IModel model) {
    model.addLayer(layerName);
  }
}
