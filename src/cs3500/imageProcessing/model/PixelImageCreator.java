package cs3500.imageProcessing.model;

public class PixelImageCreator {

  public enum FileType {
    PPM,
  }

  public static IPixelImage create(FileType fileType, String fileName) {
    switch (fileType) {
      case PPM:
        return ImageUtil.PPMtoPixelImage(fileName);
      default:
        throw new IllegalStateException("Unexpected value: " + fileType);
    }
  }
}
