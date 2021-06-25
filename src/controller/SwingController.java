package controller;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.Greyscale;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ProcessingModel;
import cs3500.imageprocessing.model.Sepia;
import cs3500.imageprocessing.model.Sharpen;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import view.IView;
import view.IViewListener;

/**
 * Represents the Controller responsible for handling GUI input. Translates button actions into
 * actual changes within the model and view.
 */
public class SwingController implements IProcessingController, IViewListener {

  private final IModel model;
  private final IView view;

  /**
   * Constructs a SwingController object.
   *
   * @param model the model to be worked with.
   * @param view  the gui view.
   */
  public SwingController(IModel model, IView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.registerViewEventListener(this);
  }

  @Override
  public void handleSepiaEvent() {

    model.applyTransformation(new Sepia());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleSharpenEvent() {

    model.applyTransformation(new Sharpen());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleGreyscaleEvent() {

    model.applyTransformation(new Greyscale());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleBlurEvent() {
    model.applyTransformation(new Blur());
    showTopMostVisibleImageLayerEvent();

  }

  @Override
  public void handleWorkingLayerEvent() {
    try {
      String s = this.view.getClickedLayer();
      view.writeError("Current working layer: " + s);
      //System.out.println("current working layer: " + s);
      model.setWorkingLayer(s);
    } catch (Exception e) {
      view.writeError("No working layer");
    }
  }

  /**
   * Updates the list of layers within the view.
   */
  public void updateLayerList() {
    List<String> layers = List.copyOf(model.list());
    view.setMenu(layers);
  }

  @Override
  public void handleExportEvent() {
    model.exportDirectory(view.getSaveAllFilePath());
  }

  @Override
  public void handleAddImageToLayerEvent() {
    model.addImageToLayer(view.getFileDest());
  }

  @Override
  public void handleVisibilityEvent() {
    List<Boolean> arr = view.getVisibility();
    for (int i = 0; i < model.list().size(); i++) {
      model.setVisiblity(model.list().get(i), arr.get(i));
    }

  }

  @Override
  public void loadVisibility() {
    view.setVisibility(model.getVisibility());
  }

  /**
   * Handle the case when the load checkerboard event is triggered.
   */
  @Override
  public void handleAddCheckerboardEvent() {
    model.generateCheckerboard(view.getBoxWidth(), view.getNumBoxes());
    showTopMostVisibleImageLayerEvent();
  }


  @Override
  public void handleLoadScriptEvent() {
    IModel testModel = new ProcessingModel();

    Readable rd = null;
    try {
      rd = new InputStreamReader(new FileInputStream(view.getScript()));
      IProcessingController processingController1 = new ProcessingController(testModel, rd,
          System.out);
      processingController1.startProcessing();
      view.writeError("Script ran successfully. Check /res folder.");
    } catch (FileNotFoundException e) {
      view.writeError(e.getMessage());
    }

  }


  @Override
  public void handleDeleteLayerEvent() {
    try {
      model.deleteLayer();
      String s = this.view.getClickedLayer();
      view.removeLayer(s);
      updateLayerList();
      handleWorkingLayerEvent();

    } catch (Exception e) {
      if (model.list().size() == 0) {
        view.writeError("Empty Layer List");
      } else {
        view.writeError("Error: Illegal Operation");
      }
    }


  }

  @Override
  public void handleAddLayerEvent() {
    try {
      model.addLayer(view.getText());
      System.out.println("Layer name " + view.getText() + " created ");
      updateLayerList();
      view.addLayer(view.getText());
      updateLayerList();
      handleWorkingLayerEvent();
    } catch (Exception e) {
      view.writeError("Error: Unsupported Operation");
    }

  }


  @Override
  public void showTopMostVisibleImageLayerEvent() {
    try {
      BufferedImage b;
      b = model.topLayerImage();
      view.setImage(b);
    } catch (Exception e) {
      view.writeError("Error: " + e.getMessage());
    }
  }

  @Override
  public void handleLoadAllEvent() {

    model.loadModel(view.getLoadedModelFileDest());
    updateLayerList();
    loadVisibility();
    handleWorkingLayerEvent();

  }

  @Override
  public void handleSaveAllEvent() {
    model.exportAll(view.getSaveAllFilePath());
  }


  @Override
  public void startProcessing() {
    updateLayerList();
  }
}
