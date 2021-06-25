
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

    if (args.length > 5 || args.length < 4) {
      // TODO: display error message and quit
      throw new IllegalArgumentException("sdfs");
    }

    if (!validBeginning(args)) {
      // TODO: display error message and quit
    }

    // at this point, you know that the first three inputs are "java -jar Program.jar"
    // and the size of the string array is 4 or 3.

    if (args.length == 5) {

    } else {
      if (args[3].equals("-text")) {

      } else if (args[3].equals("-interactive")) {

      } else {
        // TODO: display error message and quit
      }
    }
  }

  private static boolean validBeginning(String[] args) {
    return args[0].equals("java") && args[1].equals("-jar") && args[2].equals("Program.jar");
  }
}
