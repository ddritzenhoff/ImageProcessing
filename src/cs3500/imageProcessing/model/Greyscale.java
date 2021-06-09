package cs3500.imageProcessing.model;

public class Greyscale implements ITransformation {

  //protected final IPixelImage oldImage;
  protected final ITransformation abstractDelegate;


  protected final double[][] greyScaleMatrix =
      {{.2126,  .7152, .0722},
          {.2126,  .7152, .0722},
          {.2126,  .7152, .0722}};


  public Greyscale(IPixelImage oldImage) {

    this.abstractDelegate = new AbstractColorTransformation(oldImage, greyScaleMatrix);

  }

  @Override
  public IPixelImage apply() {
    //List<List<IPixel>> pixelRows = this.oldImage.getPixels();
    return this.abstractDelegate.apply();
  }

}
