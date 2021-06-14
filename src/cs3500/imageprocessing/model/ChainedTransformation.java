package cs3500.imageprocessing.model;

import java.util.ArrayList;
import java.util.List;


/**
 * class to represent a series of ITransformations that will alter an IPixelImage. These
 * Itransformations will be perfored on a IPixelImage.
 */
public class ChainedTransformation implements ITransformation {

  private ArrayList<ITransformation> transforms;

  /**
   * constructor for a ChainedTransformation.
   *
   * @param transforms a list of TransformEnum, consisting of the currently supported color, and
   *                   filter transformations.
   */
  public ChainedTransformation(List<ITransformation> transforms) {
    ImageUtil.requireNonNull(transforms, "checking transform constructor");
    this.transforms = new ArrayList<>(transforms);
  }

  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    IPixelImage tempImage = oldImage;
    for (ITransformation t : transforms) {
      ImageUtil.requireNonNull(t, "checking multi transform apply");
      tempImage = t.apply(tempImage);
    }
    return tempImage;
  }
}
