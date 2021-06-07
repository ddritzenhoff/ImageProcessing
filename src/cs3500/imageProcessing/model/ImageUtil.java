package cs3500.imageProcessing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method 
 *  as required.
 */
public class ImageUtil {

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

  protected static IPixelImage PPMtoPixelImage(String fileName) {

    int imageWidth;
    int imageHeight;
    int maxValue;
    List<IPixel> pixelRow = new ArrayList<>();
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

    return new PixelImage(imageWidth, imageHeight, maxValue, pixelRow, pixelImage, fileName);
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

