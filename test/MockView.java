import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import view.IView;
import view.IViewListener;

/**
 * Represents the mock GUI view class used to test the inputs to the functions.
 */
public class MockView implements IView {

  private IViewListener listener;
  private final Appendable out;

  /**
   * Constructs a MockView object.
   *
   * @param out the string to be written to and can then be parsed within the tests.
   */
  public MockView(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void registerViewEventListener(IViewListener listener) {
    this.listener = Objects.requireNonNull(listener);
  }

  @Override
  public String getText() {
    write("getText");
    return null;
  }

  private void write(String message) {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Test should fail, appendable fialed");
    }
  }

  @Override
  public void askForFocus() {
    write("askForFocus: ");
  }

  @Override
  public void setMenu(List<String> s) {
    StringBuilder str = new StringBuilder();
    for (String str_val : s) {
      str.append(str_val);
    }
    write("setMenu: " + str);
  }

  @Override
  public String getClickedLayer() {
    write("getClickedLayer");
    return null;
  }

  @Override
  public String getFileDest() {
    write("getFileDest");
    return null;
  }

  @Override
  public void setImage(BufferedImage bufferedImage) {
    write("setImage");
  }

  @Override
  public List<Boolean> getVisibility() {
    write("getVisibility");
    return null;
  }

  @Override
  public String getSaveAllFilePath() {
    write("getSaveAllFilePath");
    return null;
  }

  @Override
  public String getLoadedModelFileDest() {
    write("getLoadedModelFileDest");
    return null;
  }

  @Override
  public void setVisibility(List<Boolean> b) {
    write("setVisibility");
  }

  @Override
  public void addLayer(String layerName) {
    write("addLayer: " + layerName);
  }

  @Override
  public void removeLayer(String layerName) {
    write("removeLayer");
  }

  @Override
  public String getScript() {
    return null;
  }

  @Override
  public void writeError(String s) {
    write("writeError: " + s);
  }

  @Override
  public int getBoxWidth() {
    return 0;
  }

  @Override
  public int getNumBoxes() {
    return 0;
  }

  public void fireHandleBlurEvent() {
    this.listener.handleBlurEvent();
  }

  public void fireHandleSepiaEvent() {
    this.listener.handleSepiaEvent();
  }

  public void fireHandleGreyscaleEvent() {
    this.listener.handleGreyscaleEvent();
  }

  public void fireHandleSharpenEvent() {
    this.listener.handleSharpenEvent();
  }

  public void fireHandleWorkingLayerEvent() {
    this.listener.handleWorkingLayerEvent();
  }

  public void fireHandleAddLayerEvent() {

    this.listener.handleAddLayerEvent();
  }

  public void fireHandleAddImageToLayerEvent() {
    this.listener.handleAddImageToLayerEvent();
  }

  public void fireHandleVisibilityEvent() {
    this.listener.handleVisibilityEvent();
  }

  public void fireHandleDeleteLayerEvent() {
    this.listener.handleDeleteLayerEvent();
  }

  public void fireHandleSaveAllEvent() {
    this.listener.handleSaveAllEvent();
  }

  public void fireHandleLoadAllEvent() {
    this.listener.handleLoadAllEvent();
  }

  public void fireTestLoadVisibility() {
    this.listener.loadVisibility();
  }

  public void fireShowTopMostVisibleImageLayerEvent() {
    this.listener.showTopMostVisibleImageLayerEvent();
  }

  public void fireHandleExportEvent() {
    this.listener.handleExportEvent();
  }

  public void fireHandleLoadScriptEvent() {
    this.listener.handleLoadScriptEvent();
  }
}
