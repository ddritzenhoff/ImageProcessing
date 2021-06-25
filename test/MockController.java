import controller.IProcessingController;
import cs3500.imageprocessing.model.IModel;
import java.io.IOException;
import java.util.Objects;
import view.IView;
import view.IViewListener;

public class MockController implements IProcessingController, IViewListener {

  private final IView mockView;
  private final IModel model;
  private final Appendable out;

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

  // TODO: I think that you won't be doing anything within the startProcessing function but
  //  will definitely do something within the handling methods

  @Override
  public void startProcessing() {

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
  public void showTopMostVisibleImageLayerEvent() {
    write("showTopMostVisibleImageLayerEvent");
  }
}
