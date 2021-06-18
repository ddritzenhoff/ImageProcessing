package cs3500.imageprocessing.model;

public interface ILayer {
  //String getFileLocation();

  boolean getVisibility();

  IPixelImage getImage();

  int getOrder();

  void setOrder(int order);

  void setVisibility(boolean visibility);

  //void setFileLocation(String fileLocation);

  void setImage(IPixelImage image);

}
