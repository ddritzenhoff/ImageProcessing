package cs3500.imageProcessing.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents an ITransformation color transformation. This class abstracts the process of modifying
 * the color of a pixel based on its own color.
 */
public final class AbstractColorTransformation implements ITransformation {

  protected IPixelImage oldImage;
  protected final double[][] colorMatrix;

  /**
   * Represents an ITransformation color transformation. Converts the passed-in image to an
   * operated-on version of itself using the color matrix.
   *
   * @param colorMatrix a matrix containing the values to multiply each channel by. This can create
   *                    different 'looks' such as grayscale or sepia.
   */
  protected AbstractColorTransformation(double[][] colorMatrix) {
    this.colorMatrix = Objects.requireNonNull(colorMatrix);
  }

  @Override
  public IPixelImage apply(IPixelImage oldImage) {
    this.oldImage = Objects.requireNonNull(oldImage);
    List<List<IPixel>> pixelRows = this.oldImage.getPixels();

    for (int row = 0; row < pixelRows.size(); row++) {
      List<IPixel> pixelRow = pixelRows.get(row);
      for (int pixelRowIndex = 0; pixelRowIndex < pixelRow.size(); pixelRowIndex++) {
        pixelRows.get(row).get(pixelRowIndex).applyMatrix(colorMatrix);
      }
    }
    return new PixelImage(pixelRows, "color modified "+ oldImage.getFileName());

  }
}
