package cs3500.imageProcessing.model;

public interface IModel {
  void addImage(String id, IPixelImage image);
  void removeImage(String id, IPixelImage image);
  void replaceImage(String id, IPixelImage image);

  IPixelImage applyTransformation(ITransform transform);
}
