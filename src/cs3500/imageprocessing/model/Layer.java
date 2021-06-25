package cs3500.imageprocessing.model;

import controller.IProcessingController;
import controller.ProcessingController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * this is a  class to describe a layer.
 * A layer is a wrapper that encapsulates and organizes the data associated with image processing
 *  and IPixelImages. The visibility represents if a layer is visible or not, the image represents
 *  the actual IPixelImage within the layer, the layerName represents the layer name, and
 *  allows the layers to remain ordered with the use of a static orderedList. status boolean of a
 *  layer indicates if a layer has been populated.
 */
public class Layer implements ILayer {

  private boolean visibility; // status of the visibility of the layer
  private IPixelImage image; // actual image within the layer
  static List<String> orderedList = new ArrayList<>();
  private String layerName;
  private boolean status;
  private int order;


  /**
   * constructor for a layer that initializes the visibility, image, and layer name. Initializes
   * the order and status depending on how the layer is initialized.
   *
   * @param visibility if the layer is visible or not.
   * @param image an IPixelImage that will be visible or not.
   * @param layerName string representing the corresponding layer name.
   */
  public Layer(boolean visibility,IPixelImage image, String layerName) {
    //  this.fileName = fileName;
    this.layerName = layerName;
    this.visibility = visibility;
    this.image = image;
    this.order = orderedList.indexOf(layerName);
    this.status = (image != null);
  }

  /**
   * constructor for a layer that initializes the visibility, image, and layer name. Initializes
   * the order and status depending on how the layer is initialized.
   * @param visibility if the layer is visible or not.
   * @param image an IPixelImage that will be visible or not.
   * @param order the order of the layer.
   * @param layerName string representing the corresponding layer name.
   */
  public Layer(boolean visibility,IPixelImage image, int order, String layerName) {
    //  this.fileName = fileName;
    this.layerName = layerName;
    this.visibility = visibility;
    this.image = image;
    this.order = order;
    this.status = (image != null);
  }

  @Override
  public boolean getVisibility() {
    return visibility;
  }



  @Override
  public IPixelImage getImage() {
    if (status) {
      return new PixelImage(image.getPixels());
    } else {
      List<List<IPixel>> tempImage = new ArrayList<>();
      List<IPixel> tempRow = new ArrayList<>();
      IPixel tempPixel = new Pixel(255,255,255);
      tempRow.add(tempPixel);
      tempImage.add(tempRow);
      return new PixelImage(tempImage);

    }

  }

  @Override
  public int getOrder() {
    return orderedList.indexOf(layerName);
    //return order;
  }

  @Override
  public String getLayerName() {
    return this.layerName;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(getOrder()).append(" ").append(visibility).append(" ").append("\n");
    return sb.toString();

  }

  @Override
  public Boolean getStatus() {
    return this.status;
  }

  @Override
  public int loadedOrder() {
    return order;
  }


  /**
   * main method to run our program.
   * @param args null
   */
  public static void main(String[] args) {
    IModel testModel = new ProcessingModel();

    Readable rd = null;

    Readable rdd = new InputStreamReader(System.in);
    IProcessingController processingControllerSystemInput = new ProcessingController(testModel, rdd,
        System.out);

    processingControllerSystemInput.startProcessing();

    try {
      rd = new InputStreamReader(new FileInputStream("res/script1.txt"));
      IProcessingController processingController1 = new ProcessingController(testModel, rd,
          System.out);
      processingController1.startProcessing();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      rd = new InputStreamReader(new FileInputStream("res/script2.txt"));
      IProcessingController processingController2 = new ProcessingController(testModel, rd,
          System.out);
      processingController2.startProcessing();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }


  public void setImage(IPixelImage image) {
    this.image = image;
  }
}
