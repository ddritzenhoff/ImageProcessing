package controller;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ImageUtil;
import cs3500.imageprocessing.model.Sepia;
import cs3500.imageprocessing.model.Sharpen;
import java.awt.image.BufferedImage;
import java.util.List;
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
    model.applyTransformation(new Sepia());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleSharpenEvent() {
    //  String text = model.getData();
    // view.setText(text);
    model.applyTransformation(new Sharpen());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleGreyscaleEvent() {
    //  String text = model.getData();
    // view.setText(text);
    model.applyTransformation(new Sharpen());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleLoadEvent() {

//    model.addImageToLayer(view.getText());
//    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleWorkingLayerEvent() {
    String s = this.view.getClickedLayer();//"current working layer"
    System.out.println("current working layer: " + s);
    model.setWorkingLayer(s);
  }

  @Override
  public void handleBlurEvent() {
    model.applyTransformation(new Blur());
    showTopMostVisibleImageLayerEvent();

    //model.setData(textToSave);
    //model.setData("test");
    //view.askForFocus();
  }

//  @Override
//  public void handleToUpperCaseEvent(IViewEvent event) {
//    view.setText( view.getText().toUpperCase());
//  }

  public void updateLayerList() {
    List<String> layers = model.list();
    String[] s = new String[layers.size()];
    for(int i = 0 ; i < layers.size() ; i ++) {
      s[i] = layers.get(i);
    }
    view.setMenu(s);
  }

  public void handleAddLayerEvent() {
    model.addLayer(view.getText());
    System.out.println("Layer name " + view.getText() + " created ");
    updateLayerList();

  }

  @Override
  public void handleAddImageToLayerEvent() {
    model.addImageToLayer(view.getFileDest());

  }


  public void handleVisibilityEvent() {
  Boolean[] arr = view.getVisibility();
      for (int i = 0 ; i < model.list().size() ; i++) {
      model.setVisiblity(model.list().get(i),arr[i]);
    }
  }

  //controller ->  model - > controller -> image -> view
  public void showTopMostVisibleImageLayerEvent() {
    BufferedImage b = model.topLayerImage();
      view.setImage(b);


  }

  /**
   * Keep processing layers and IPixelImages until the user quits.
   */
  @Override
  public void startProcessing() {
    updateLayerList();
  }
}
