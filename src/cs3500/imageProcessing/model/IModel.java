package cs3500.imageProcessing.model;

public interface IModel {
  void addImage(String id, IImage image);
  void removeImage(String id, IImage image);
  void replaceImage(String id, IImage image);

  IImage applyTransformation(ITransform transform);
}
