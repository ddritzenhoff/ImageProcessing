package cs3500.imageProcessing.model;

import java.util.List;

public class Greyscale implements ITransformation{

  protected final IPixelImage oldImage;
  //protected final IKernel blurKernel;

  protected final double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
      {1.0 / 8, 1.0 / 4, 1.0 / 8},
      {1.0 / 16, 1.0 / 8, 1.0 / 16}};


  public Greyscale(IPixelImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IPixelImage apply(String id) {
    List<List<IPixel>> pixelRows = this.oldImage.getPixels();

    for (int row = 0; row < pixelRows.size(); row++) {
      List<IPixel> pixelRow = pixelRows.get(row);
      for (int pixelRowIndex = 0; pixelRowIndex < pixelRow.size(); pixelRowIndex++) {

//        int numDiagonalNeighbors = getNumEdgeNeighbors(row, pixelRowIndex);
//        int numStraightNeighbors = getNumStraightNeighbors(row, pixelRowIndex);
//
//        pixelRows.get(row).get(pixelRowIndex).scaleRGB(.2126,.7152,.0722)
//            (.2126));
        pixelRows.get(row).get(pixelRowIndex).scaleRGB(.2126,.7152,.0722);

      }
    }
    return new PixelImage(pixelRows,id); //TODO: added a field for ID
  }

}
