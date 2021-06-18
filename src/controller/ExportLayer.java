package controller;

import cs3500.imageprocessing.model.IModel2;

public class ExportLayer implements ICommand {

  protected final String newFileName;
  public ExportLayer(String newFileName) {
      this.newFileName = newFileName;
  }

  @Override
  public void exec(IModel2 model) {
    model.exportLayer(this.newFileName);
  }
}
