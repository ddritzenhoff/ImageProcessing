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
  protected final List<List<IPixel>> pixelImage;
  protected final String fileName;

  public PixelImage(int imageWidth, int imageHeight, int maxValue,
      List<List<IPixel>> pixelImage, String fileName) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.maxValue = maxValue;
    this.pixelImage = pixelImage;
    this.fileName = fileName;
  }

  public PixelImage(List<List<IPixel>> pixelImage, String newFileName) {
  this.pixelImage = pixelImage;
  this.imageHeight = getNumRows();
  this.imageWidth = getNumPixelsInRow();
  this.maxValue = 255; // TODO: question: is this correct?
  this.fileName = newFileName;
  }

  @Override
  public void render(String type) {
    //currently returns a string render.
    String value = "";
    FileOutputStream fos = null;
    try {
//      System.out.println(this.fileName);
      String newFileName = fileName;
      if (newFileName.contains("src/files/")){
        newFileName =  fileName.substring(10);
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



    IPixelImage testImage2 = ImageUtil.PPMtoPixelImage("src/files/Koala.ppm");
//    testImage2.render("jpg");
//    testImage2.render("ppm");
//    testImage2.render("png");
    testImage2.render("ppm");

//    IPixelImage rect = new Checkerboard(50, 10).returnPixelImage();
//    rect.render("ppm");
    // IPixelImage testImage = new PixelImage().generatePPM("src/Koala.ppm");
    //testImage.render();
    IPixelImage testImage3 = ImageUtil.PPMtoPixelImage("EditedBlurredKoala1.png");
    //Blur test2 = new Blur()
     Blur test = new Blur(testImage3);

     //test.apply("k");
     test.apply("k").render("png");



//     Greyscale testGreyscale = new Greyscale(testImage2);
//     testGreyscale.apply("koalaTestGreyscale").render("png");

   // testImage.render();
  }

}
