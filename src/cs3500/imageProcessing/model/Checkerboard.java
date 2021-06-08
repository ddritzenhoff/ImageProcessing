package cs3500.imageProcessing.model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Checkerboard {
  protected  int imageWidth;
  protected  int imageHeight;
  protected int numTiles;
  protected  int maxValue = 255;
  protected List<List<IPixel>> pixelImage;
  protected IPixelImage finalPix;

  //protected final String fileName;
  //PixelImage newCheckerBoard = super(1000,1000,255,temp,"c");



  public Checkerboard(int sizeTile, int numTiles) {

    this.numTiles = numTiles;
    this.imageWidth = sizeTile * numTiles;
    this.imageHeight = sizeTile * numTiles;
    this.pixelImage = makeCheckerboard();


  }

//  public Checkerboard(int imageWidth, int imageHeight, int maxValue,
//      List<IPixel> pixelRow,
//      List<List<IPixel>> pixelImage, String fileName) {
//    super(imageWidth, imageHeight, maxValue, pixelRow, pixelImage, fileName);
//  }

  public IPixelImage returnPixelImage() {
    return new PixelImage(imageWidth,imageHeight,255,pixelImage,"CHECKERBOARD");
  }

  public List<List<IPixel>> makeCheckerboard() {
    IPixel blackPixel = new Pixel(0,0,0);
    IPixel whitePixel = new Pixel(255,255,255);
    List<List<IPixel>> image = new ArrayList<>();


//    for (int i = 0 ; i < imageHeight ; i++){
//      if (( i % (numTiles )) % 2 == 0) {
//        row.add(blackPixel);
//      }
//      else {
//        row.add(whitePixel);
//      }
//    }
    //make a scale factor. it will be the width of every box.
    int scaleFactor = imageWidth/numTiles;
    //do black a scale factor amount of times, then white, then black, etc.

    List<IPixel> row = new ArrayList<>();
    for (int vertB = 0 ; vertB < scaleFactor; vertB++) {
      for (int x = 0 ; x < imageHeight; x++) {
        for (int i = 0; i < scaleFactor; i++) {
          row.add(blackPixel);
          x++;
        }
        for (int i = 0; i < scaleFactor; i++) {
          row.add(whitePixel);
          x++;
        }
      }
      image.add(row);

    }
    for (int vertW = 0 ; vertW < scaleFactor; vertW++) {

      for (int x = 0 ; x < imageHeight; x++) {
        for (int i = 0; i < scaleFactor; i++) {
          row.add(whitePixel);
          x++;
        }
        for (int i = 0; i < scaleFactor; i++) {
          row.add(blackPixel);
          x++;
        }
      }
      image.add(row);

    }



    return image ;
  }





}
