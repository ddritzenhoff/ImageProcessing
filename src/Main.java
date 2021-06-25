
import controller.IProcessingController;
import controller.ProcessingController;
import controller.SwingController;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ProcessingModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;
import view.IProcessingView;
import view.IView;

import view.ProcessingView;
import view.SwingView;

/**
 * Class to run all of the methods the image processing application a few different ways.
 */
public class Main {

  /**
   * Run the image processing application.
   * @param args arguments which type of the image processing application to run.
   * @throws FileNotFoundException if the input file path wasn't found.
   */
  public static void main(String [] args) throws FileNotFoundException {

    if (args.length > 5 || args.length < 4) {
      throw new IllegalArgumentException("incorrect number of arguments");
    }

    if (!validBeginning(args)) {
      throw new IllegalArgumentException("incorrect commands. First three words should be java -jar Program.jar");
    }

    // at this point, you know that the first three inputs are "java -jar Program.jar"
    // and the size of the string array is 4 or 3.

    if (args.length == 5) {

      if (args[3].equals("-script")) {
        String pathOfScriptFile = Objects.requireNonNull(args[4]);
        IModel model = new ProcessingModel();
        Readable rd = new InputStreamReader(new FileInputStream(pathOfScriptFile));
        IProcessingController controller = new ProcessingController(model, rd,
            System.out);
        controller.startProcessing();
      } else {
        throw new IllegalArgumentException("invalid argument. Must be -script");
      }

    } else {
      if (args[3].equals("-text")) {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        IModel model = new ProcessingModel();
        IProcessingController controller = new ProcessingController(model, reader, writer);
        controller.startProcessing();

      } else if (args[3].equals("-interactive")) {
        IModel model = new ProcessingModel();
        IView swingView = new SwingView();
        IProcessingController swingController = new SwingController(model, swingView);
        swingController.startProcessing();
      } else {
        throw new IllegalArgumentException("invalid argument. Must be -text or -interactive");
      }
    }
  }

  private static boolean validBeginning(String[] args) {
    return args[0].equals("java") && args[1].equals("-jar") && args[2].equals("Program.jar");
  }
}
