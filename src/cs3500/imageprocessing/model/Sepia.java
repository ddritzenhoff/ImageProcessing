package cs3500.imageprocessing.model;

/**
 * represents a ITransformation color transformation. Changes all the pixels in the IPixelImage to
 * greyscale. Uses the sepiaMatrix, and applies the values to the RGB channels of the pixel.
 */
public class Sepia implements ITransformation {

  private final ITransformation abstractDelegate;
  private final double[][] sepiaMatrix =
      {{.393, .769, .189},
          {.349, .686, .168},
          {.272, .534, .131}};

  /**
   * Constructor of a Sepiacolor transformation. Uses a AbstractColorTransformation named
   * abstractDelegate to abstract the procedure.
   */
  public Sepia() {
    this.abstractDelegate = new AbstractColorTransformation(sepiaMatrix);
  }

  /**
   * Apply performs a ITransformation on an IPixelImage.
   *
   * @param oldImage Old IPixelImage that will be transformed.
   * @return a new IPixelImage with a Sepia color transformation applied to it.
   */
  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    return abstractDelegate.apply(oldImage);
  }

}



