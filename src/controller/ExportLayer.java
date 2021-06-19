package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to export an IPixelImage into the specified filetype (within the
 * string). This is 'triggered' with the 'save' command.
 */
public class ExportLayer implements ICommand {

  protected final String newFileName;

  /**
   * Constructs an ExportLayer object.
   *
   * @param newFileName the name and type of file of the working layer IPixelImage.
   */
  public ExportLayer(String newFileName) {
    this.newFileName = newFileName;
  }

  @Override
  public void go(IModel model) {
    model.exportLayer(this.newFileName);
  }
}
