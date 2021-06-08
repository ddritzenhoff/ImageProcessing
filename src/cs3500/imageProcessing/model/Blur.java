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

        int numDiagonalNeighbors = getNumEdgeNeighbors(row, pixelRowIndex);
        int numStraightNeighbors = getNumStraightNeighbors(row, pixelRowIndex);

        pixelRows.get(row).get(pixelRowIndex).scaleChannels(
            (1.0 / 16) * numDiagonalNeighbors * (1.0 / 8) * numStraightNeighbors * (1.0 / 4));

      }
    }
    return new PixelImage(pixelRows,id); //TODO: added a field for ID
  }

  // TODO: you could add this as a value within the IPixel
  protected int getNumEdgeNeighbors(int row, int pixelRowIndex) {
    // TODO: make more dynamic
    int numEdgeNeighbors = 0;

    // check top left corner
    int tempPixelRow = row + -1;
    int tempPixelRowIndex = pixelRowIndex + -1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numEdgeNeighbors++;

    // check top right corner
    tempPixelRow = row + -1;
    tempPixelRowIndex = pixelRowIndex + 1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numEdgeNeighbors++;

    // check bottom left corner
    tempPixelRow = row + 1;
    tempPixelRowIndex = pixelRowIndex + -1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numEdgeNeighbors++;

    // check bottom right corner
    tempPixelRow = row + 1;
    tempPixelRowIndex = pixelRowIndex + 1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numEdgeNeighbors++;

    return numEdgeNeighbors;
  }

  // TODO: you could add this as a value within the IPixel
  protected int getNumStraightNeighbors(int row, int pixelRowIndex) {
    // TODO: make more dynamic
    int numStraightNeighbors = 0;

    // check left
    int tempPixelRow = row;
    int tempPixelRowIndex = pixelRowIndex + -1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numStraightNeighbors++;

    // check right
    tempPixelRow = row;
    tempPixelRowIndex = pixelRowIndex + 1;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numStraightNeighbors++;

    // check up
    tempPixelRow = row - 1;
    tempPixelRowIndex = pixelRowIndex;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numStraightNeighbors++;

    // check down
    tempPixelRow = row + 1;
    tempPixelRowIndex = pixelRowIndex;
    if(pixelWithinBounds(tempPixelRow, tempPixelRowIndex)) numStraightNeighbors++;

    return numStraightNeighbors;
  }

  protected boolean pixelWithinBounds(int tempPixelRow, int tempPixelRowIndex) {
    return tempPixelRow >= 0 && tempPixelRow < this.oldImage.getNumRows() && tempPixelRowIndex >= 0
        && tempPixelRowIndex < this.oldImage.getNumPixelsInRow();
  }
}
