package cs3500.imageprocessing.model;

import java.awt.Color;
import java.util.Objects;

/**
 * represents a singular pixel in a IPixelImage. Has a r, g, and b value to represent the three
 * color channels; red, green and blue.
 */
public class Pixel implements IPixel {

  private int r;
  private int g;
  private int b;

  /**
   * constructor to represent the individual components of a pixel.
   *
   * @param r the red value of a pixel
   * @param g the green value of a pixel
   * @param b the blue value of a pixel
   * @throws IllegalArgumentException if the R, G, or B values are greater than 255, or less than
   *                                  0.
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Illegal pixel");
    }

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
  public void addValues(IPixel tempPixel) {
    ImageUtil.requireNonNull(tempPixel, "null pixel in addValues");
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
    ImageUtil.requireNonNull(matrix, "null apply Matrix matrix");
    if (matrix.length != 3 || !testMatrix(matrix)) {
      throw new IllegalArgumentException("Invalid matrix size");
    }

    int oldR = r;
    int oldG = g;
    int oldB = b;

    int newR = (int) ((oldR * matrix[0][0]) + (oldG * matrix[0][1]) + (oldB * matrix[0][2]));
    int newG = (int) ((oldR * matrix[1][0]) + (oldG * matrix[1][1]) + (oldB * matrix[1][2]));
    int newB = (int) ((oldR * matrix[2][0]) + (oldG * matrix[2][1]) + (oldB * matrix[2][2]));

    r = ImageUtil.pixelClamp(newR);
    g = ImageUtil.pixelClamp(newG);
    b = ImageUtil.pixelClamp(newB);
  }

  @Override
  public String toString() {
    return "\n" + r + "\n" + g + "\n" + b;
  }

  /**
   * tests a matrix to verify it has nxn dimensions.
   *
   * @param matrix the specific matrix
   * @return false if the matrix of n length does not have nxn elements. true otherwise.
   */
  private boolean testMatrix(double[][] matrix) {
    int counter = 0;
    for (double[] d : matrix) {
      counter += d.length;
    }
    int matrixLength = matrix.length * matrix.length;
    return counter == matrixLength;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return getR() == pixel.getR() && this.getG() == pixel.getG() && this.getB() == pixel.getB();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getR(), getG(), getB());
  }

  static IPixel blend(IPixel p1, IPixel p2) {
      Color c0 = new Color( p1.getR(), p1.getG(), p1.getB());
      Color c1 = new Color( p2.getR(), p2.getG(), p2.getB());
      double totalAlpha = c0.getAlpha() + c1.getAlpha();


      double weight0 = .5;
      double weight1 = 1-weight0;

      double r = weight0 * c0.getRed() + weight1 * c1.getRed();
      double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
      double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
      double a = Math.max(c0.getAlpha(), c1.getAlpha()); // test commit

      return new Pixel((int) r, (int) g, (int) b);

  }
}
