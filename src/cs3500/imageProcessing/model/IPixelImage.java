package cs3500.imageProcessing.model;

import java.util.List;

public interface IPixelImage {

  //TODO: JAVADOC
  List<List<IPixel>> getPixels();

  //TODO: JAVADOC
  IPixel getPixel(int row, int pixelRowIndex);

  //TODO: JAVADOC
  void render(String type);

  //TODO: JAVADOC
  int getNumRows();

  //TODO: JAVADOC
  int getNumPixelsInRow();

}
