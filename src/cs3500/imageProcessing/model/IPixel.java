package cs3500.imageProcessing.model;

/**
 * This represents the most basic version of an image. It contains three different channels, which
 * together can make up all the colors in the human color spectrum.
 */
public interface IPixel {

  /**
   * Gets the RED value of the pixel. This is a value between 0 and 255.
   *
   * @return the integer representation of how much red there is within the pixel.
   */
  int getR();

  /**
   * Gets the GREEN value of the pixel. This is a value between 0 and 255.
   *
   * @return the integer representation of how much green there is within the pixel.
   */
  int getG();

  /**
   * Gets the BLUE value of the pixel. This is a value between 0 and 255.
   *
   * @return the integer representation of how much blue there is within the pixel.
   */
  int getB();

  /**
   * Scales this pixel. Multiplies the scalar value to every channel of this pixel.
   *
   * @param scalar the degree to which the channels will be scaled.
   */
  void scaleChannels(double scalar);

  /**
   * performs a color transformation which modifies the color of a pixel based on its own color.
   *
   * @param matrix represents a linear color transformation in a array of doubles.
   */
  void applyMatrix(double[][] matrix);

  /**
   * The channel values of the pixels are truncated to between 0 and 255.
   */
  void clamp();

  /**
   * adds all of the R, G, and B values of the given tempPixel to this pixel.
   *
   * @param tempPixel the pixel which will be used to add to this pixel.
   */
  void addValues(IPixel tempPixel);


}
