package cs3500.imageProcessing.model;

import java.util.List;

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
  public IPixelImage apply(String id) {
    //List<List<IPixel>> pixelRows = this.oldImage.getPixels();
    return this.abstractDelegate.apply(id);
  }

}



