package cs3500.imageProcessing.model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PixelImage implements IPixelImage {
  private int imageWidth = 1024;
  private int imageHeight = 768;
  private int maxValue = 255;
  //private List<IPixel> pixelRow;
  private List<List<IPixel>> pixelImage;
  private String fileName;


  PixelImage() {
    //this.fileName = "";
    this.pixelImage = pixelImage;
  }

  PixelImage(List<List<IPixel>> pixelImage, String fileName) {
    this.fileName = fileName;
    this.pixelImage = pixelImage;
  }


  public static IPixelImage PPMtoPixelImage(String filename) {



    this.fileName = filename;


    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null; //TODO: Not sure if this is good.
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

    this.imageWidth = sc.nextInt();
   // pixelRow = new ArrayList<>(imageWidth);
    //System.out.println("Width of image: " + imageWidth);

    this.imageHeight = sc.nextInt();
    this.pixelImage = new ArrayList<>(imageHeight);
    //System.out.println("Height of image: " + imageHeight);

    this.maxValue = sc.nextInt();
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

    return new PixelImage(pixelImage, fileName);

  }

  @Override
  public void render() {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(this.fileName);
      fos = new FileOutputStream("file.jpg");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes("P3\n" + "# Created by Ben and Dom" + "\n" + imageWidth + " " + imageHeight  + "\n" + maxValue );
//      outStream.writeUTF("\nP3");
//      outStream.writeUTF("\n# Created by Ben and Dom "  );
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (List<IPixel> lop : pixelImage ) {
      for (IPixel pixel : lop ) {
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
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //return null;
  }


  public static void main(String []args) {
    String filename2;

    if (args.length>0) {
      filename2 = args[0];
    }
    else {
      filename2 = "src/Koala.ppm";
    }

   IPixelImage testImage = new PixelImage().generatePPM("src/Koala.ppm");
    testImage.render();

   // testImage.render();
  }

}
