
import controller.IProcessingController;
import controller.SwingController;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ProcessingModel;

import view.IView;

import view.SwingView;

public class Main {
  public static void main(String [] args){
    IModel model = new ProcessingModel();
    IView swingView = new SwingView();


    IProcessingController swingController = new SwingController(model, swingView);
      swingController.startProcessing();
  }
}
