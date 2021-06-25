import controller.IProcessingController;
import cs3500.imageprocessing.model.IModel;
import java.io.IOException;
import java.util.Objects;
import view.IView;
import view.IViewListener;

/**
 * Represents the mock controller class to test the GUI controller.
 */
public class MockController implements IProcessingController, IViewListener {

  private final IView mockView;
  private final IModel model;
  private final Appendable out;

  /**
   * Constructs a MockView object.
   * @param mockView the mock view class.
   * @param model the model to be used.
   * @param out the object to send the function names that were called.
   */
  public MockController(IView mockView, IModel model, Appendable out) {
    this.mockView = Objects.requireNonNull(mockView);
    this.model = Objects.requireNonNull(model);
    this.mockView.registerViewEventListener(this);
    this.out = Objects.requireNonNull(out);
  }

  private void write(String message) {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("appendable failed. tests should fail");
    }
  }

  @Override
  public void startProcessing() {
    write("start processing");
  }

  @Override
  public void handleBlurEvent() {
    write("handleBlurEvent");
  }

  @Override
  public void handleSepiaEvent() {
    write("handleSepiaEvent");
  }

  @Override
  public void handleGreyscaleEvent() {
    write("handleGreyscaleEvent");
  }

  @Override
  public void handleSharpenEvent() {
    write("handleSharpenEvent");
  }

  @Override
  public void handleWorkingLayerEvent() {
    write("handleWorkingLayerEvent");
  }

  @Override
  public void handleAddLayerEvent() {
    write("handleAddLayerEvent");
  }

  @Override
  public void handleAddImageToLayerEvent() {
    write("handleAddImageToLayerEvent");
  }

  @Override
  public void handleVisibilityEvent() {
    write("handleVisibilityEvent");
  }

  @Override
  public void handleDeleteLayerEvent() {
    write("handleDeleteLayerEvent");
  }

  @Override
  public void handleSaveAllEvent() {
    write("handleSaveAllEvent");
  }

  @Override
  public void handleLoadAllEvent() {
    write("handleLoadAllEvent");
  }

  @Override
  public void handleExportEvent() {
    write("handleExportEvent");
  }

  @Override
  public void handleLoadScriptEvent() {
    write("handleLoadScriptEvent");
  }

  @Override
  public void loadVisibility() {
    write("loadVisibility");
  }

  @Override
  public void handleAddCheckerboardEvent() {
    write("handleAddCheckerboardEvent");
  }

  @Override
  public void showTopMostVisibleImageLayerEvent() {
    write("showTopMostVisibleImageLayerEvent");
  }
}
