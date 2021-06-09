package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;


/**
 * class to represent a series of ITransformations that will alter an IPixelImage.
 * These Itransformations will be perfored on a IPixelImage.
 */
public class ChainedTransformation implements ITransformation {

  ArrayList<TransformEnum> transforms;
  IPixelImage oldImage;

  /**
   * constructor for a ChainedTransformation
   * @param oldImage represents the old image that will be copied, then altered.
   * @param transforms a list of TransformEnum, consisting of the currently supported color, and
   *                   filter transformations.
   */
  ChainedTransformation(IPixelImage oldImage, List<TransformEnum> transforms) {

    this.oldImage = oldImage;
    this.transforms = new ArrayList<>(transforms);

  }

  //TODO: JAVADOC
  @Override
  public IPixelImage apply() {
    IPixelImage tempImage = new PixelImage(oldImage.getPixels());

    for (TransformEnum t : transforms) {
      switch (t) {
        case BLUR:
          IPixelImage Blur = new Blur(tempImage).apply();
          transforms.remove(t);
          return new ChainedTransformation(Blur,transforms).apply();

        case SHARPEN:
          IPixelImage Sharpen = new Sharpen(tempImage).apply();
          transforms.remove(t);
          return new ChainedTransformation(Sharpen,transforms).apply();

        case SEPIA:
          IPixelImage Sepia = new Sepia(tempImage).apply();
          transforms.remove(t);
          return new ChainedTransformation(Sepia,transforms).apply();

        case GREYSCALE:
          IPixelImage Greyscale = new Greyscale(tempImage).apply();
          transforms.remove(t);
          return new ChainedTransformation(Greyscale,transforms).apply();

        default:
          throw new IllegalArgumentException("shouldn't have got here");

      }
    }
    return tempImage;
  }
}
