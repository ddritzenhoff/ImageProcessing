package controller;

import cs3500.imageprocessing.model.Greyscale;
import cs3500.imageprocessing.model.IModel2;

public class GreyscaleCMD implements ICommand{

  public GreyscaleCMD() {}

  @Override
  public void exec(IModel2 model) {
    model.applyTransformation(new Greyscale());
  }
}
