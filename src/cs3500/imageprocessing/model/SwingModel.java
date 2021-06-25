package cs3500.imageprocessing.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SwingModel extends ProcessingModel implements ISwingModel {

  public SwingModel(){
    super();
  }
  @Override
  public List<String> list() {
    return Layer.orderedList;
  }

  @Override
  public BufferedImage topLayerImage() {
    return ImageUtil.bufferedImageTopMostVisibleImage(layers);
  }

  @Override
  public void loadModel(String fileDirectory) {
    Layer.orderedList.clear();
    this.layers = ImageUtil.readAll(fileDirectory);
    List<String> tempList;
    tempList = new ArrayList<>();

    for (ILayer l : layers.values()) {
      System.out.println(l.loadedOrder());
      for (int i = 0; i < layers.size(); i++) {
        if (l.loadedOrder() == i) {
          System.out.println("layername: " + l.getLayerName() + " " + i);
          tempList.add(l.getLayerName());
        }
      }
    }
    Layer.orderedList.addAll(tempList);
    this.workingLayer = "none";

  }

  @Override
  public void exportDirectory(String fileDirectory) {
    BufferedImage bi = topLayerImage();
    IPixelImage pi = ImageUtil.bufferedImageToIPixelImage(bi);
    ImageUtil.exportDirectory(pi, fileDirectory);
  }

  @Override
  public List<Boolean> getVisibility() {
    List<Boolean> temp = new ArrayList<>();
    for (ILayer l : layers.values()) {
      temp.add(l.getVisibility());
    }

    return temp;

  }

}
