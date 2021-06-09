package cs3500.imageProcessing.model;

public class Sepia implements ITransformation {

  protected final ITransformation abstractDelegate;

  protected final double[][] sepiaMatrix =
         {{.393,  .769, .189},
          {.349,  .686, .168},
          {.272,  .534, .131}};


  public Sepia(IPixelImage oldImage) {
    // this.oldImage = oldImage;
    this.abstractDelegate = new AbstractColorTransformation(oldImage, sepiaMatrix);

  }

  @Override
  public IPixelImage apply() {
    //List<List<IPixel>> pixelRows = this.oldImage.getPixels();
    return this.abstractDelegate.apply();
  }

}



