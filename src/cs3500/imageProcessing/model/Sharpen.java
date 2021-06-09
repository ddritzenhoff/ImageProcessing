package cs3500.imageProcessing.model;


// TODO: the abstract class functionality should probably change a little.
//  also, I'm not completely sure that this works.
public class Sharpen extends AbstractTransformation implements ITransformation {

  protected final double[][] sharpenKernel =
      {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};

  protected Sharpen(IPixelImage oldImage) {
    super(oldImage);
  }

  @Override
  public IPixelImage apply(String id) {
    return super.doApply(sharpenKernel);
  }
}
