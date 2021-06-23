package cs3500.imageprocessing.model;


import java.awt.image.BufferedImage;
import java.util.List;

/**
 * this interface holds the methods and requirements of our implementation of a model.
 * Our program is centered around having a selected layer, then allowing actions to be performed
 * on that selected layer.
 */
public interface IModel {


  /**
   * creates a new Layer with the given layerName. this layer is empty, and its visibility
   * is set to true.
   *
   * @param layerName string that represents a new layer.
   */
  void addLayer(String layerName);

  /**
   * Adds an image to a layer. Supports .jpg, .png and .gif file extensions.
   * This also supports the backwards compatibility of importing .ppm files.
   *
   * @param imageFileName string that will be used to upload the file.
   */
  void addImageToLayer(String imageFileName);


  /**
   * sets the given LayerName as the workingLayer within the model.
   * this allows further operations to be done on the workingLayer.
   *
   * @param layerName the name that will be set to the workingLayer
   */
  void setWorkingLayer(String layerName);

  /**
   * applies the given transformation to the current workingLayer.
   * Will throw an exception if the working layer is empty.
   *
   * @param transformation ITransformation that will be performed on the IPixelImage within a layer.
   */
  void applyTransformation(ITransformation transformation);

  /**
   * exports the top-most visible layer in the model as the given fileName.
   * This method will throw an exception if no layers are visible.
   * supports legacy exporting such as exporting a .ppm, while also exporting
   * other file formats such as .jpg, and .png.
   *
   * @param newFileName the file name that will be sent to the file system.
   */
  void exportLayer(String newFileName);

  /**
   * sets the visibility of a layer to either visible(true), or invisible(false).
   * will throw an exception if the layer does not exist.
   * layerName does not have be be the workingLayer.
   *
   * @param layerName represents the layer that will
   * @param isVisible boolean to represent the visibility. default layer visibility is true.
   */
  void setVisiblity(String layerName, boolean isVisible);

  /**
   * exports the model as a colletction of files.
   * one txt file will indicate the status of the layers, their respective orders, and names.
   * will also create .txt files for every IPixelImage within a layer,
   * such that a layer can read back in to a new ProcessingModel.
   *
   * @param directoryName name that will be saved as the model.
   */
  void exportAll(String directoryName);

  /**
   * deletes the workingLayer and its contents.
   */
  void deleteLayer();

  /**
   * legacy method to support generating a checkerboard.
   * will load this checkerboard into the working layer.
   *
   * @param sizeTile pixel width of a tile
   * @param numSquares number of squares to create a numSquares x numSquares checkerboard.
   */
  void generateCheckerboard(int sizeTile, int numSquares);

  //temp
  List<String> list();

  BufferedImage layerImage(String layerName);
  BufferedImage topLayerImage();

}
