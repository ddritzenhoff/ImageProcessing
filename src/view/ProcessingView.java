package view;

import cs3500.imageprocessing.model.IModel2;
import java.io.IOException;

public class ProcessingView implements IProcessingView{

  private final IModel2 model;
  private Appendable ap;

  public ProcessingView(IModel2 model, Appendable ap) {
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
