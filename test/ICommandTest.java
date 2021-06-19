import controller.AddImageToLayer;
import controller.AddLayer;
import controller.BlurCMD;
import controller.CreateCheckerboard;
import controller.ExportAll;
import controller.ExportLayer;
import controller.GreyscaleCMD;
import controller.ICommand;
import controller.MakeInvisible;
import controller.MakeVisible;
import controller.RemoveLayer;
import controller.SepiaCMD;
import controller.SetWorkingLayer;
import controller.SharpenCMD;
import cs3500.imageprocessing.model.IModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class test test all the ICommand function-objects using a mock model.
 */
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
    String expected = "addImageToLayer: man.png";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testAddLayer() {
    ICommand cmd = new AddLayer("first");
    cmd.go(model);
    String expected = "addLayer: first";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testBlurCMD() {
    ICommand cmd = new BlurCMD();
    cmd.go(model);
    String expected = "applyTransformation: transformation = Blur";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testCreateCheckerboard() {
    ICommand cmd = new CreateCheckerboard("300 300");
    cmd.go(model);
    String expected = "generateCheckerboard: sizeTile = 300, numSquares = 300";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testExportAll() {
    ICommand cmd = new ExportAll("res/res2");
    cmd.go(model);
    String expected = "exportAll: directoryName = res/res2";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testExportLayer() {
    ICommand cmd = new ExportLayer("newfilename.png");
    cmd.go(model);
    String expected = "exportLayer: newFileName = newfilename.png";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testGreyscaleCMD() {
    ICommand cmd = new GreyscaleCMD();
    cmd.go(model);
    String expected = "applyTransformation: transformation = Greyscale";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testMakeInvisible() {
    ICommand cmd = new MakeInvisible("first");
    cmd.go(model);
    String expected = "setVisiblity: layerName = first, isVisible = false";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testMakeVisible() {
    ICommand cmd = new MakeVisible("first");
    cmd.go(model);
    String expected = "setVisiblity: layerName = first, isVisible = true";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testRemoveLayer() {
    ICommand cmd = new RemoveLayer();
    cmd.go(model);
    String expected = "deleteLayer";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testSepiaCMD() {
    ICommand cmd = new SepiaCMD();
    cmd.go(model);
    String expected = "applyTransformation: transformation = Sepia";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testSetWorkingLayer() {
    ICommand cmd = new SetWorkingLayer("first");
    cmd.go(model);
    String expected = "setWorkingLayer: layerName = first";
    assertEquals(expected, log.toString());
  }

  @Test
  public void testSharpenCMD() {
    ICommand cmd = new SharpenCMD();
    cmd.go(model);
    String expected = "applyTransformation: transformation = Sharpen";
    assertEquals(expected, log.toString());
  }
}
