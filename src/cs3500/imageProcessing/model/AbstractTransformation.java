package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: I think that I want to make this class final so that it can only be used as a delegate.
public final class AbstractTransformation implements ITransformation{

  protected final IPixelImage oldImage;
  protected final double[][] kernel;
  protected final int kernelSize;

  protected AbstractTransformation(IPixelImage oldImage, double[][] kernel) {
    this.oldImage = Objects.requireNonNull(oldImage);
    this.kernel = Objects.requireNonNull(kernel);
    this.kernelSize = this.kernel.length;
  }

  @Override
  public IPixelImage apply(String id) {
    List<List<IPixel>> pixelRows = new ArrayList<>();
    for (int row = 0; row < oldImage.getNumRows(); row++) {
      List<IPixel> pixelRow = new ArrayList<>();
      for (int pixelRowIndex = 0; pixelRowIndex < oldImage.getNumPixelsInRow(); pixelRowIndex++) {
        pixelRow.add(getNewPixel(row, pixelRowIndex));
      }

      pixelRows.add(pixelRow);
    }

    return new PixelImage(pixelRows, "abstractImage");
  }

  protected IPixel getNewPixel(int row, int pixelRowIndex) {
    int startingValue = -1 * (this.kernelSize / 2);
    IPixel newPixel = new Pixel(0, 0, 0);

    int tempPixelRow = startingValue;
    for (int ii = 0; ii < this.kernelSize; ii++) {
      int tempPixelRowIndex = startingValue;
      for (int jj = 0; jj < this.kernelSize; jj++) {

        int p1 = row + tempPixelRow;
        int p2 = pixelRowIndex + tempPixelRowIndex;
        if (pixelWithinBounds(p1, p2)) {
          IPixel tempPixel = oldImage.getPixel(p1, p2);
          tempPixel.scaleChannels(this.kernel[ii][jj]);
          newPixel.addValues(tempPixel);
        }

        tempPixelRowIndex++;
      }

      tempPixelRow++;
    }

    return newPixel;
  }

  protected boolean pixelWithinBounds(int tempPixelRow, int tempPixelRowIndex) {
    return tempPixelRow >= 0 && tempPixelRow < this.oldImage.getNumRows() && tempPixelRowIndex >= 0
        && tempPixelRowIndex < this.oldImage.getNumPixelsInRow();
  }
}