package cs3500.imageProcessing.model;

public class Blur implements ITransformation {

  protected final IPixelImage oldImage;

  public Blur(IPixelImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IPixelImage apply(String id) {
    return null;
  }
}
