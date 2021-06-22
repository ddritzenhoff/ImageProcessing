package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to add an IPixelImage to a layer. This is 'triggered' with the
 * 'load' command.
 */
public class AddImageToLayer implements ICommand {

  protected final String imageFileName;

  /**
   * Constructs an AddImageToLayer object.
   *
   * @param imageFileName the file to be loaded into the layer.
   */
  public AddImageToLayer(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  @Override
  public void apply(IModel model) {
    model.addImageToLayer(this.imageFileName);
  }
}
