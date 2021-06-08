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
  public String toString() {
    return   "\n" + r + "\n" + g + "\n" + b  ;
  }



}
