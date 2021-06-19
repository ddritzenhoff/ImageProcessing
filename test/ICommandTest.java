import controller.AddImageToLayer;
import controller.AddLayer;
import controller.BlurCMD;
import controller.ICommand;
import cs3500.imageprocessing.model.IModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ICommandTest {

  StringBuilder log;
  IModel model;

  @Before
  public void init() {
    log = new StringBuilder();
    model = new MockModel(log);
  }

  @Test
  public void testAddImageToLayer() {
    ICommand cmd = new AddImageToLayer("man.png");
    cmd.go(model);
    String expected = "man.png";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testAddLayer() {
    ICommand cmd = new AddLayer("first");
    cmd.go(model);
    String expected = "first";
    assertEquals(expected, log.toString());
  }

  // TODO: This may not work. I can't test because of errors in other classes
  @Test
  public void testBlurCMD() {
    ICommand cmd = new BlurCMD();
    cmd.g
  }
}
