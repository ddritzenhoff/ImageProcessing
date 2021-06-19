package cs3500.imageprocessing.model;


/**
 * Represents an ITransformation sharpen operation. Converts the passed-in image to a sharpened
 * version of itself.
 */
public class Sharpen implements ITransformation {

  private final ITransformation abstractDelegate;

  private final double[][] sharpenKernel =
      {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};

  /**
   * Constructor of a Sharpen operation Uses a AbstractFilterTransformation named abstractDelegate
   * to abstract the process of using kernels over an image.
   */
  public Sharpen() {
    this.abstractDelegate = new AbstractFilterTransformation(sharpenKernel);
  }

  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    return this.abstractDelegate.apply(oldImage);
  }

  @Override
  public String toString() {
    return "Sharpen";
  }
}
