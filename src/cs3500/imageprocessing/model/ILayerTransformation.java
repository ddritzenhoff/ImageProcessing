package cs3500.imageprocessing.model;

/**
 * This is an interface for a function-object to operate on ILayers.
 */
public interface ILayerTransformation {

  /**
   * Operates on the layers.
   * @param layer1 the first layer to be operated upon.
   * @param layer2 the second layer to be operated upon.
   * @return the IPixelImage representation after the transformation.
   */
  public IPixelImage apply(ILayer layer1, ILayer layer2);

}
