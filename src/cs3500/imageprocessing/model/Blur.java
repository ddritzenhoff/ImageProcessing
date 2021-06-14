package cs3500.imageprocessing.model;


/**
 * Represents an ITransformation blur operation. Converts the passed-in image to a blurred version
 * of itself.
 */
public class Blur implements ITransformation {

  private final ITransformation abstractDelegate;

  private final double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
      {1.0 / 8, 1.0 / 4, 1.0 / 8},
      {1.0 / 16, 1.0 / 8, 1.0 / 16}};

  /**
   * Constructor of a Blur operation Uses a AbstractFilterTransformation named abstractDelegate to
   * abstract the process of using kernels over an image.
   */
  public Blur() {
    this.abstractDelegate = new AbstractFilterTransformation(blurKernel);
  }

  /**
   * Apply performs a ITransformation on an IPixelImage.
   *
   * @return a new IPixelImage with a blur transformation applied to it.
   */
  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    return this.abstractDelegate.apply(oldImage);
  }

}
