package cs3500.imageProcessing.model;

/**
 * This class represents a factory class to let you create a PixelImage depending on the type of
 * file (ex. ppm, jpg) you would like to deconstruct.
 */
public class PixelImageCreator {

  // TODO: think about whether this really is the best design.

  /**
   * The type of file options to deconstruct.
   */
  public enum FileType {
    PPM,
  }

  /**
   * Create a PixelImage dpeending on the type of file to deconstruct.
   *
   * @param fileType the type of the file (e.g. .jpg, .ppm).
   * @param fileName the name of the file.
   * @return the IPixelImage representation of the image.
   */
  public static IPixelImage create(FileType fileType, String fileName) {
    switch (fileType) {
      case PPM:
        return ImageUtil.PPMtoPixelImage(fileName);
      default:
        throw new IllegalStateException("Unexpected value: " + fileType);
    }
  }
}
