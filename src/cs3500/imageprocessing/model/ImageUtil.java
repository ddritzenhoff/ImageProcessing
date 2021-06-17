package cs3500.imageprocessing.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
  public static void saveAll(String modelFileName, Map<String, ILayer> layers) {

    //TODO: one file with all of the txt information
    //TODO: several files with all of the other files actual pixels
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println("res/"+modelFileName);
      String newTitle = "res/"+modelFileName + "." + "txt";
      fos = new FileOutputStream(newTitle);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    for (String s : layers.keySet()) {
      try {
        outStream.writeBytes(s + " " + layers.get(s).toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    //sends all the files in the registry to a destination folder
    for (String s : layers.keySet()) {
      IPixelImage img = layers.get(s).getImage();
      ImageUtil.pixelImageToTxtFile(s,"res/saveall/", img);
    }
  }

  //reconstructs a ProcessingModel from two files.
  static ProcessingModel readAll(String modelFileName) {
     Map<String, ILayer> loadedLayers = new HashMap<>();
     String layerName;
     int order;
     boolean visibility;
     //String fileName;
    // String fileLocation;

    try {
      File myObj = new File("res/"+modelFileName);
      Scanner scanner = new Scanner(myObj);
      while (scanner.hasNextLine()) {
        String data = scanner.nextLine();
        String[] arr = data.split(" ");
        layerName = arr[0];
        order = Integer.parseInt(arr[1]);
        visibility = Boolean.parseBoolean(arr[2]);
       // fileName = arr[3];
       // fileLocation = arr[4];

       // IPixelImage tempImage2 =
       //     ImageUtil.txtFileToPixelImage(fileName);
        IPixelImage tempImage =
            ImageUtil.txtFileToPixelImage(layerName);
        ILayer tempLayer = new Layer(visibility,tempImage,order);

        //[order,   visibility,    fileName, fileLocation ]
        //-> model    -> model     -> txtFileToPixelImage
        loadedLayers.put(layerName,tempLayer);
        System.out.println(data);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return new ProcessingModel(modelFileName, loadedLayers);
  }

  //WORKS
  static void pixelImageToTxtFile(String fileName, String destinationFileLocation, IPixelImage image) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(destinationFileLocation+fileName + ".txt");
      String newTitle = destinationFileLocation+fileName + ".txt";
      File nf = new File(newTitle);
      fos = new FileOutputStream(nf);


    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes(
          image.getNumPixelsInRow() + " "
              + image.getNumRows());
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

  //WORKS
  static IPixelImage txtFileToPixelImage(String layerName) {
    List<List<IPixel>> pixelCols = new ArrayList<>();
    try {
      File txtFile = new File("res/"+layerName);
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
          int r = 0;
          int g = 0;
          int b = 0;
          if (scanner.hasNextLine()) {
            r = Integer.parseInt(scanner.next());
            if (scanner.hasNextLine()) {
              g = Integer.parseInt(scanner.next());
              if (scanner.hasNextLine()) {
                b = Integer.parseInt(scanner.next());
              }
            }
          }
          IPixel newPixel = new Pixel(r,g,b);
          pixelRow.add(newPixel);
        }
        pixelCols.add(pixelRow);
      }
      scanner.close();

    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(layerName + " does not exist.") ;
      //System.out.println("An error occurred.");
      //  e.printStackTrace();
    }
    return new PixelImage(pixelCols);
  }



  //WORKS
  static BufferedImage normalImageToBufferedImage(String fileLocation) {
    ImageUtil.requireNonNull(fileLocation, "normalImagetoBufferedImage");
    BufferedImage newBufferedImage;
    try {
      newBufferedImage = ImageIO.read(new File(fileLocation));
      return newBufferedImage;

    } catch (IOException e) {
      throw new IllegalArgumentException(fileLocation + " does not exist.") ;
    }
  }

  //WORKS
  static IPixelImage bufferedImageToIPixelImage(BufferedImage image) {

    List<List<IPixel>> row = new ArrayList<>();
    for(int i = 0 ; i < image.getHeight(); i ++) {
      List<IPixel> col = new ArrayList<>();
      for (int j = 0 ; j < image.getWidth() ; j++ ) {
        Color pixelColor = new Color (image.getRGB(j,i));
        col.add(new Pixel(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue()));
      }
      row.add(col);
    }
    return new PixelImage(row);
  }

//WORKS
  static BufferedImage pixelImageToBufferedImage(IPixelImage image) {

    int cols = image.getNumPixelsInRow();
    int rows = image.getNumRows(); //BufferedImage.TYPE_4BYTE_ARGB
    BufferedImage tempBufferedImage = new BufferedImage(cols,rows,1);
    for(int i = 0 ; i < rows ; i++) {
      for (int j = 0 ; j < cols ; j++ ) {
        IPixel current = image.getPixel(i,j);
        Color currentColor = new Color(current.getR(), current.getG(), current.getB());
        tempBufferedImage.setRGB(j,i,currentColor.getRGB());
      }
    }
    //use set rgb.
//    BufferedImage b_img = new BufferedImage(image.getImage().getWith(),
//        image.getImage().getHeight(),
//        BufferedImage.TYPE_4BYTE_ARGB);
    return tempBufferedImage;
  }


  //WORKS
  static void saveBufferedImage(String fileName, BufferedImage image, String type) {
    File outputfile = new File("res/"+fileName +"."+type);
    try {
      ImageIO.write(image, type, outputfile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void imageWrapperExport(IPixelImage image, String newFileName) {
    String extension = newFileName.substring(newFileName.length()-3);
    if (extension.equals(".ppm")) {
      image.render("ppm", newFileName);
    }
    else  {
      saveBufferedImage(newFileName,ImageUtil.pixelImageToBufferedImage(image),extension);
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

