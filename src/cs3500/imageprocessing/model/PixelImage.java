package cs3500.imageprocessing.model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is how an image is represented within this program. It is simply a collection of IPixels
 * arranged within a 2D structure to mimic the layout of an image.
 */
public class PixelImage implements IPixelImage {

  private final int imageWidth;
  private final int imageHeight;
  private final int maxValue;
  protected final List<List<IPixel>> pixelImage;

  /**
   * Constructs a PixelImage object.
   *
   * @param pixelImage the 2D array of pixels to represent an image.
   */
  public PixelImage(List<List<IPixel>> pixelImage) {
    this.pixelImage = ImageUtil.requireNonNull(pixelImage, "PixelImage constructor");
    this.imageHeight = getNumRows();
    this.imageWidth = getNumPixelsInRow();
    this.maxValue = 255;
  }


  @Override
  public void render(String type, String newFileName) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(newFileName);
      String newTitle = newFileName + "." + type;
      fos = new FileOutputStream("res/" + newTitle);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes(
          "P3\n" + "# Created by GIMP version 2.10.20 PNM plug-in" + "\n" + imageWidth + " "
              + imageHeight + "\n" + maxValue);

    } catch (IOException e) {
      e.printStackTrace();
    }
    for (List<IPixel> lop : pixelImage) {
      for (IPixel pixel : lop) {
        try {
          value = pixel.toString();
          outStream.writeBytes(value);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    try {
      outStream.writeBytes("\n");
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getNumRows() {
    return this.pixelImage.size();
  }

  @Override
  public int getNumPixelsInRow() {
    return this.pixelImage.get(0).size();
  }

  @Override
  public IPixel getPixel(int row, int pixelRowIndex) {
    if (row < 0 || row >= getNumRows() || pixelRowIndex < 0
        || pixelRowIndex >= getNumPixelsInRow()) {
      throw new IllegalArgumentException("pixel does not exist. Indices are out of bounds");
    }

    IPixel oldPixel = this.pixelImage.get(row).get(pixelRowIndex);

    return new Pixel(oldPixel.getR(), oldPixel.getG(), oldPixel.getB());
  }

  @Override
  public List<List<IPixel>> getPixels() {

    List<List<IPixel>> imageCopy = new ArrayList<>();
    for (int row = 0; row < getNumRows(); row++) {
      List<IPixel> tempRow = new ArrayList<>();
      for (int pixelRowIndex = 0; pixelRowIndex < getNumPixelsInRow(); pixelRowIndex++) {
        tempRow.add(getPixel(row, pixelRowIndex));
      }
      imageCopy.add(tempRow);
    }

    return imageCopy;
  }

  @Override
  public boolean equals(Object o) {

    if (o == null) {
      return false;
    }
    if (!(o instanceof IPixelImage)) {
      return false;
    }
    IPixelImage that = (IPixelImage)o;

    //IPixelImage that = new PixelImage(o.getPixels());

    boolean start = true;
    List<List<IPixel>> thatPixels = that.getPixels();
    for (int i = 0; i < this.getNumRows(); i++) {
      for (int j = 0; j < this.getNumPixelsInRow(); j++) {
        start = start && this.pixelImage.get(i).get(j).equals(thatPixels.get(i).get(j));
      }
    }
    return start;
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageWidth, imageHeight, maxValue, pixelImage);
  }

}
