package cs3500.imageProcessing.model;

/**
 * represents a singular pixel in a IPixelImage.
 * Has a r, g, and b value to represent the three color channels; red, green and blue.
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

  /**
   * getter to return the this pixels r value.
   * @return an integer representing the red value of a pixel.
   */
  public int getR() {
    return this.r;
  }


  /**
   * getter to return the this pixels g value.
   * @return an integer representing the green value of a pixel.
   */
  public int getG() {
    return this.g;
  }

  /**
   * getter to return the this pixels b value.
   * @return an integer representing the blue value of a pixel.
   */
  public int getB() {
    return this.b;
  }

  /**
   * Scales this pixel.
   * Multiplies the scalar value to every channel of this pixel.
   * @param scalar a decimal value.
   */
  public void scaleChannels(double scalar) {
    this.r = (int) (this.r * scalar);
    this.g = (int) (this.g * scalar);
    this.b = (int) (this.b * scalar);
  }


  /**
   *
   * @param tempPixel
   */
  public void addValues(IPixel tempPixel) {
    this.r = this.r + tempPixel.getR();
    this.g = this.g + tempPixel.getG();
    this.b = this.b + tempPixel.getB();
  }

  @Override
  public void clamp() {
    this.r = ImageUtil.pixelClamp(this.r);
    this.g = ImageUtil.pixelClamp(this.g);
    this.b = ImageUtil.pixelClamp(this.b);
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
