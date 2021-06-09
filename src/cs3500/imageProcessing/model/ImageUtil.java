package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Additionally, this class has static utility methods to convert from a
 * PPM to a IPixelImage, and clamp the values of a pixel.
 */
public class ImageUtil {

  /**
   * method to verify that the file that will be converted into a IPixelImage exists
   * @param fileName represents the string name of the file.
   * @return a Scanner containing the fileInputStream of the given fileName.
   */
  protected static Scanner requireFileExists(String fileName) {
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(fileName));
      return sc;
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file name incorrect.");
    }

  }

  /**
   * converts a PPM file into a pixel image.
   * @param fileName name of the file.
   * @return a new IPi
   */
  protected static IPixelImage PPMtoPixelImage(String fileName) {

    int imageWidth;
    int imageHeight;
    int maxValue;
    List<List<IPixel>> pixelImage;

    Scanner sc = requireFileExists(fileName);
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    imageWidth = sc.nextInt();
    // pixelRow = new ArrayList<>(imageWidth);
    //System.out.println("Width of image: " + imageWidth);

    imageHeight = sc.nextInt();
    pixelImage = new ArrayList<>(imageHeight);
    //System.out.println("Height of image: " + imageHeight);

    maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    for (int i = 0; i < imageHeight; i++ ) {
      List<IPixel> tempPixelRow = new ArrayList<>(imageWidth);
      for (int j = 0; j < imageWidth; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        IPixel tempPixel = new Pixel(r,g,b);
        tempPixelRow.add(tempPixel);

        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        //System.out.print("(" + j + "," + i + "): " + r + "," + g + "," + b + "  ");
      }
      //System.out.println("\n");
      pixelImage.add(tempPixelRow);
    }

    return new PixelImage(imageWidth, imageHeight, maxValue, pixelImage, fileName);
  }

  public static int pixelClamp(int value) {
    if (value > 255) {
      return 255;
    }
    if (value < 0 ) {
      return 0 ;
    }
    return value;
  }


  //demo main
  public static void main(String []args) {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "src/Koala.ppm";
      }
      
//      ImageUtil.readPPM(filename);
  }

}

