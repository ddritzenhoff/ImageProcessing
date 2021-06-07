package cs3500.imageProcessing.model;

public class Filter implements ITransform{

  protected final IPixelImage oldImage;

  public Filter(IPixelImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IPixelImage apply(String id) {

    IPixelImage newImage = null;
    // Do stuff
    return newImage;
  }

}
