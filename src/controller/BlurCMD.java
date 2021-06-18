package controller;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.IModel2;

public class BlurCMD implements ICommand {

  public BlurCMD() {
  }

  @Override
  public void exec(IModel2 model) {
    model.applyTransformation(new Blur());
  }
}
