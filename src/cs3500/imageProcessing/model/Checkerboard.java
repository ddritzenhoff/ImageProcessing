package cs3500.imageProcessing.model;


import java.awt.Color;
import java.util.List;

public class Checkerboard extends PixelImage {
  protected  int imageWidth = 0;
  protected  int imageHeight = 0;
  protected  int maxValue = 255;
  protected final List<List<IPixel>> pixelImage;

  protected IPixelImage p = new
  //protected final String fileName;
  //PixelImage newCheckerBoard = super(1000,1000,255,temp,"c");


  public Checkerboard(int sizeTile, int numTiles, Color colors) {
    super(imageWidth,imageHeight,255, new List<List<IPixel>> ,"c");
    imageWidth = sizeTile * numTiles;
    imageHeight = sizeTile * numTiles;

    super.


  }

  IPixelImage makeCheckerboard(int imageWidth, numTiles) {
    IPixel blackPixel = new Pixel(0,0,0);
    IPixel whitePixel = new Pixel(255,255,255);
    IPixelImage newPixelImage = new PixelImage(1000,1000,255,
        null, "CHECKERBOARD");
    )
    for (int i = 0; i < numTiles ; i++) {
      for()


    }
      return new PixelImage(1000,1000,255,
          null, "CHECKERBOARD"); }





  }
