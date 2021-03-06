package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to make a layer invisible. This is 'triggered' with the 'invisible'
 * command.
 */
public class MakeInvisible implements ICommand {

  protected final String layerName;

  /**
   * Constructs a MakeInvisible object.
   *
   * @param layerName the name of the layer to be made invisible.
   */
  public MakeInvisible(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void apply(IModel model) {
    model.setVisiblity(this.layerName, false);
  }
}
