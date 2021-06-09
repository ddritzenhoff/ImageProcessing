package cs3500.imageProcessing.model;

// TODO: document
public interface IModel {
  void addImage(String id, IPixelImage image);
  void removeImage(String id, IPixelImage image);
  void replaceImage(String id, IPixelImage image);

  IPixelImage applyTransformation(ITransformation transform, String fileName);
}
