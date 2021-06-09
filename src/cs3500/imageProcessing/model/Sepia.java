package cs3500.imageProcessing.model;

import java.util.List;

public class Sepia implements ITransformation {

  protected final IPixelImage oldImage;

  protected final double[][] sepiaMatrix =
      {{.393,  .769, .189},
          {.349,  .686, .168},
          {.272,  .534, .131}};


  public Sepia(IPixelImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IPixelImage apply(String id) {
    List<List<IPixel>> pixelRows = this.oldImage.getPixels();

    for (int row = 0; row < pixelRows.size(); row++) {
      List<IPixel> pixelRow = pixelRows.get(row);
      for (int pixelRowIndex = 0; pixelRowIndex < pixelRow.size(); pixelRowIndex++) {
        pixelRows.get(row).get(pixelRowIndex).applyMatrix(sepiaMatrix);

      }
    }
    return new PixelImage(pixelRows,id); //TODO: added a field for ID
  }

}



