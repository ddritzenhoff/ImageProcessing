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

  protected final int imageWidth;
  protected final int imageHeight;
  protected final int maxValue;
  private final List<IPixel> pixelRow;
  protected final List<List<IPixel>> pixelImage;
  protected final String fileName;


  public PixelImage(int imageWidth, int imageHeight, int maxValue,
      List<IPixel> pixelRow,
      List<List<IPixel>> pixelImage, String fileName) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.maxValue = maxValue;
    this.pixelRow = pixelRow;
    this.pixelImage = pixelImage;
    this.fileName = fileName;
  }

//  PixelImage() {
//    //this.fileName = "";
//    this.pixelImage = pixelImage;
//  }

//  PixelImage(List<List<IPixel>> pixelImage, String fileName) {
//    this.fileName = fileName;
//    this.pixelImage = pixelImage;
//  }


  @Override
  public void render(String type) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
      System.out.println(this.fileName);
      String newFileName = "Edited" + "koala" + "." + type;
      fos = new FileOutputStream(newFileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));

    try {
      outStream.writeBytes("P3\n" + "# Created by GIMP version 2.10.20 PNM plug-in" + "\n" + imageWidth + " " + imageHeight  + "\n" + maxValue );
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
      outStream.writeBytes("\n");
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

    IPixelImage testImage2 = ImageUtil.PPMtoPixelImage("src/files/Koala.ppm");
    testImage2.render("jpg");
   // IPixelImage testImage = new PixelImage().generatePPM("src/Koala.ppm");
    //testImage.render();

   // testImage.render();
  }

}
