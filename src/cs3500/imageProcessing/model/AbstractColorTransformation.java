package cs3500.imageProcessing.model;

import java.util.List;
import java.util.Objects;

public class AbstractColorTransformation implements ITransformation {

  protected final IPixelImage oldImage;
  protected final double[][] colorMatrix;
  //protected final int kernelSize;

  protected AbstractColorTransformation(IPixelImage oldImage, double[][] colorMatrix) {
    this.oldImage = Objects.requireNonNull(oldImage);
    this.colorMatrix = Objects.requireNonNull(colorMatrix);
    //this.kernelSize = this.kernel.length;
  }

  @Override
  public IPixelImage apply() {
    List<List<IPixel>> pixelRows = this.oldImage.getPixels();

    for (int row = 0; row < pixelRows.size(); row++) {
      List<IPixel> pixelRow = pixelRows.get(row);
      for (int pixelRowIndex = 0; pixelRowIndex < pixelRow.size(); pixelRowIndex++) {
        pixelRows.get(row).get(pixelRowIndex).applyMatrix(colorMatrix);
      }
    }
    return new PixelImage(pixelRows); //TODO: added a field for ID

  }
}
