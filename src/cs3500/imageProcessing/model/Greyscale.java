package cs3500.imageProcessing.model;

/**
 * represents a ITransformation color transformation. Changes all the pixels in the IPixelImage to
 * greyscale. Uses the greyScaleMatrix, and applies the values to the RGB channels of the pixel.
 */
public class Greyscale implements ITransformation {

  protected final ITransformation abstractDelegate;
  protected final double[][] greyScaleMatrix =
      {{.2126, .7152, .0722},
          {.2126, .7152, .0722},
          {.2126, .7152, .0722}};

  /**
   * Constructor of a Greyscale color transformation. Uses a AbstractColorTransformation named
   * abstractDelegate to abstract the procedure.
   */
  public Greyscale() {
    this.abstractDelegate = new AbstractColorTransformation(greyScaleMatrix);
  }

  /**
   * Apply performs a ITransformation on an IPixelImage.
   *
   * @param oldImage the image to be converted into a new grayscale image.
   * @return a new IPixelImage with a Greyscale color transformation applied to it.
   */
  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    return this.abstractDelegate.apply(oldImage);
  }

}
