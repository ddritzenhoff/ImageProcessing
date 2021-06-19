import controller.IProcessingController;
import controller.ProcessingController;
import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ProcessingModel;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import view.IProcessingView;

public class IProcessingControllerTest {

  protected StringBuilder sb;
  protected Scanner sc;
  protected Readable rd;
  protected IModel model;
  protected IProcessingView view;

  @Before
  public void init() {
    this.sc = new Scanner(rd);
  }

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

  @Test
  public void testProcessingController() {

  }

}


