package cs3500.imageProcessing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ImageUtil.requireNonNull(fileName, "addImage filename.");
    ImageUtil.requireNonNull(image, "addImage IPixelImage");

    if (images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry already has a file of this name.");
    }

    images.putIfAbsent(fileName, image);
  }

  @Override
  public void removeImage(String fileName) {
    ImageUtil.requireNonNull(fileName, "remove image");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("invalid filename");
    }
    images.size();
    images.remove(fileName);
  }

  @Override
  public void replaceImage(String fileName, IPixelImage image) {
    ImageUtil.requireNonNull(fileName, "replace image fileName");
    ImageUtil.requireNonNull(image, "replace image IPixelImage");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("invalid filename");
    }
    images.replace(fileName, image);
  }

  @Override
  public IPixelImage chainTransformations(List<ITransformation> transforms, String fileName) {
    ImageUtil.requireNonNull(transforms, "list transforms");
    ImageUtil.requireNonNull(fileName, "chain transformation filename");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }
    IPixelImage newImage = new ChainedTransformation(transforms).apply(images.get(fileName));
    return newImage;
  }

  //TODO: I think this should be void
  @Override
  public void applyTransformation(ITransformation transform, String fileName, String newFileName) {
    ImageUtil.requireNonNull(transform, "apply transformation transform");
    checkRegistry(fileName, newFileName);

    this.images.putIfAbsent(newFileName, transform.apply(images.get(fileName)));
  }

  //TODO: I think this should MAYBE be void, and add the generated checkerboad to the catalog.
  public IPixelImage generateCheckerboard(int sizeTile, int numSquares) {
    if (sizeTile < 1 || numSquares < 1 ) {
      throw new IllegalArgumentException("invalid parameters to make a checkerboard");
    }
    return new Checkerboard(sizeTile, numSquares);
  }

  public void importPPM(String directoryName, String fileName) {
    ImageUtil.requireNonNull(fileName, "import ppm filename");
    ImageUtil.requireNonNull(fileName, "import ppm directoryName");
    addImage(fileName, ImageUtil.PPMtoPixelImage(directoryName));
  }

  public void exportPPM(String fileName) {
    ImageUtil.requireNonNull(fileName, "export ppm filename");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }
    images.get(fileName).render("ppm");
  }

  public String printRegistry() {
    return images.keySet().toString();
  }

  public IPixelImage getImage(String fileName) {
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }
    return new PixelImage(images.get(fileName).getPixels());
  }

}
