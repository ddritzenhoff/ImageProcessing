package cs3500.imageprocessing.model;

import static cs3500.imageprocessing.model.ImageUtil.readAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingModel2 implements IModel2 {

  private final Map<String, ILayer> layers;

  String workingLayer;


  public ProcessingModel2() {
    layers = new HashMap<>();
    workingLayer = null;
  }

  public ProcessingModel2(String fileDirectory) {
    this(readAll(fileDirectory));
  }

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
    Layer.orderedList.add(layerName); //TODO: figure this out
    layers.putIfAbsent(layerName, layer);
    this.setWorkingLayer(layerName);

  }

  @Override
  public void addImageToLayer(String imageFileName) {
    ImageUtil.requireNonNull(imageFileName, "addImageToLayer image file is null");
    ImageUtil.requireNonNull(this.workingLayer,
        "you must create a layer before loading an image into it");

    IPixelImage newImage = ImageUtil
        .bufferedImageToIPixelImage(ImageUtil.normalImageToBufferedImage(imageFileName));

    if (layers.containsKey(imageFileName)) {
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

  @Override
  public void applyTransformation(ITransformation transformation) {
    ImageUtil.requireNonNull(transformation, "applyTransformation null transformation");
    ImageUtil.requireNonNull(this.workingLayer, "no layer exists to be worked with");

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
    //ImageUtil.requireNonNull(this.workingLayer, "a layer does not exist to be worked on");
    ImageUtil.requireNonNull(layers.get(workingLayer).getImage(), "null working layer");
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

}
