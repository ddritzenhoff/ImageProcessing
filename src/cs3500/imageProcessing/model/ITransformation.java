package cs3500.imageProcessing.model;

/**
 * a ITransformation represents either a color, filter, or chained transformation. It's
 * corresponding apply method performs the transformation on an IPixelImage. Currently there are two
 * color transformations: Sepia, and Greyscale. Currently there are two filter transformations:
 * Blur, and Sharpen.
 */
public interface ITransformation {

  /**
   * Apply performs a ITransformation on an IPixelImage. Currently there are two color
   * transformations: Sepia, and Greyscale. Currently there are two filter transformations: Blur,
   * and Sharpen.
   *
   * @param oldImage this is the image to be operated upon.
   * @return a new IPixelImage with the appropriate transformation applied to it.
   */
  IPixelImage apply(IPixelImage oldImage);
}
