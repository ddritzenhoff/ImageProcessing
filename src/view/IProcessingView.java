package view;

import java.io.IOException;

/**
 * The view that the user will get exposed to when incorrect inputs are given or general updates
 * are made.
 */
public interface IProcessingView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

}
