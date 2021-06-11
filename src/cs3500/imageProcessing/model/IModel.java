package cs3500.imageProcessing.model;

import java.util.List;

// TODO: document
public interface IModel {

  void addImage(String id, IPixelImage image);

  /**
   * removes the image with the given ID from the map of images.
   * @param id
   */
  void removeImage(String id);

  /**
   * replaces the IPixelImage at the given ID with the given image parameter.
   * @param id string id that corresponds to a IPixelImage
   * @param image a new IPixelIamge that will take the spot of the previous id.
   */
  void replaceImage(String id, IPixelImage image);


  /**
   * chainTransformations performs a series of ITransformations on an IPixelImage.
   * Currently there are two color transformations: Sepia, and Greyscale.
   * Currently there are two filter transformations: Blur,and Sharpen.
   *
   * @param fileName this is the image to be operated upon.
   * @param transforms this is the list of the transforms that will be performed
   *                 on the IPixelImage correlated with the fileName.
   * @return a new IPixelImage with the appropriate transformations applied to it.
   */
  IPixelImage chainTransformations(List<ITransformation> transforms, String fileName);

  /**
   * applyTransformation performs a ITransformation on an IPixelImage. Currently there are two color
   * transformations: Sepia, and Greyscale. Currently there are two filter transformations: Blur,
   * and Sharpen.
   *
   * @param fileName this is the image to be operated upon.
   * @param transform this is the transform that will be performed
   *                 on the IPixelImage correlated with the fileName.
   * @return a new IPixelImage with the appropriate transformation applied to it.
   */
  IPixelImage applyTransformation(ITransformation transform, String fileName);

  /**
   * generates a checkerboard of the given sizeTile and numSquares dimensions.
   * @param sizeTile integer representing the pixel width of a tile.
   * @param numSquares integer representing the n, in a nxn square board.
   * @return returns a IPixelImage of a Checkerboard.
   */
  IPixelImage generateCheckerboard(int sizeTile, int numSquares);

  /**
   * imports a PPM and converts it to a IPixelImage using PPMtoPixelImage.
   * @param fileName
   */
  void importPPM(String directoryName,String fileName);

  /**
   * exports an IPixelImage as a filetype of the given fileName.
   * Currently supports .jpg, .png, ppm extensions
   * @param fileName
   */
  void exportPPM(String fileName);

  /**
   * creates a string of the keys of the current images in the registry.
   * @return a string of a list of current images in our model.
   */
  String printRegistry();

  //TODO: do you wanna keep this? I think its pretty useful,
  // but im currently only using it for testing.
  // It might be useful in the the controller tho.
  /**
   * returns a copy of the IPixelImage at the given fileName.
   * @param fileName the corresponding file name of the IPixel image
   * @return a copy of the IPixelImage at the given file name.
   */
  IPixelImage getImage(String fileName);
}
