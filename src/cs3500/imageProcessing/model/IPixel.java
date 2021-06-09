package cs3500.imageProcessing.model;

/**
 * This represents the most basic version of an image. It contains three different channels,
 * which together can make up all the colors in the human color spectrum.
 */
public interface IPixel {

  /**
   * Gets the RED value of the pixel. This is a value between 0 and 255.
   * @return the integer representation of how much red there is within the pixel.
   */
  int getR();

  /**
   * Gets the GREEN value of the pixel. This is a value between 0 and 255.
   * @return the integer representation of how much green there is within the pixel.
   */
  int getG();

  //TODO: JAVADOC -- Dominik working on this.
  int getB();

  //TODO: JAVADOC
  void scaleChannels(double scalar);

  //TODO: JAVADOC
  void applyMatrix(double[][] matrix);

  //TODO: JAVADOC
  void clamp();

  //TODO: JAVADOC
  void addValues(IPixel tempPixel);


}
