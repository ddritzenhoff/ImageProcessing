package cs3500.imageProcessing.model;

import java.util.List;

public interface IPixelImage {

  /**
   * Gets a copy of all the pixels within the image.
   *
   * @return the 2D arraylist representation of the image.
   */
  List<List<IPixel>> getPixels();

  /**
   * Creates a copy, not a reference, of the pixel at the specified row and column.
   *
   * @param row           the row at which the pixel exists (starting from 0)
   * @param pixelRowIndex the column at which the pixel exists (starting from 0)
   * @return the pixel copy of the image.
   */
  IPixel getPixel(int row, int pixelRowIndex);

  /**
   * Translates a PixelImage into whatever filetype specified.
   *
   * @param type the file extension type to be created.
   */
  void render(String type);

  /**
   * Gets the number of rows that exist within the file.
   *
   * @return the number of rows of pixels within the file.
   */
  int getNumRows();

  /**
   * Gets the number of pixels within one row of pixels. This assumes all pixel rows are of the same
   * length.
   *
   * @return the number of pixels within one row of pixels.
   */
  int getNumPixelsInRow();

}
