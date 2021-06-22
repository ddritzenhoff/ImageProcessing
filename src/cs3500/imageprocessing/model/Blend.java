package cs3500.imageprocessing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an ILayerTransformation layer operation. This class is specifically responsible for
 * blending two IPixelImage layers together.
 */
public class Blend implements ILayerTransformation {

  @Override
  public IPixelImage apply(ILayer layer1, ILayer layer2) {
    IPixelImage image1 = layer1.getImage();
    IPixelImage image2 = layer1.getImage();

    List<List<IPixel>> mesh = new ArrayList<>();
    for (int i = 0 ; i < image1.getNumRows() ; i++ ) {
      List<IPixel> row = new ArrayList<>();
      for (int j = 0 ; j < image1.getNumPixelsInRow() ; j++) {
        IPixel newPixel = Pixel.blend(image1.getPixel(i,j), image2.getPixel(i,j));
        row.add(newPixel);
      }
      mesh.add(row);
    }
    IPixelImage blendedLayer = new PixelImage(mesh);
    //return new Layer(true,blendedLayer, "");
    return blendedLayer;
  }

  @Override
  public String toString() {
    return "Blend";
  }
}
