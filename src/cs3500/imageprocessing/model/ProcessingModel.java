package cs3500.imageprocessing.model;

import static cs3500.imageprocessing.model.ImageUtil.imageWrapperImport;
import static cs3500.imageprocessing.model.ImageUtil.readAll;

import java.util.HashMap;
import java.util.Map;


/**
 * this class represents a specific implementation of a IModel. It centered around having a selected
 * layer, then allowing actions to be performed on that selected layer. these layer names are stored
 * in a hashmap of String,Layer which wraps its respective IPixelImage, order, visibility, and other
 * properties.
 */
public class ProcessingModel implements IModel {

  protected Map<String, ILayer> layers;

  protected String workingLayer;

  /**
   * default constructor of a processing model. Initializes a hash map with no contents. Initializes
   * the working layer to null.
   */
  public ProcessingModel() {
    layers = new HashMap<>();
    workingLayer = null;
    Layer.orderedList.clear();
  }

  /**
   * constructor to read in a ProcessingModel from a fileDirectory. uses the constructor below with
   * the readAll method.
   *
   * @param fileDirectory a string that will point to the file directory which contains a file with
   *                      the information associated with a ProcessingModel such as the layers
   *                      status' and IPixelImages' locations.
   */
  public ProcessingModel(String fileDirectory) {
    this(readAll(fileDirectory));
  }

  /**
   * constructor to create a processing model from the given layers.
   *
   * @param layers will be passed in to this constructor from a ImageUtil method that retrieves the
   *               layers and their respective images from the file system.
   */
  public ProcessingModel(Map<String, ILayer> layers) {
    this.layers = layers;
  }


  @Override
  public void addLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "addLayer null layer name");

    if (layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer already exists");
    }

    // at this point, the layer is unique.

    ILayer layer = new Layer(true, null, layerName);
    Layer.orderedList.add(layerName);
    layers.putIfAbsent(layerName, layer);
    this.setWorkingLayer(layerName);

  }

  @Override
  public void addImageToLayer(String imageFileName) {
    ImageUtil.requireNonNull(imageFileName, "addImageToLayer image file is null");
    ImageUtil.requireNonNull(this.workingLayer,
        "you must create a layer before loading an image into it");
    IPixelImage newImage = imageWrapperImport(imageFileName);

    if (layers.get(workingLayer).getStatus()) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }

    // at this point, you now that the working layer exists and doesn't have an image loaded into
    // it yet.
    ILayer currentLayer = layers.get(this.workingLayer);
    layers.replace(this.workingLayer,
        new Layer(currentLayer.getVisibility(),
            newImage, currentLayer.getLayerName()));

  }

  @Override
  public void setWorkingLayer(String layerName) {

    if (!this.layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer does not exist");
    }

    // at this point, you know that layerName is non-null and is a valid layer.

    this.workingLayer = layerName;
  }

  @Override
  public void deleteLayer() {
    ImageUtil.requireNonNull(workingLayer, "delete layer");
    if (!layers.containsKey(workingLayer)) {
      throw new IllegalArgumentException("layer doesnt exist.");
    }
    Layer.orderedList.remove(workingLayer);
    layers.remove(workingLayer);
  }

  /**
   * legacy method to support generating a checkerboard. will load this checkerboard into the
   * working layer.
   */
  @Override
  public void generateCheckerboard(int sizeTile, int numSquares) {
    if (layers.get(workingLayer).getStatus()) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }
    if (sizeTile < 1 || numSquares < 1) {
      throw new IllegalArgumentException("invalid parameters to make a checkerboard");
    }

    // at this point, you now that the working layer exists and doesn't have an image loaded into
    // it yet.
    ILayer currentLayer = layers.get(this.workingLayer);
    IPixelImage cb = new Checkerboard(sizeTile, numSquares);
    layers.replace(this.workingLayer,
        new Layer(currentLayer.getVisibility(),
            cb, currentLayer.getLayerName()));


  }

  @Override
  public void applyTransformation(ITransformation transformation) {
    ImageUtil.requireNonNull(transformation, "applyTransformation null transformation");
    ImageUtil.requireNonNull(this.workingLayer, "no layer exists to be worked with");
    validLayer(workingLayer);
    if (!this.layers.containsKey(this.workingLayer)) {
      throw new IllegalArgumentException("no image yet loaded into the layer");
    }

    // at this point, you know that the transformation is valid, and an IPixelImage exists
    // to be worked on.

    ILayer oldLayer = this.layers.get(this.workingLayer);
    IPixelImage newImage = transformation.apply(this.layers.get(this.workingLayer).getImage());
    layers.replace(workingLayer, new Layer(oldLayer.getVisibility(), newImage,
        oldLayer.getLayerName()));
  }

  @Override
  public void exportLayer(String newFileName) {
    ImageUtil.requireNonNull(newFileName, "exportLayer null layer name");
    ImageUtil.saveTopMostVisibleImage(newFileName, this.layers);

  }

  @Override
  public void setVisiblity(String layerName, boolean isVisible) {
    ImageUtil.requireNonNull(layerName, "toggleVisibility layer name null");
    if (!this.layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer name does not exist");
    }

    ILayer currentLayer = layers.get(layerName);
    if (currentLayer.getStatus()) {
      ILayer newLayer = new Layer(isVisible,
          currentLayer.getImage(),
          currentLayer.getLayerName());
      layers.replace(layerName, newLayer);
    }
  }

  @Override
  public void exportAll(String directoryName) {
    ImageUtil.saveAll("res/" + directoryName, this.layers);
  }

  /**
   * this method checks the status of the given layer.
   *
   * @param layerName a boolean representing if the layer is populated or not.
   */
  private void validLayer(String layerName) {
    if (!layers.get(layerName).getStatus()) {
      throw new IllegalArgumentException("empty image layer");
    }
  }

  @Override
  public String toString() {
    return Layer.orderedList.toString();
  }

}
