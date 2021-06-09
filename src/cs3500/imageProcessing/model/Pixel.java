package cs3500.imageProcessing.model;

/**
 * represents a singular pixel.
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;

  /**
   * constructor to represent the individual components of a pixel.
   * @param r  the red value of a pixel
   * @param g  the green value of a pixel
   * @param b  the blue value of a pixel
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public int getR() {
    return this.r;
  }

  public int getG() {
    return this.g;
  }

  public int getB() {
    return this.b;
  }

  @Override
  public void scaleChannels(double scalar) {
    this.r = (int) (this.r * scalar);
    this.g = (int) (this.g * scalar);
    this.b = (int) (this.b * scalar);
  }

  @Override
  public void scaleRGB(double r, double g, double b) {
    int oldR = this.r;
    int oldG = this.g;
    int oldB = this.b;
    this.r = (int)(oldR * r + oldG * g + oldB * b );
    this.g = (int)(oldR * r + oldG * g + oldB * b );
    this.b = (int)(oldR * r + oldG * g + oldB * b );
  }


  @Override
  public void addValues(IPixel tempPixel) {
    this.r = ImageUtil.pixelClamp(this.r + tempPixel.getR());
    this.g = ImageUtil.pixelClamp(this.g + tempPixel.getG());
    this.b = ImageUtil.pixelClamp(this.b + tempPixel.getB());
  }

  @Override
  public void applyMatrix(double[][] matrix) {
    int oldR = r;
    int oldG = g;
    int oldB = b;

    int newR = (int)((oldR * matrix[0][0]) + (oldG * matrix[0][1]) + (oldB * matrix[0][2])) ;
    int newG = (int)((oldR * matrix[1][0]) + (oldG * matrix[1][1]) + (oldB * matrix[1][2])) ;
    int newB = (int)((oldR * matrix[2][0]) + (oldG * matrix[2][1]) + (oldB * matrix[2][2])) ;

    r = ImageUtil.pixelClamp(newR);
    g = ImageUtil.pixelClamp(newG);
    b = ImageUtil.pixelClamp(newB);


  }

  @Override
  public String toString() {
    return   "\n" + r + "\n" + g + "\n" + b  ;
  }



}
