package cs3500.imageProcessing.model;

public class Filter implements ITransform{

  protected final IImage oldImage;

  public Filter(IImage oldImage) {
    this.oldImage = oldImage;
  }

  @Override
  public IImage apply(String id) {

    IImage newImage = null;
    // Do stuff
    return newImage;
  }

}
