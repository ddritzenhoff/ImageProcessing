package controller;

import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ImageUtil;
import cs3500.imageprocessing.model.ProcessingModel;

/**
 * Represents a function-object to read all of the contents of a directory and loading it into the
 * model. This is 'triggered' with the 'read-all' command.
 */
public class ReadAll implements ICommand {

  protected final String directoryName;

  /**
   * Constructs a ReadAll object.
   *
   * @param directoryName the name of the directory that contains all the layers to be loaded.
   */
  public ReadAll(String directoryName) {
    this.directoryName = directoryName;
  }

  @Override
  public void go(IModel model) {
    // TODO: QUESTION: is this the correct command?
    //  (had to make the readAll method public)
    model.
    model = new ProcessingModel(ImageUtil.readAll(directoryName));
  }
}
