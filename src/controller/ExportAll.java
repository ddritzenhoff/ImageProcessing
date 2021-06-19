package controller;

import cs3500.imageprocessing.model.IModel;

/**
 * Represents a function-object to export all of the layers within the model. This is 'triggered'
 * with the 'save-all' command.
 */
public class ExportAll implements ICommand {

  protected final String directoryName;

  /**
   * Constructs an ExportAll object.
   *
   * @param directoryName the name of the directory in which all of the contents will be exported
   *                      into.
   */
  public ExportAll(String directoryName) {
    this.directoryName = directoryName;
  }

  @Override
  public void go(IModel model) {
    model.exportAll(directoryName);
  }
}
