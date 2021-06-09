package cs3500.imageProcessing.model;

/**
 * represents a ITransformation color transformation.
 * Changes all the pixels in the IPixelImage to greyscale.
 * Uses the sepiaMatrix, and applies the values to the RGB channels of the pixel.
 */
public class Sepia implements ITransformation {

  protected final ITransformation abstractDelegate;
  protected final double[][] sepiaMatrix =
      {{.393,  .769, .189},
          {.349,  .686, .168},
          {.272,  .534, .131}};

  /**
   * Constructor of a Sepiacolor transformation.
   * Uses a AbstractColorTransformation named abstractDelegate to abstract the procedure.
   * @param oldImage the IPixelImage that will have the Sepia
   *                color transformation applied to it.
   */
  public Sepia(IPixelImage oldImage) {
    this.abstractDelegate = new AbstractColorTransformation(oldImage, sepiaMatrix);

  }

  /**
   * Apply performs a ITransformation on an IPixelImage.
   * @return a new IPixelImage with a Sepia color transformation applied to it.
   * @param oldImage
   */
  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    return abstractDelegate.apply(oldImage);
  }

}



