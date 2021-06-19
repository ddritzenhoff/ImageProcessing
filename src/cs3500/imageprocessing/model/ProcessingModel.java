package cs3500.imageprocessing.model;

import static cs3500.imageprocessing.model.ImageUtil.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import cs3500.imageprocessing.model.ImageUtil;


/**
 * This class represents the model to process different images. This particular implementation lets
 * you add images to 'storage' and transform them using different operations.
 */
public class ProcessingModel implements IModel {

  private final Map<String, ILayer> layers = null;
  // String inside the map represents the layer name. a image inside a layer has a name.
  private String modelName;


  //used for loading in a Processing model with a directory name.
  public ProcessingModel(String fileName, String fileDirectory) {
   // this(readAll(fileDirectory + fileName )); // weird bug
    ImageUtil.requireNonNull(fileName, "Processing Model file name" );
  }

  //
  public ProcessingModel(String modelName, Map<String, ILayer> layers) {
    ImageUtil.requireNonNull(modelName, "Processing Model name" );
    ImageUtil.requireNonNull(layers, "Processing Model layers " );
    //this.layers = layers;
    this.modelName = modelName;
  }

  /**
   * Constructs a IModel object.
   */
  public ProcessingModel(String modelName) {
    this.modelName = modelName;
    //this.layers = new HashMap<>();
  }

  private ProcessingModel(IModel processingModel){
    ImageUtil.requireNonNull(processingModel, "Processing Model" );
   // this.layers = processingModel.getLayers();
    this.modelName = processingModel.getModelName();
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

  public void saveTopMostVisible(String name, String type) {
    ImageUtil.requireNonNull(name, "null string name");
    ImageUtil.requireNonNull(type, "null string type");
    if(!layers.containsKey(name)) {
      throw new IllegalArgumentException("layers does not contain " + name);
    }
    ImageUtil.saveTopMostVisibleImage(name, this.layers);
  }

  public void toggle(String layerName) {
    ILayer currentLayer = layers.get(layerName);
    Boolean currentVisibility = currentLayer.getVisibility();
    ILayer newLayer = new Layer(!currentVisibility,
        currentLayer.getImage(),
        currentLayer.getLayerName());
    layers.replace(layerName,newLayer);
  }

  public void addLayer(String layerName) {
    if (layers.containsKey(layerName)) {
      throw new IllegalArgumentException(layerName + "already exists");
    }
    Layer.orderedList.add(layerName);
    ILayer layer = new Layer(true, null, layerName);
    this.layers.putIfAbsent(layerName, layer);
  }

  //TODO: change this to take in a string for the file name
  public void addImageToLayer(String layerName, IPixelImage image) {
    validLayer(layerName);
    ImageUtil.requireNonNull(layerName, "addImageToLayer filename.");
    if (layers.containsKey(layerName)) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }


    ILayer currentLayer = layers.get(layerName);
    layers.replace(layerName,
        new Layer(currentLayer.getVisibility(),
            image, currentLayer.getLayerName()));
  }

  public void deleteLayer(String layerName) {
    ImageUtil.requireNonNull(layerName,"delete layer");

    if (!layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer doesnt exist.");
    }

    Layer.orderedList.remove(layerName);
    layers.remove(layerName);
  }

  public void blendLayers(String layer1, String layer2, String newLayerName) {
    ImageUtil.requireNonNull(layer1,"blend layers " +  layer1);
    ImageUtil.requireNonNull(layer2, "blend layers " + layer2);
    validLayer(layer1);
    validLayer(layer2);
    if (!layers.containsKey(layer1)) {
      throw new IllegalArgumentException(layer1 + "doesnt exist.");
    }

    if (!layers.containsKey(layer2)) {
      throw new IllegalArgumentException(layer2 + "doesnt exist.");
    }

    if (layers.containsKey(newLayerName)) {
      throw new IllegalArgumentException(newLayerName + "already exists");
    }

    ILayerTransformation blend = new Blend();
    IPixelImage newLayer =  blend.apply(layers.get(layer1),layers.get(layer2));
    //    addLayer(newLayerName);
    //    addImageToLayer(newLayerName,newLayer);
    addLayer(newLayerName);
    addImageToLayer(newLayerName, newLayer);
    //  layers.putIfAbsent(newLayerName, newLayer);
  }

  // applies the transfomration to the layer. does not replace the layer
  public void applyTransformation(ITransformation transform, String layerName) {
    ImageUtil.requireNonNull(transform, "apply transformation transform");
    ImageUtil.requireNonNull(layerName, "apply transformation layerName");
    validLayer(layerName);
    if (!layers.containsKey(layerName)) {
      throw new IllegalArgumentException(layerName + "doesnt exist.");
    }

    //checkRegistry(fileName, newFileName);
    ILayer oldLayer = layers.get(layerName);
    IPixelImage newImage = transform.apply(oldLayer.getImage());
    layers.replace(layerName, new Layer(oldLayer.getVisibility(), newImage,
        oldLayer.getLayerName()));
  }


  @Override
  public void chainTransformations(List<ITransformation> transforms, String layerName) {
    ImageUtil.requireNonNull(transforms, "list transforms");
    ImageUtil.requireNonNull(layerName, "chain transformations layername");
    validLayer(layerName);
    if (!layers.containsKey(layerName)) {
      throw new IllegalArgumentException(layerName + "doesnt exist.");
    }

    ILayer oldLayer = layers.get(layerName);
    IPixelImage newImage = new ChainedTransformation(transforms).apply(oldLayer.getImage());
    layers.replace(layerName, new Layer(oldLayer.getVisibility(), newImage,
        oldLayer.getLayerName()));
  }


  public Map<String, ILayer> getLayers() {
    return layers;
  }

  public String  getModelName() {
    return modelName;
  }

  private void validLayer(String layerName) {
    if (layers.get(layerName).getImage() == null) {
      throw new IllegalArgumentException("empty image layer");
    }
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
