package controller;

import cs3500.imageprocessing.model.IModel2;

public class SetWorkingLayer implements ICommand {

  protected final String layerName;

  public SetWorkingLayer(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void exec(IModel2 model) {
    model.setWorkingLayer(this.layerName);
  }
}
