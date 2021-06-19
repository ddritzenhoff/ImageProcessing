import controller.IProcessingController;
import controller.ProcessingController;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ProcessingModel;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Class used to test all of the aspects of the controller.
 */
public class IProcessingControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    IModel model = null;
    Reader input = new StringReader("create-layer first");
    StringBuffer output = new StringBuffer();
    IProcessingController processingController = new ProcessingController(model, input, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    IModel model = new ProcessingModel();
    Reader input = new StringReader("create-layer first");
    StringBuffer output = null;
    IProcessingController processingController = new ProcessingController(model, input, output);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    IModel model = new ProcessingModel();
    Reader input = null;
    StringBuffer output = new StringBuffer();
    IProcessingController processingController = new ProcessingController(model, input, output);
  }

}


