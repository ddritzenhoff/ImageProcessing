package cs3500.imageProcessing.model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Checkerboard implements IPixelImage {
  protected IPixelImage delegate;
  protected  int imageWidth;
  protected  int imageHeight;
  protected int numTiles;
  protected  int maxValue = 255;
  protected List<List<IPixel>> pixelImage;


  //protected final String fileName;
  //PixelImage newCheckerBoard = super(1000,1000,255,temp,"c");



  public Checkerboard(int sizeTile, int numTiles) {
    //TODO: documentation regarding the parameters.

    this.numTiles = numTiles;
    this.imageWidth = sizeTile * numTiles;
    this.imageHeight = sizeTile * numTiles;
    this.pixelImage = makeCheckerboard();
    this.delegate = new PixelImage(pixelImage,"Checkerboard");

  }

  public IPixelImage returnPixelImage() {
  // return new PixelImage(imageWidth,imageHeight,255,pixelImage,"CHECKERBOARD");
    return delegate;
  }

  public List<List<IPixel>> makeCheckerboard() {
    IPixel blackPixel = new Pixel(1,255,1);
    IPixel whitePixel = new Pixel(255,255,255);

    List<List<IPixel>> image = new ArrayList<>();

    //make a scale factor. it will be the width of every box.
    int scaleFactor = imageWidth/numTiles;
    //do black a scale factor amount of times, then white, then black, etc.

    List<IPixel> tempRow = makeRow(blackPixel, whitePixel, scaleFactor);
    List<IPixel> tempWRow = makeRow(whitePixel, blackPixel, scaleFactor);

    for ( int j = 0 ; j < numTiles/2 ; j ++ ) {
      for (int i = 0; i < scaleFactor; i++) {
        image.add(tempRow);
      }
      for (int i = 0; i < scaleFactor; i++) { ;
        image.add(tempWRow);
      }
    }
    return image ;
  }

  private List<IPixel> makeRow(IPixel pixel1, IPixel pixel2, int scaleFactor) {

    List<IPixel> row = new ArrayList<>();
    for ( int x = 0 ; x < numTiles/2 ; x ++  ) {
      for (int i = 0; i < scaleFactor; i++) {
        row.add(pixel1);
      }
      for (int i = 0; i < scaleFactor; i++) {
        row.add(pixel2);
      }
    }

    return row;
  }


  @Override
  public List<List<IPixel>> getPixels() {
    return delegate.getPixels();
  }

  @Override
  public IPixel getPixel(int row, int pixelRowIndex) {
    return delegate.getPixel(row, pixelRowIndex);
  }

  @Override
  public void render(String type) {
    delegate.render(type);

  }

  @Override
  public int getNumRows() {
    return delegate.getNumRows();
  }

  @Override
  public int getNumPixelsInRow() {
    return delegate.getNumPixelsInRow();
  }

}
