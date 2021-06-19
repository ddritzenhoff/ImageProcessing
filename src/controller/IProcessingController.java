package controller;

/**
 * represents a Controller in which the string commands from the user are interpreted to make
 * changes to the image processing 'library'.
 */
public interface IProcessingController {

  /**
   * Keep processing layers and IPixelImages until the user quits.
   */
  void startProcessing();
}
