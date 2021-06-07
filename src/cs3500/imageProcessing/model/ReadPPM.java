package cs3500.imageProcessing.model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadPPM implements IPixelImage {
  private int imageWidth;
  private int imageHeight;
  private List<IPixel> pixelRow;
  private List<List<IPixel>> pixelImage;

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public IPixelImage generatePPM(String filename) {

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
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
    pixelRow = new ArrayList<>(imageWidth);
    //System.out.println("Width of image: " + imageWidth);

    imageHeight = sc.nextInt();
    pixelImage = new ArrayList<>(imageHeight);
    //System.out.println("Height of image: " + imageHeight);

    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    for (int i = 0; i < imageHeight; i++ ) {
      for (int j = 0; j < imageWidth; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        IPixel tempPixel = new Pixel(r,g,b);
        pixelRow.add(tempPixel);

        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        System.out.print("(" + j + "," + i + "): " + r + "," + g + "," + b + "  ");
      }
      System.out.println("\n");
      pixelImage.add(pixelRow);
    }

    return null;
  }


  @Override
  public void render() {

  }
//
//  public static void main(String []args) {
//    String filename2;
//
//    if (args.length>0) {
//      filename2 = args[0];
//    }
//    else {
//      filename2 = "src/Koala.ppm";
//    }
//
//    new ReadPPM(filename2);
//  }




}
