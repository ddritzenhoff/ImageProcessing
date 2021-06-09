package cs3500.imageProcessing.model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is how an image is represented within this program. It is simply a collection of IPixels
 * arranged within a 2D structure to mimic the layout of an image.
 */
public class PixelImage implements IPixelImage {

  protected final int imageWidth;
  protected final int imageHeight;
  protected final int maxValue;
  protected final List<List<IPixel>> pixelImage;
  protected final String fileName;

  /**
   * Constructs a PixelImage object.
   *
   * @param imageWidth  the number of pixels within a row of pixels.
   * @param imageHeight the number of pixels within a column of pixels.
   * @param maxValue    the max value that a pixel can take.
   * @param pixelImage  the 2D array of pixels to represent an image.
   * @param fileName    the name of the file.
   */
  public PixelImage(int imageWidth, int imageHeight, int maxValue,
      List<List<IPixel>> pixelImage, String fileName) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.maxValue = maxValue;
    this.pixelImage = pixelImage;
    this.fileName = fileName;
  }

  /**
   * Constructs a PixelImage object with a default file name.
   *
   * @param pixelImage the 2D array of pixels to represent an image.
   */
  public PixelImage(List<List<IPixel>> pixelImage) {
    this.pixelImage = pixelImage;
    this.imageHeight = getNumRows();
    this.imageWidth = getNumPixelsInRow();
    this.maxValue = 255;
    this.fileName = "tempFileName";
  }

  // TODO: consider removing fileName and putting it into model.
  // TODO: also consider to move render a different class.

  @Override
  public void render(String type) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
//      System.out.println(this.fileName);
      String newFileName = fileName;
      if (newFileName.contains("src/files/")) {
        newFileName = fileName.substring(10);
      }
      System.out.println(newFileName);
      String newTitle = "Edited" + newFileName + "." + type;
      fos = new FileOutputStream(newTitle);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes(
          "P3\n" + "# Created by GIMP version 2.10.20 PNM plug-in" + "\n" + imageWidth + " "
              + imageHeight + "\n" + maxValue);
//      outStream.writeUTF("\nP3");
//      outStream.writeUTF("\n# Created by Ben and Dom "  );
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
        //TODO: observers.
      }
    }

    try {
      outStream.writeBytes("\n");
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //return null;
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


  public static void main(String[] args) {
    String filename2;

    if (args.length > 0) {
      filename2 = args[0];
    } else {
      filename2 = "src/Koala.ppm";
    }

    IPixelImage testImage2 = ImageUtil.PPMtoPixelImage("src/files/Boston.ppm");

    // IPixelImage testImage2 = new Checkerboard(100,10);
    ITransformation sepia = new Sepia();
    ITransformation blur = new Blur();
    ITransformation greyscale = new Greyscale();
    ITransformation sharpen = new Sharpen();

    List<ITransformation> commands = Arrays.asList(blur, blur, blur, blur);

    ITransformation Chained = new ChainedTransformation(testImage2, commands);

    IPixelImage test = Chained.apply(testImage2);
    test.render("ppm");


  }

}
