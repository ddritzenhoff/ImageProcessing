package cs3500.imageProcessing.model;

/**
 * represents a ITransformation color transformation.
 * Changes all the pixels in the IPixelImage to greyscale.
 * Uses the greyScaleMatrix, and applies the values to the RGB channels of the pixel.
 */
public class Greyscale implements ITransformation {

  protected final ITransformation abstractDelegate;
  protected final double[][] greyScaleMatrix =
      {{.2126,  .7152, .0722},
          {.2126,  .7152, .0722},
          {.2126,  .7152, .0722}};

  /**
   * Constructor of a Greyscale color transformation.
   * Uses a AbstractColorTransformation named abstractDelegate to abstract the procedure.
   * @param oldImage the IPixelImage that will have the Greyscale
   *                color transformation applied to it.
   */
  public Greyscale(IPixelImage oldImage) {
    this.abstractDelegate = new AbstractColorTransformation(oldImage, greyScaleMatrix);
  }

  /**
   * Apply performs a ITransformation on an IPixelImage.
   * @return a new IPixelImage with a Greyscale color transformation applied to it.
   */
  @Override
  public IPixelImage apply() {
    return this.abstractDelegate.apply();
  }

}
