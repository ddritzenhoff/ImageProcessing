package controller;

import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.IModel2;

public interface ICommand {

  public void exec(IModel2 model);

}
