package view;

import cs3500.imageprocessing.model.IModel;
import java.io.IOException;

/**
 * Represents the ascii view of the ImageProcessing project.
 */
public class ProcessingView implements IProcessingView{

  private final IModel model;
  private Appendable ap;

  /**
   * Constructs a ProcessingView object.
   * @param model the ProcessingModel implementation and holds the current image/layer states.
   * @param ap Appendable object to be used in the rendering.
   */
  public ProcessingView(IModel model, Appendable ap) {
    this.model = model;
    // if the passed in appendable is not valid, you have to print everything to console instead.
    this.ap = (ap == null) ? System.out : ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      return;
    }

    // At this point, the message is not null
    this.ap.append(message);
  }
}
