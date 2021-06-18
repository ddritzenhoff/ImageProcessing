package controller;

import cs3500.imageprocessing.model.IModel2;

public class AddImageToLayer implements ICommand {

  protected final String imageFileName;

  public AddImageToLayer(String imageFileName) {
    this.imageFileName = imageFileName;
  }

  @Override
  public void exec(IModel2 model) {
    model.addImageToLayer(this.imageFileName);
  }
}
