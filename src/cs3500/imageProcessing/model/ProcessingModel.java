package cs3500.imageProcessing.model;

import java.util.HashMap;
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
  public void removeImage(String fileName, IPixelImage image) {

  }

  @Override
  public void replaceImage(String fileName, IPixelImage image) {

  }

  @Override
  public IPixelImage applyTransformation(ITransformation transform, String fileName) {
    Objects.requireNonNull(transform);
    Objects.requireNonNull(fileName);

    return transform.apply(images.get(fileName));
  }

  public IPixelImage generateCheckerboard(int sizeTile, int numSquares) {
    return new Checkerboard(sizeTile, numSquares);
  }

  public void importPPM(String fileName) {
    images.putIfAbsent(fileName, ImageUtil.PPMtoPixelImage(fileName));
  }

  public void exportPPM(String fileName) {
    images.get(fileName).render("ppm");
  }


}
