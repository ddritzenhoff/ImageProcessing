package cs3500.imageprocessing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * This class represents the model to process different images. This particular implementation lets
 * you add images to 'storage' and transform them using different operations.
 */
public class ProcessingModel implements IModel {

  private final Map<String, ILayer> layers;
  // String inside the map represents the layer name. a image inside a layer has a name.
  private String modelName;


  public ProcessingModel(Map<String, ILayer> layers) {
    this.layers = layers;

  }

  public ProcessingModel(String fileName) {
    this(ImageUtil.readAll(fileName));
  }

  public ProcessingModel(String modelName, Map<String, ILayer> layers) {
    this.layers = layers;
    this.modelName = modelName;
  }

  private ProcessingModel(IModel processingModel){
    this.layers = processingModel.getLayers();
    this.modelName = processingModel.getModelName();
  }

  /**
   * Constructs a IModel object.
   */
  public ProcessingModel() {
    this(new HashMap<>());
  }

  /**
   * this method will do the thing:
   * A multi-layered image can be saved simply as a collection of files:
   * one for each layer (as regular images), and one (text) file that
   * stores the locations of all the layer files.
   */
  public void saveMultiLayerImage() {
    ImageUtil.saveAll(this.modelName,this.layers);
  }

  public void addLayer(String layerName) {
    // TODO: create a dummy pixelImage.
    ILayer layer = new Layer(true, null, layers.size());
    this.layers.putIfAbsent(layerName, layer);
  }


  public void addImageToLayer(String layerName, IPixelImage image) {
    ImageUtil.requireNonNull(layerName, "addImageToLayer filename.");
    ILayer currentLayer = layers.get(layerName);
    layers.replace(layerName,
        new Layer(currentLayer.getVisibility(),
            image, currentLayer.getOrder()));

  }

  public void deleteLayer(String layerName) {
    layers.remove(layerName);
  }

  // applies the transfomration to the layer. does not replace the layer
  public void applyTransformation(ITransformation transform, String layerName) {
    ImageUtil.requireNonNull(transform, "apply transformation transform");
    //checkRegistry(fileName, newFileName);
    ILayer oldLayer = layers.get(layerName);
    IPixelImage newImage = transform.apply(oldLayer.getImage());
    layers.replace(layerName, new Layer(oldLayer.getVisibility(), newImage,
        oldLayer.getOrder()));

  }

  @Override
  public void chainTransformations(List<ITransformation> transforms, String layerName) {
    ImageUtil.requireNonNull(transforms, "list transforms");
    //checkRegistry(fileName, newFileName);
    ILayer oldLayer = layers.get(layerName);
    IPixelImage newImage = new ChainedTransformation(transforms).apply(oldLayer.getImage());
    layers.replace(layerName, new Layer(oldLayer.getVisibility(), newImage,
        oldLayer.getOrder()));

  }


  public Map<String, ILayer> getLayers() {
    return layers;
  }

  public String  getModelName() {
    return modelName;
  }
//  @Override // removes the image from the layer
//  public void removeImage(String fileName) {
//    ImageUtil.requireNonNull(fileName, "remove image");
//    if (!images.containsKey(fileName)) {
//      throw new IllegalArgumentException("invalid filename");
//    }
//    //images.size();
//    images.remove(fileName);
//  }

//  @Override // rep
//  public void replaceImage(String fileName, IPixelImage image) {
//    ImageUtil.requireNonNull(fileName, "replace image fileName");
//    ImageUtil.requireNonNull(image, "replace image IPixelImage");
//    if (!images.containsKey(fileName)) {
//      throw new IllegalArgumentException("invalid filename");
//    }
//    images.replace(fileName, image);
//  }





//  @Override
//  public void generateCheckerboard(int sizeTile, int numSquares, String newFileName) {
//    if (sizeTile < 1 || numSquares < 1) {
//      throw new IllegalArgumentException("invalid parameters to make a checkerboard");
//    }
//
//    if (images.containsKey(newFileName)) {
//      throw new IllegalArgumentException("registry already has a file with this name");
//    }
//
//    IPixelImage newCb = new Checkerboard(sizeTile, numSquares);
//    this.images.putIfAbsent(newFileName,
//        newCb);
//  }

//  // TODO: should not be dealing with fileName
//  @Override
//  public void importPPM(String directoryName, String fileName) {
//    ImageUtil.requireNonNull(fileName, "import ppm filename");
//    ImageUtil.requireNonNull(fileName, "import ppm directoryName");
//    addImage(fileName, ImageUtil.ppmToPixelImage(directoryName));
//  }

//  // TODO: should not be dealing with fileName
//  @Override
//  public void exportPPM(String fileName) {
//    ImageUtil.requireNonNull(fileName, "export ppm filename");
//
//    if (!images.containsKey(fileName)) {
//      throw new IllegalArgumentException("registry does not have this file.");
//    }
//
//    images.get(fileName).render("ppm", fileName);
//  }

//  public String printRegistry() {
//    return images.keySet().toString();
//  }
//
//  // TODO: self eval needs to change this???
//  @Override
//  public IPixelImage getImage(String fileName) {
//    if (!images.containsKey(fileName)) {
//      throw new IllegalArgumentException("registry does not have this file.");
//    }
//    return new PixelImage(images.get(fileName).getPixels());
//  }


//  private void checkRegistry(String fileName, String newFileName) throws IllegalArgumentException {
//    ImageUtil.requireNonNull(fileName, "chain transformation filename");
//    ImageUtil.requireNonNull(newFileName, "newFileName does not exist");
//
//    if (!images.containsKey(fileName)) {
//      throw new IllegalArgumentException("registry does not have this file.");
//    }
//
//    if (images.containsKey(newFileName)) {
//      throw new IllegalArgumentException("registry already has a file with this name");
//    }
//  }

}
