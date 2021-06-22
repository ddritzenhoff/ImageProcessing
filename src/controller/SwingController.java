package controller;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.IModel;
import java.util.Objects;
import view.IView;
import view.IViewEvent;
import view.IViewListener;

public class SwingController implements IProcessingController, IViewListener {

  private final IModel model;
  private final IView view; // no interface yet, but we'll get there.

  public SwingController(IModel model, IView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.registerViewEventListener(this);
  }

  @Override
  public void handleSepiaEvent() {
  //  String text = model.getData();
   // view.setText(text);
    view.askForFocus();
  }

  @Override
  public void handleLoadEvent(IViewEvent event) {

  }

  @Override
  public void handleWorkingLayerEvent(IViewEvent event) {
    String s = event.toString();
    model.setWorkingLayer(s);
  }

  @Override
  public void handleBlurEvent(IViewEvent event) {
    String textToSave = view.getText();
    model.applyTransformation(new Blur());

    //model.setData(textToSave);
    //model.setData("test");
    //view.askForFocus();
  }

//  @Override
//  public void handleToUpperCaseEvent(IViewEvent event) {
//    view.setText( view.getText().toUpperCase());
//  }

  public void updateLayerList() {
    String[] s = model.toString().split(" ");
    view.setMenu(s);
  }

  public void handleAddLayerEvent() {

  }

  /**
   * Keep processing layers and IPixelImages until the user quits.
   */
  @Override
  public void startProcessing() {

  }
}
