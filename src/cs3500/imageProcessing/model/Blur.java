package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;

public class Blur implements ITransformation {

  protected final IPixelImage oldImage;

  protected final double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
      {1.0 / 8, 1.0 / 4, 1.0 / 8},
      {1.0 / 16, 1.0 / 8, 1.0 / 16}};


  public Blur(IPixelImage oldImage) {
    this.oldImage = oldImage;
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

    return new PixelImage(pixelRows, "BlurredKoala1");
  }

  protected IPixel getNewPixel(int row, int pixelRowIndex) {

    IPixel newPixel = new Pixel(0, 0, 0);

    int tempPixelRow = -1;
    for (int ii = 0; ii < 3; ii++) {
      int tempPixelRowIndex = -1;
      for (int jj = 0; jj < 3; jj++) {

        int p1 = row + tempPixelRow;
        int p2 = pixelRowIndex + tempPixelRowIndex;
        if (pixelWithinBounds(p1, p2)) {
          IPixel tempPixel = oldImage.getPixel(p1, p2);
          tempPixel.scaleChannels(blurKernel[ii][jj]);
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
