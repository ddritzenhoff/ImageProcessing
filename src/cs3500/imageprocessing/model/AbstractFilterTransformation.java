package cs3500.imageprocessing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an ITransformation kernel operation. For everything that uses a kernel, this class
 * abstracts the process of using that kernel over the image.
 */
public final class AbstractFilterTransformation implements ITransformation {

  private final double[][] kernel;
  private final int kernelSize;

  /**
   * Represents an ITransformation filter operation. Converts the passed-in image to an operated-on
   * version of itself using the kernel.
   *
   * @param kernel an odd 2D array (1x1, 3x3, etc) that represents the values the surrounding pixels
   *               of the image must be multiplied by. After multiplication, the products are summed
   *               together.
   */
  protected AbstractFilterTransformation(double[][] kernel) {
    this.kernel = Objects.requireNonNull(kernel);
    this.kernelSize = this.kernel.length;
  }

  @Override
  public IPixelImage apply(IPixelImage oldImage) {

    Objects.requireNonNull(oldImage);

    List<List<IPixel>> pixelRows = new ArrayList<>();
    for (int row = 0; row < oldImage.getNumRows(); row++) {
      List<IPixel> pixelRow = new ArrayList<>();
      for (int pixelRowIndex = 0; pixelRowIndex < oldImage.getNumPixelsInRow(); pixelRowIndex++) {
        pixelRow.add(getNewPixel(row, pixelRowIndex, oldImage));
      }
      pixelRows.add(pixelRow);
    }

    return new PixelImage(pixelRows);
  }

  /**
   * Uses the kernel to take the sum of the products of the surrounding pixels.
   *
   * @param row           the pixel row of interest.
   * @param pixelRowIndex the pixel row index of interest.
   * @param oldImage      the image containing the pixels to be operated on.
   * @return a new IPixel, which contains the RGB values from the kernel operation.
   */
  private IPixel getNewPixel(int row, int pixelRowIndex, IPixelImage oldImage) {
    int startingValue = -1 * (this.kernelSize / 2);
    IPixel newPixel = new Pixel(0, 0, 0);

    int tempPixelRow = startingValue;
    for (int ii = 0; ii < this.kernelSize; ii++) {
      int tempPixelRowIndex = startingValue;
      for (int jj = 0; jj < this.kernelSize; jj++) {

        int p1 = row + tempPixelRow;
        int p2 = pixelRowIndex + tempPixelRowIndex;
        if (pixelWithinBounds(p1, p2, oldImage)) {
          IPixel tempPixel = oldImage.getPixel(p1, p2);
          tempPixel.scaleChannels(this.kernel[ii][jj]);
          newPixel.addValues(tempPixel);
        }

        tempPixelRowIndex++;
      }

      tempPixelRow++;
    }

    // clamp the values to make sure they stay within range.
    newPixel.clamp();

    return newPixel;
  }

  /**
   * Checks to see whether the pixel is within bounds of the passed-in image.
   *
   * @param tempPixelRow      the pixel row of interest.
   * @param tempPixelRowIndex the pixel row index of interest.
   * @param oldImage          the image containing the pixels within question.
   * @return true if the pixel is within bounds and false otherwise.
   */
  private boolean pixelWithinBounds(int tempPixelRow, int tempPixelRowIndex, IPixelImage oldImage) {
    return tempPixelRow >= 0 && tempPixelRow < oldImage.getNumRows() && tempPixelRowIndex >= 0
        && tempPixelRowIndex < oldImage.getNumPixelsInRow();
  }
}
