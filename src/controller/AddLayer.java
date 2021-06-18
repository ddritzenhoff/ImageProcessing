package controller;

import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.IModel2;

public class AddLayer implements ICommand {

  protected final String layerName;

  public AddLayer(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void exec(IModel2 model) {
    model.addLayer(layerName);
  }
}
