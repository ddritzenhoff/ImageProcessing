
import controller.IProcessingController;
import controller.ProcessingController;
import controller.SwingController;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ISwingModel;
import cs3500.imageprocessing.model.ProcessingModel;

import cs3500.imageprocessing.model.SwingModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;
import view.IView;

import view.SwingView;

/**
 * Class to run all of the methods the image processing application a few different ways.
 */
public class Main {

  /**
   * Run the image processing application.
   *
   * @param args arguments which type of the image processing application to run.
   * @throws FileNotFoundException if the input file path wasn't found.
   */
  public static void main(String[] args) throws FileNotFoundException {

    if (args.length < 1 || args.length > 2) {
      throw new IllegalArgumentException("incorrect number of arguments");
    }

    if (args[0].equals("-script")) {
      String pathOfScriptFile = Objects.requireNonNull(args[1]);
      IModel model = new ProcessingModel();
      Readable rd = new InputStreamReader(new FileInputStream(pathOfScriptFile));
      IProcessingController controller = new ProcessingController(model, rd,
          System.out);
      controller.startProcessing();
      return;
    }

    // } else {
    if (args[0].equals("-text")) {
      InputStreamReader reader = new InputStreamReader(System.in);
      OutputStreamWriter writer = new OutputStreamWriter(System.out);
      IModel model = new SwingModel();
      IProcessingController controller = new ProcessingController(model, reader, writer);
      controller.startProcessing();
      return;
    }

    //  } else
    if (args[0].equals("-interactive")) {
      ISwingModel model = new SwingModel();
      IView swingView = new SwingView();
      IProcessingController swingController = new SwingController(model, swingView);
      swingController.startProcessing();
      return;
    } else {
      throw new IllegalArgumentException("invalid argument. Must be -text or -interactive");
    }

  }

}
