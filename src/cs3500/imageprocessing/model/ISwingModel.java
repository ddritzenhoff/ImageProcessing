package cs3500.imageprocessing.model;

import java.awt.image.BufferedImage;
import java.util.List;


/**
 * This interface represents a GUI representation of a model using Swing.
 * It contains all of the previous capabilities of a IModel, plus additional functionality
 * to streamline use with Swing.
 */
public interface ISwingModel extends IModel {
  /**
   * Returns a list of the layers.
   *
   * @return Returns all the current layers in their string representations.
   */
  List<String> list();

  /**
   * Gets the Buffered image representation of the top layer image.
   *
   * @return the buffered image representation of the top layer image.
   */
  BufferedImage topLayerImage();

  /**
   * Parses a text file to load a saved model.
   *
   * @param fileDirectory the file path to the file that should be parsed.
   */
  void loadModel(String fileDirectory);

  /**
   * Gets the visibility statuses of each layer.
   *
   * @return the visibility statuses of every layer within the model. True if the layer is visible
   *         and false otherwise.
   */
  List<Boolean> getVisibility();

  /**
   * The directory to be exported.
   *
   * @param fileDirectory the path to the file directory to be exported.
   */
  void exportDirectory(String fileDirectory);

}
