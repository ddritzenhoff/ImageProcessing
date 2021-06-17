package cs3500.imageprocessing.model;

public class Layer implements ILayer {
  String fileName;
  boolean visibility;
  IPixelImage image;


  public Layer(String fileName, boolean visibility,
      IPixelImage image, int order) {
    this.fileName = fileName;
    this.visibility = visibility;
    this.image = image;
  }

  public String getFileName() {
    return fileName;
  }

  public boolean getVisibility() {
    return visibility;
  }

  public IPixelImage getImage() {
    return image;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
        sb.append(fileName).append(" ")
        .append(visibility).append(" ");
    // .append(image.toString()).append(" ");

    return sb.toString();

  }
}
