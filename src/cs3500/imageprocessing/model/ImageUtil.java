package cs3500.imageprocessing.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Additionally, this class has static utility methods to convert from a PPM to a IPixelImage, and
 * clamp the values of a pixel.
 */
public class ImageUtil {

  /**
   * method to verify that the file that will be converted into a IPixelImage exists.
   *
   * @param fileName represents the string name of the file.
   * @return a Scanner containing the fileInputStream of the given fileName.
   */
  protected static Scanner requireFileExists(String fileName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileName));
      return sc;
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file name incorrect.");
    }

  }

  /**
   * converts a PPM file into a pixel image.
   *
   * @param fileDirectory name of the file.
   * @return a new IPixelImage representation of a PPM image.
   */
  protected static IPixelImage ppmToPixelImage(String fileDirectory) {

    int imageWidth;
    int imageHeight;
    int maxValue;
    List<List<IPixel>> pixelImage;

    Scanner sc = requireFileExists(fileDirectory);
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
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

    imageHeight = sc.nextInt();
    pixelImage = new ArrayList<>(imageHeight);

    maxValue = sc.nextInt();

    for (int i = 0; i < imageHeight; i++) {
      List<IPixel> tempPixelRow = new ArrayList<>(imageWidth);
      for (int j = 0; j < imageWidth; j++) {

        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        IPixel tempPixel = new Pixel(r, g, b);
        tempPixelRow.add(tempPixel);

      }
      pixelImage.add(tempPixelRow);
    }

    return new PixelImage(pixelImage);
  }

  /**
   * clamps the integer value passed in. the clamp default value is set to 255. Used to ensure that
   * a pixel value does not go below 0, and does not go past 255.
   *
   * @param value an integer that will be either returned, or replaced with 0 or 255.
   * @return a value between 0 and 255.
   */
  public static int pixelClamp(int value) {
    if (value > 255) {
      return 255;
    }
    if (value < 0) {
      return 0;
    }
    return value;
  }

  /**
   * a method that checks if the object is null.
   *
   * @param o       object to be checked for nullity
   * @param message will throw a IlelgalARgument exception with the given message
   * @param <T>     generic for object type
   * @return either the object, or throws an illegal argument exception.
   */
  public static <T> T requireNonNull(T o, String message) {
    if (o == null) {
      throw new IllegalArgumentException("Null " + message);
    }
    return o;
  }

  //file will need: file location, file name, visibility.
  public static void saveAll(String newFileName, Map<String, Layer> images) {

    //TODO: one file with all of the txt information
    //TODO: several files with all of the other files actual pixels
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(newFileName);
      String newTitle = newFileName + "." + "txt";
      fos = new FileOutputStream(newTitle);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

//    try {
//      outStream.writeBytes(
//          "P3\n" + "# Created by GIMP version 2.10.20 PNM plug-in" + "\n" + imageWidth + " "
//              + imageHeight + "\n" + maxValue);
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    for (Layer i : images.values()) {
      try {
        outStream.writeBytes(i.toString()+ "\n");

      }
      catch (IOException e) {
        e.printStackTrace();
      }

    }

    //sends all the files in the registry to a destination folder
    for (String s : images.keySet()) {
      IPixelImage img = images.get(s).getImage();
      ImageUtil.pixelImageToTxtFile(s,"/res/", img);
    }


    try {
      outStream.writeBytes("\n");
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  static ProcessingModel readAll(String fileName) {
    //TODO: get this to work.
    return null;
  }

  // I can use render instead.
  static void pixelImageToTxtFile(String fileName, String destinationFileLocation, IPixelImage image) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(destinationFileLocation+fileName + ".txt");
      String newTitle = destinationFileLocation+fileName + ".txt";
      fos = new FileOutputStream(newTitle);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes(
          image.getNumPixelsInRow() + " "
              + image.getNumRows() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (List<IPixel> lop : image.getPixels()) {
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
      //outStream.writeBytes("\n");
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static IPixelImage txtFileToPixelImage(String fileName, String fileLocation) {
    List<List<IPixel>> pixelCols = new ArrayList<>();
    try {
      File txtFile = new File(fileLocation);
      Scanner scanner = new Scanner(txtFile);
      int intRows = 0;
      int intCols = 0;

      if (scanner.hasNextLine()) {
        intRows = Integer.valueOf(scanner.nextInt());
        intCols = Integer.valueOf(scanner.nextInt());
      } else {
        throw new IllegalArgumentException("couldnt get rows and cols from this IPixelImageFile.");

      }
      for ( int j = 0 ; j < intCols ; j++ ) {
        List<IPixel> pixelRow = new ArrayList<>();
        for (int i = 0 ; i < intRows ; i++ ) {

          IPixel newPixel = new Pixel(Integer.valueOf(scanner.nextLine()),
              Integer.valueOf(scanner.nextLine()),
              Integer.valueOf(scanner.nextLine()));
          pixelRow.add(newPixel);
        }
        pixelCols.add(pixelRow);
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return new PixelImage(pixelCols);
  }




  static BufferedImage normalImageToBufferedImage(String fileLocation) {
    BufferedImage newBufferedImage;
    try {
      newBufferedImage = ImageIO.read(new File(fileLocation));
      return newBufferedImage;

    } catch (IOException e) {
      e.printStackTrace();
    }
    throw new IllegalArgumentException("failed to return buffered image");
  }

  static IPixelImage bufferedImageToIPixelImage(BufferedImage image) {

    List<List<IPixel>> col = new ArrayList<>();
    for(int i = 0 ; i < image.getHeight() ; i ++) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0 ; j < image.getWidth() ; j++ ) {
        Color pixelColor = new Color (image.getRGB(i,j));
        row.add(new Pixel(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
      }
      col.add(row);
    }
    return new PixelImage(col);
  }


  static void pixelImageToBufferedImage(Layer image) {

    int cols = image.getImage().getNumPixelsInRow();
    int rows = image.getImage().getNumRows(); //BufferedImage.TYPE_4BYTE_ARGB
    BufferedImage tempBufferedImage = new BufferedImage(cols,rows,1);
    for(int i = 0 ; i < rows ; i++) {
      for (int j = 0 ; j < cols ; j++ ) {
        IPixel current = image.getImage().getPixel(i,j);
        Color currentColor = new Color(current.getR(), current.getG(), current.getB());
        tempBufferedImage.setRGB(i,j,currentColor.getRGB());
      }
    }
    //use set rgb.
//    BufferedImage b_img = new BufferedImage(image.getImage().getWith(),
//        image.getImage().getHeight(),
//        BufferedImage.TYPE_4BYTE_ARGB);
  }



  static void saveBufferedImage(String fileName, BufferedImage image, String type) {
    File outputfile = new File("res/"+fileName +"."+type);
    try {
      ImageIO.write(image, type, outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  // TODO: QUESTION: What happens when something other than a supported file type is read?
  public static void saveImage(String fileName) {
    /*
    Notes:
    - it seems like TYPE_INT_ARGB is the default RGB color model, and sRGB is the default color
    space.
     */

    ImageUtil.requireNonNull(fileName, "saveImage file name null");

    // at this point, you know that the file name is non-null.

    BufferedImage img;
    int w, h;

    try {
      img = ImageIO.read(new File(fileName)); // "bld.jpg" is an example input.
      w = img.getWidth();
      h = img.getHeight();
      if (img.getType() != BufferedImage.TYPE_INT_RGB) {
        // do something?
      }

      // convert BufferedImage to PixelImage??
    } catch (IOException e) {
      System.out.println("Image could not be read");
      System.exit(1);
    }
  }


  /**
   * this method will do the thing:
   * A multi-layered image can be saved simply as a collection of files:
   * one for each layer (as regular images), and one (text) file that
   * stores the locations of all the layer files.
   */
  public static void saveMultiLayerImage() {

  }



  }

