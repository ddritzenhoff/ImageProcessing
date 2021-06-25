import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static org.junit.Assert.assertEquals;

import cs3500.imageprocessing.model.ProcessingModel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Represents the class used to test the controller and view for the GUI.
 */
public class MyWindowTest {

  MockView mockView;
  MockController mockController;

  @Test
  public void testHandleBlurEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleBlurEvent();
    assertEquals("handleBlurEvent", c_out.toString());
  }

  @Test
  public void testHandleSepiaEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleSepiaEvent();
    assertEquals("handleSepiaEvent", c_out.toString());
  }

  @Test
  public void testHandleGreyscaleEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleGreyscaleEvent();

    assertEquals("handleGreyscaleEvent", c_out.toString());
  }

  @Test
  public void testHandleSharpenEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleSharpenEvent();

    assertEquals("handleSharpenEvent", c_out.toString());
  }

  @Test
  public void testHandleWorkingLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleWorkingLayerEvent();
    mockView.getClickedLayer();
    assertEquals("handleWorkingLayerEvent", c_out.toString());
    assertEquals("getClickedLayer", v_out.toString());
  }

  @Test
  public void testHandleAddLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleAddLayerEvent();

    assertEquals("handleAddLayerEvent", c_out.toString());
  }

  @Test
  public void testHandleAddImageToLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleAddImageToLayerEvent();
    mockView.getFileDest();

    assertEquals("handleAddImageToLayerEvent", c_out.toString());
    assertEquals("getFileDest", v_out.toString()); // TODO: run again
  }

  @Test
  public void testHandleVisibilityEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleVisibilityEvent();
    mockView.getVisibility();

    assertEquals("handleVisibilityEvent", c_out.toString());
    assertEquals("getVisibility", v_out.toString());
  }

  @Test
  public void testHandleDeleteLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleDeleteLayerEvent();

    assertEquals("handleDeleteLayerEvent", c_out.toString());
  }

  @Test
  public void testHandleSaveAllEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleSaveAllEvent();

    assertEquals("handleSaveAllEvent", c_out.toString());
  }

  @Test
  public void testHandleLoadAllEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleLoadAllEvent();

    assertEquals("handleLoadAllEvent", c_out.toString());
  }

  @Test
  public void testLoadVisibility() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireTestLoadVisibility();
    mockView.setVisibility(new ArrayList<>());

    assertEquals("loadVisibility", c_out.toString());
    assertEquals("setVisibility", v_out.toString());
  }

  @Test
  public void testShowTopMostVisibleImageLayerEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireShowTopMostVisibleImageLayerEvent();
    mockView.setImage(new BufferedImage(4, 4, TYPE_INT_RGB));

    assertEquals("showTopMostVisibleImageLayerEvent", c_out.toString());
    assertEquals("setImage", v_out.toString());
  }

  @Test
  public void testHandleExportEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleExportEvent();

    assertEquals("handleExportEvent", c_out.toString());
  }

  @Test
  public void testHandleLoadScriptEvent() {
    Appendable c_out = new StringBuilder();
    Appendable v_out = new StringBuilder();
    mockView = new MockView(v_out);
    mockController = new MockController(mockView, new ProcessingModel(), c_out);
    mockView.fireHandleLoadScriptEvent();

    assertEquals("handleLoadScriptEvent", c_out.toString());
  }
}
