package cs3500.imageprocessing.model;

public interface ILayerTransformation {

  public IPixelImage apply(ILayer layer1, ILayer layer2);

}
