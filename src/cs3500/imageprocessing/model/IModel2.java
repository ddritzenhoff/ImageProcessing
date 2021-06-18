package cs3500.imageprocessing.model;

public interface IModel2 {

  public void addLayer(String layerName);

  public void addImageToLayer(String imageFileName);

  public void setWorkingLayer(String layerName);

  public void applyTransformation(ITransformation transformation);

  public void exportLayer(String newFileName);

  public void setVisibility(boolean isVisible);

  public void replaceImage(IPixelImage image);

  public void exportAll(String directoryName);

}