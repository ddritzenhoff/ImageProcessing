package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;


/**
 * class to represent a series of ITransformations that will alter an IPixelImage. These
 * Itransformations will be perfored on a IPixelImage.
 */
public class ChainedTransformation implements ITransformation {

  ArrayList<TransformEnum> transforms;
  IPixelImage oldImage;

  /**
   * constructor for a ChainedTransformation
   *
   * @param oldImage   represents the old image that will be copied, then altered.
   * @param transforms a list of TransformEnum, consisting of the currently supported color, and
   *                   filter transformations.
   */
  ChainedTransformation(IPixelImage oldImage, List<TransformEnum> transforms) {

    this.oldImage = oldImage;
    this.transforms = new ArrayList<>(transforms);
  }

  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    IPixelImage tempImage = oldImage;
    for (ITransformation t : transforms) {
      tempImage = t.apply(tempImage);
    }

    return tempImage;
  }
}
