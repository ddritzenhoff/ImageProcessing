package cs3500.imageProcessing.model;

import java.util.List;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PixelImage implements IPixelImage{

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

}
