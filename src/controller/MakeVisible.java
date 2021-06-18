package controller;

import cs3500.imageprocessing.model.IModel2;

public class MakeVisible implements ICommand {

  protected final String layerName;

  public MakeVisible(String layerName) {
    this.layerName = layerName;
  }

  @Override
  public void exec(IModel2 model) {
    model.setVisiblity(this.layerName, true);
  }
}
