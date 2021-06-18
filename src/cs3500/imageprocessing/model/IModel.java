package cs3500.imageprocessing.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * This interface represents the model of the ImageProcessing library. It allows for the storage and
 * transformation of images.
 */
public interface IModel {

  void addImageToLayer(String layerName, IPixelImage image);

  void saveMultiLayerImage();

  void addLayer(String layerName);

  void deleteLayer(String layerName);
  // void addImage(String id, IPixelImage image);

  /**
   * removes the image with the given ID from the map of images.
   *
   * @param id the image file name to be removed from storage.
   */
  //void removeImage(String id);

  /**
   * replaces the IPixelImage at the given ID with the given image parameter.
   *
   * @param id    string id that corresponds to a IPixelImage
   * @param image a new IPixelIamge that will take the spot of the previous id.
   */
 // void replaceImage(String id, IPixelImage image);


  /**
   * chainTransformations performs a series of ITransformations on an IPixelImage. Currently there
   * are two color transformations: Sepia, and Greyscale. Currently there are two filter
   * transformations: Blur,and Sharpen.
   *
   * @param fileName    this is the image to be operated upon.
   * @param transforms  this is the list of the transforms that will be performed on the IPixelImage
   *                    correlated with the fileName.
   * @param newFileName this is the name of the new file that has been created.
   */
    void chainTransformations(List<ITransformation> transforms, String layerName);
  //void chainTransformations(List<ITransformation> transforms, String fileName, String newFileName);

  /**
   * applyTransformation performs a ITransformation on an IPixelImage. Currently there are two color
   * transformations: Sepia, and Greyscale. Currently there are two filter transformations: Blur,
   * and Sharpen.
   *
   * @param fileName    this is the image to be operated upon.
   * @param transform   this is the transform that will be performed on the IPixelImage correlated
   *                    with the fileName.
   * @param newFileName this is the name of the new file that has been created.
   */
   void applyTransformation(ITransformation transform, String layerName);
 // void applyTransformation(ITransformation transform, String fileName, String newFileName);

  /**
   * generates a checkerboard of the given sizeTile and numSquares dimensions. adds the generated
   * checkerboard to the catalog.
   *
   * @param sizeTile    integer representing the pixel width of a tile.
   * @param numSquares  integer representing the n, in a nxn square board.
   * @param newFileName this is the name of the new file that has been created.
   */
 // void generateCheckerboard(int sizeTile, int numSquares, String newFileName);

  /**
   * imports a PPM and converts it to an IPixelImage using PPMtoPixelImage.
   *
   * @param directoryName the location within the file directory where the file exists.
   * @param fileName      the name of the file to import.
   */
 // void importPPM(String directoryName, String fileName);

  /**
   * exports an IPixelImage as a filetype of the given fileName. Currently supports .jpg, .png, ppm
   * extensions
   *
   * @param fileName the name of the file to export.
   */
 // void exportPPM(String fileName);

  /**
   * creates a string of the keys of the current images in the registry.
   *
   * @return a string of a list of current images in our model.
   */
 // String printRegistry();

  /**
   * returns a copy of the IPixelImage at the given fileName.
   *
   * @param fileName the corresponding file name of the IPixel image
   * @return a copy of the IPixelImage at the given file name.
   */
  //IPixelImage getImage(String fileName);

  String  getModelName();

  Map<String, ILayer> getLayers();
}
