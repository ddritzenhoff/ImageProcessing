package cs3500.imageProcessing.model;

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

  protected final int imageWidth;
  protected final int imageHeight;
  protected final int maxValue;
  protected final List<List<IPixel>> pixelImage;

  /**
   * Constructs a PixelImage object with a default file name.
   *
   * @param pixelImage the 2D array of pixels to represent an image.
   */
  public PixelImage(List<List<IPixel>> pixelImage, String fileName) {
    this.pixelImage = pixelImage;
    this.imageHeight = getNumRows();
    this.imageWidth = getNumPixelsInRow();
    this.maxValue = 255;
    this.fileName = fileName;
  }

  @Override
  public void render(String type) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      String newFileName = fileName;
      if (newFileName.contains("src/files/")) {
        newFileName = fileName.substring(10);
      }
      System.out.println(newFileName);
      String newTitle = newFileName + "." + type;
      fos = new FileOutputStream(newTitle);
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

  //TODO: added this.
  public String getFileName() {
    return this.fileName;
  }

  public int getNumRows() {
    return this.pixelImage.size();
  }

  public int getNumPixelsInRow() {
    return this.pixelImage.get(0).size();
  }

  public IPixel getPixel(int row, int pixelRowIndex) {
    if (row < 0 || row >= getNumRows() || pixelRowIndex < 0
        || pixelRowIndex >= getNumPixelsInRow()) {
      throw new IllegalArgumentException("pixel does not exist. Indices are out of bounds");
    }

    IPixel oldPixel = this.pixelImage.get(row).get(pixelRowIndex);

    return new Pixel(oldPixel.getR(), oldPixel.getG(), oldPixel.getB());
  }

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
  public boolean equals(IPixelImage o) {

    if (o == null) {
      return false;
    }
    IPixelImage that =  o;
    PixelImage np = new PixelImage(o.getPixels(), "Test");

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
    return Objects.hash(imageWidth, imageHeight, maxValue, pixelImage, fileName);
  }
}
