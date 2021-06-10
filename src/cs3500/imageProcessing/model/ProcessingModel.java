package cs3500.imageProcessing.model;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents the model to process different images. This particular implementation lets
 * you add images to 'storage' and transform them using different operations.
 */
public class ProcessingModel implements IModel {

  protected final Map<String, IPixelImage> images;

  /**
   * Constructs a IModel object.
   */
  public ProcessingModel() {
    this.images = new HashMap<>();
  }

  @Override
  public void addImage(String fileName, IPixelImage image) {
    Objects.requireNonNull(fileName);
    Objects.requireNonNull(image);

    // TODO: QUESTION: stuff here.
    if (images.containsKey(fileName)) {
      throw new IllegalStateException("");
    }

    images.putIfAbsent(fileName, image);
  }

  @Override
  public void removeImage(String fileName) {
    images.remove(fileName);
  }

  @Override
  public void replaceImage(String fileName, IPixelImage image) {
    images.replace(fileName,image);
  }

  /**
   * chainTransformations performs a series of ITransformations on an IPixelImage. Currently there
   * are two color transformations: Sepia, and Greyscale. Currently there are two filter
   * transformations: Blur,and Sharpen.
   *
   * @param transforms this is the list of the transforms that will be performed on the IPixelImage
   *                   correlated with the fileName.
   * @param fileName   this is the image to be operated upon.
   * @return a new IPixelImage with the appropriate transformations applied to it.
   */
  @Override
  public IPixelImage chainTransformations(List<ITransformation> transforms, String fileName) {
    ImageUtil.requireNonNull(transforms, "list transforms");
    ImageUtil.requireNonNull(fileName, "chain transformation filename");
    IPixelImage newImage = new ChainedTransformation(transforms).apply(images.get(fileName));
    return newImage;
  }

  @Override
  public IPixelImage applyTransformation(ITransformation transform, String fileName) {
    ImageUtil.requireNonNull(transform, "apply transformation transform");
    ImageUtil.requireNonNull(fileName, "apply transformation filename");

    return transform.apply(images.get(fileName));
  }

  public IPixelImage generateCheckerboard(int sizeTile, int numSquares) {
    return new Checkerboard(sizeTile, numSquares);
  }

  public void importPPM(String fileName) {
    ImageUtil.requireNonNull(fileName, "import ppm filename");
    images.putIfAbsent(fileName, ImageUtil.PPMtoPixelImage(fileName));
  }

  public void exportPPM(String fileName) {
    ImageUtil.requireNonNull(fileName, "export ppm filename");
    images.get(fileName).render("ppm");
  }


}
