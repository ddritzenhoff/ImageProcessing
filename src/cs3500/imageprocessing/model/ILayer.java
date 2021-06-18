package cs3500.imageprocessing.model;

public interface ILayer {

  boolean getVisibility();

  IPixelImage getImage();

  int getOrder();

  void setImage(IPixelImage image);

  String getLayerName();

}
