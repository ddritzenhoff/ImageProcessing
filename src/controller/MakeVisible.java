package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to make a layer visible. This is 'triggered' with the 'visible'
 * command.
 */
public class MakeVisible implements ICommand {

  protected final String layerName;

  /**
   * Constructs a MakeVisible object.
   *
   * @param layerName the name of the layer to be made visible.
   */
  public MakeVisible(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void apply(IModel model) {
    model.setVisiblity(this.layerName, true);
  }
}
