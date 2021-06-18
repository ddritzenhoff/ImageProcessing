package controller;

import cs3500.imageprocessing.model.IModel2;
import cs3500.imageprocessing.model.Sepia;

public class SepiaCMD implements ICommand{

  public SepiaCMD() {}

  @Override
  public void exec(IModel2 model) {
    model.applyTransformation(new Sepia());
  }
}
