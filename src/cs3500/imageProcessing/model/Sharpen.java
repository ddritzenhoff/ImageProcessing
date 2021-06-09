package cs3500.imageProcessing.model;


// TODO: the abstract class functionality should probably change a little.
//  also, I'm not completely sure that this works.
public class Sharpen implements ITransformation {

  protected final ITransformation abstractDelegate;

  protected final double[][] sharpenKernel =
      {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};

  public Sharpen(IPixelImage oldImage) {
    this.abstractDelegate = new AbstractFilterTransformation(oldImage, sharpenKernel);
  }

  @Override
  public IPixelImage apply(String id) {
    return this.abstractDelegate.apply(id);
  }
}
