package cs3500.imageProcessing.model;

public class Blur implements ITransformation {

//  protected final IPixelImage oldImage;
protected final ITransformation abstractDelegate;

  protected final double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
      {1.0 / 8, 1.0 / 4, 1.0 / 8},
      {1.0 / 16, 1.0 / 8, 1.0 / 16}};

  public Blur(IPixelImage oldImage) {
    this.abstractDelegate = new AbstractFilterTransformation(oldImage, blurKernel);
  }

  @Override
  public IPixelImage apply() {
    return this.abstractDelegate.apply();
  }

}
