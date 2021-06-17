package cs3500.imageprocessing.model;

public class Layer implements ILayer {
  String fileLocation;
  String fileName;
  boolean visibility;
  IPixelImage image;
  int order;


  public Layer(String fileLocation, String fileName, boolean visibility,
      IPixelImage image, int order) {
    this.fileLocation = fileLocation;
    this.fileName = fileName;
    this.visibility = visibility;
    this.image = image;
    this.order = order;
  }

  public String getFileLocation() {
    return fileLocation;
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

  public int getOrder() {
    return order;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(fileLocation).append(" ")
        .append(fileName).append(" ")
        .append(order).append(" ")
        .append(visibility).append(" ");
    // .append(image.toString()).append(" ");

    return sb.toString();

  }
}
