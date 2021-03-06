package cs3500.imageprocessing.model;

/**
 * Represents a Layer. This can contain an IPixelImage, which in turn can be altered from
 * a few different commands.
 */
public interface ILayer {

  /**
   * Gets the visibility of the layer.
   * @return true if the layer is set to invisible and false otherwise.
   */
  boolean getVisibility();

  /**
   * Gets a copy of the IPixelImage within the layer.
   * @return a copy of the IPixelImage.
   */
  IPixelImage getImage();

  /**
   * Gets the order of the layer.
   * @return the layer's order (starting from 0)
   */
  int getOrder();

  /**
   * Sets an IPixelImage within the layer.
   * @param image sets an IPixelImage within the layer.
   */
  void setImage(IPixelImage image);

  /**
   * Gets the name of the layer.
   * @return the string representation of the layer name.
   */
  String getLayerName();

  /**
   * gets the status of a layer.
   * the layer status indicates if a layer has been populated with an IPixelImage.
   * @return a boolean representing the layer status.
   */
  Boolean getStatus();

  /**
   * Gets the order of the layer with respect to all the other layers.
   * @return the order of the layer.
   */
  int loadedOrder();

}
