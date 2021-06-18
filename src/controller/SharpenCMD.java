package controller;

import cs3500.imageprocessing.model.IModel2;
import cs3500.imageprocessing.model.Sharpen;

public class SharpenCMD implements ICommand{

  public SharpenCMD() {}

  @Override
  public void exec(IModel2 model) {
    model.applyTransformation(new Sharpen());
  }
}
