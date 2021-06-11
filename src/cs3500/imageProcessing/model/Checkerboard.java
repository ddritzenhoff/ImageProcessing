package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a checkerboard programmatically using the IPixelImage format. Represented as a 2D array.
 */
public class Checkerboard implements IPixelImage {

  protected IPixelImage delegate;
  protected int imageWidth;
  protected int imageHeight;
  protected int numTiles;
  protected List<List<IPixel>> pixelImage;

  /**
   * creates a Checkerboard depending on the pixel size of the tile, and the number of tiles. The
   * number of tiles will create a numTiles x numTiles sized checkerboard.
   *
   * @param sizeTile the pixel dimensions of a single tile.
   * @param numTiles the number of tiles to exist within the image.
   */
  public Checkerboard(int sizeTile, int numTiles) {
    this.numTiles = numTiles;
    this.imageWidth = sizeTile * numTiles;
    this.imageHeight = sizeTile * numTiles;
    this.pixelImage = makeCheckerboard();
    this.delegate = new PixelImage(pixelImage);
  }

  /**
   * Creates a checkerboard pattern programmatically.
   *
   * @return the 2D arraylist of pixels, which make up the checkerboard pattern.
   */
  private List<List<IPixel>> makeCheckerboard() {
    IPixel blackPixel = new Pixel(0, 0, 0);
    IPixel whitePixel = new Pixel(255, 255, 255);

    List<List<IPixel>> image = new ArrayList<>();

    //make a scale factor. it will be the width of every box.
    int scaleFactor = imageWidth / numTiles;
    //do black a scale factor amount of times, then white, then black, etc.

    List<IPixel> tempRow = makeRow(blackPixel, whitePixel, scaleFactor);
    List<IPixel> tempWRow = makeRow(whitePixel, blackPixel, scaleFactor);

    for (int j = 0; j < numTiles / 2; j++) {
      for (int i = 0; i < scaleFactor; i++) {
        image.add(tempRow);
      }
      for (int i = 0; i < scaleFactor; i++) {
        ;
        image.add(tempWRow);
      }
    }
    return image;
  }

  /**
   * Makes a row of the checkerboard.
   *
   * @param pixel1      a pixel containing color 1.
   * @param pixel2      a pixel containing color 2 where color 1 != color 2. This helps create the
   *                    checkerboard pattern.
   * @param scaleFactor the degree to how big the checkerboard row will be.
   * @return the row of pixels, which make up one of the checkerboard rows.
   */
  private List<IPixel> makeRow(IPixel pixel1, IPixel pixel2, int scaleFactor) {

    List<IPixel> row = new ArrayList<>();
    for (int x = 0; x < numTiles / 2; x++) {
      for (int i = 0; i < scaleFactor; i++) {
        row.add(pixel1);
      }
      for (int i = 0; i < scaleFactor; i++) {
        row.add(pixel2);
      }
    }

    return row;
  }


  @Override
  public List<List<IPixel>> getPixels() {
    return delegate.getPixels();
  }

  @Override
  public IPixel getPixel(int row, int pixelRowIndex) {
    return delegate.getPixel(row, pixelRowIndex);
  }

  @Override
  public void render(String type, String newFileName) {
    delegate.render(type, newFileName);

  }

  @Override
  public int getNumRows() {
    return delegate.getNumRows();
  }

  @Override
  public int getNumPixelsInRow() {
    return delegate.getNumPixelsInRow();
  }


  @Override
  public boolean equals(IPixelImage o) {
    return delegate.equals(o);
  }

}
