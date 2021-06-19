package cs3500.imageprocessing.model;

import static cs3500.imageprocessing.model.ImageUtil.imageWrapperImport;
import static cs3500.imageprocessing.model.ImageUtil.readAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingModel2 implements IModel2 {

  private Map<String, ILayer> layers;

  private String workingLayer;

  /**
   * default constructor of a processing model. Initializes a hash map with no contents.
   * Initializes the working layer to null.
   */
  public ProcessingModel2() {
    layers = new HashMap<>();
    workingLayer = null;
  }

  /**
   * constructor to read in a ProcessingModel from a fileDirectory.
   * uses the constructor below with the readAll method.
   *
   * @param fileDirectory
   */
  public ProcessingModel2(String fileDirectory) {
    this(readAll(fileDirectory));
  }

  /**
   * constructor to create a processing model from the given layers.
   *
   * @param layers will be passed in to this constructor from a ImageUtil method that
   *               retrieves the layers and their respective images from the file system.
   */
  public ProcessingModel2(Map<String, ILayer> layers) {
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
    //todo: make this work with .ppm
    IPixelImage newImage = imageWrapperImport(imageFileName);
//    IPixelImage newImage = ImageUtil
//        .bufferedImageToIPixelImage(ImageUtil.normalImageToBufferedImage(imageFileName));

    if (layers.get(this.workingLayer).getImage() != null) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }

    // at this point, you now that the working layer exists and doesn't have an image loaded into
    // it yet.
    ILayer currentLayer = layers.get(this.workingLayer);
    layers.replace(this.workingLayer,
        new Layer(currentLayer.getVisibility(),
            newImage, currentLayer.getLayerName()));

    //this.layers.put(imageFileName, newImage);
  }

  @Override
  public void setWorkingLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "setWorkingLayer null layer name");

    if (!this.layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer does not exist");
    }

    // at this point, you know that layerName is non-null and is a valid layer.

    this.workingLayer = layerName;
  }

  public void deleteLayer() {
    ImageUtil.requireNonNull(workingLayer,"delete layer");
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
    if (layers.get(this.workingLayer).getImage() != null) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }
    if (sizeTile < 1 || numSquares < 1) {
      throw new IllegalArgumentException("invalid parameters to make a checkerboard");
    }

    // at this point, you now that the working layer exists and doesn't have an image loaded into
    // it yet.
    ILayer currentLayer = layers.get(this.workingLayer);
    IPixelImage cb = new Checkerboard(sizeTile,numSquares);
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

  @Override //TODO: add type file
  public void exportLayer(String newFileName) {
    ImageUtil.requireNonNull(newFileName, "exportLayer null layer name");
    validLayer(workingLayer);
    ImageUtil.saveTopMostVisibleImage(newFileName,this.layers);

  }

  @Override
  public void setVisiblity(String layerName, boolean isVisible) {
    ImageUtil.requireNonNull(layerName, "toggleVisibility layer name null");
    if (!this.layers.containsKey(layerName)) {
      throw new IllegalArgumentException("layer name does not exist");
    }

    ILayer currentLayer = layers.get(layerName);
    ILayer newLayer = new Layer(isVisible,
        currentLayer.getImage(),
        currentLayer.getLayerName());
    layers.replace(layerName,newLayer);
  }

  @Override
  public void exportAll(String directoryName) {
    ImageUtil.saveAll(directoryName,this.layers);
  }

  private void validLayer(String layerName) {
    if (layers.get(layerName).getImage() == null) {
      throw new IllegalArgumentException("empty image layer");
    }
  }

}
