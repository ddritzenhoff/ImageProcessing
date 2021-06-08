package cs3500.imageProcessing.model;

import java.util.List;

public interface IPixelImage {

  public List<List<IPixel>> getPixels();

  public IPixel getPixel(int row, int pixelRowIndex);

  public void render(String type);

  public int getNumRows();

  public int getNumPixelsInRow();

}
