package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;

public class Blur implements ITransformation {

  protected final IPixelImage oldImage;
  //protected final IKernel blurKernel;

  protected final double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
      {1.0 / 8, 1.0 / 4, 1.0 / 8},
      {1.0 / 16, 1.0 / 8, 1.0 / 16}};


  public Blur(IPixelImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IPixelImage apply(String id) {
    List<List<IPixel>> pixelRows = this.oldImage.getPixels();

    for (int row = 0; row < pixelRows.size(); row++) {
      List<IPixel> pixelRow = pixelRows.get(row);
      for (int pixelRowIndex = 0; pixelRowIndex < pixelRow.size(); pixelRowIndex++) {

        // get pixels surrounding this pixel.
        List<List<IPixel>> kernelPixels = getKernelPixels(pixelRows, row, pixelRowIndex);

        // map pixels with kernel

        // add pixels to newImage

      }
    }

    return null;
  }

  protected List<List<IPixel>> getKernelPixels(List<List<IPixel>> pixelRows, int row,
      int pixelRowIndex) {

    List<List<IPixel>> kernel = new ArrayList<>();
    for (int rowX = -1; rowX <= 1; rowX++) {
      List<IPixel> kernelRow = new ArrayList<>();
      for (int colX = -1; colX <= 1; colX++) {

        int tempPixelRow = row + rowX;
        int tempPixelRowIndex = pixelRowIndex + colX;
        if (pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) {

          IPixel tempPixel = this.oldImage.getPixel(tempPixelRow, tempPixelRowIndex);
          kernelRow.add(tempPixel);
        } else {
          kernelRow.add(null);
        }
      }
      kernel.add(kernelRow);
    }

    return kernel;
  }

  protected boolean pixelWithinBounds(int tempPixelRow, int tempPixelRowIndex) {
    return tempPixelRow >= 0 && tempPixelRow < this.oldImage.getNumRows() && tempPixelRowIndex >= 0
        && tempPixelRowIndex < this.oldImage.getNumPixelsInRow();
  }
}
