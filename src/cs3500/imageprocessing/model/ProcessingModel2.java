package cs3500.imageprocessing.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingModel2 implements IModel2 {

  Map<String, IPixelImage> deck;
  Map<String, Boolean> layerVisibility;
  List<String> layers; // a1 (invis) a2 (invis) a3 a4
  String workingLayer; // a2 - invisible

  public ProcessingModel2() {
    deck = new HashMap<>();
    layers = new ArrayList<>();
    workingLayer = null;
  }

  @Override
  public void addLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "addLayer null layer name");

    if (layers.contains(layerName)) {
      throw new IllegalArgumentException("layer already exists");
    }

    // at this point, the layer is unique.

    layers.add(layerName);

    if (layers.size() == 1) {
      this.setWorkingLayer(layerName);
    }
  }

  @Override
  public void addImageToLayer(String imageFileName) {
    ImageUtil.requireNonNull(imageFileName, "addImageToLayer image file is null");
    ImageUtil.requireNonNull(this.workingLayer,
        "you must create a layer before loading an image into it");

    IPixelImage newImage = ImageUtil
        .bufferedImageToIPixelImage(ImageUtil.normalImageToBufferedImage(imageFileName));

    if (deck.containsKey(workingLayer)) {
      throw new IllegalArgumentException("you cannot add two images to a layer.");
    }

    // at this point, you now that the working layer exists and doesn't have an image loaded into
    // it yet.

    this.deck.put(imageFileName, newImage);
  }

  @Override
  public void setWorkingLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "setWorkingLayer null layer name");

    if (!this.layers.contains(layerName)) {
      throw new IllegalArgumentException("layer does not exist");
    }

    // at this point, you know that layerName is non-null and is a valid layer.

    this.workingLayer = layerName;
  }

  @Override
  public void applyTransformation(ITransformation transformation) {
    ImageUtil.requireNonNull(transformation, "applyTransformation null transformation");
    ImageUtil.requireNonNull(this.workingLayer, "no layer exists to be worked with");

    if (!this.deck.containsKey(this.workingLayer)) {
      throw new IllegalArgumentException("no image yet loaded into the layer");
    }

    // at this point, you know that the transformation is valid, and an IPixelImage exists
    // to be worked on.

    IPixelImage newImage = transformation.apply(this.deck.get(this.workingLayer));
    this.replaceImage(newImage);
  }

  @Override
  public void exportLayer(String newFileName) {
    ImageUtil.requireNonNull(newFileName, "exportLayer null layer name");
    ImageUtil.requireNonNull(this.workingLayer, "a layer does not exist to be worked on");

    /*
    - If the layer is invisible, you don't want to export it, even if the image doesn't exist.
     */

    if (!this.deck.containsKey(this.workingLayer)) {
      throw new IllegalArgumentException("layer exists but no image within layer");
    }

    // at this point, you have a pixelimage to be exported.



    ImageUtil.imageWrapperExport(this.deck.get(this.workingLayer), newFileName);
    // TODO: deal with visibility.
  }

  @Override
  public void setVisibility(boolean isVisible) {

  }

  @Override
  public void replaceImage(IPixelImage image) {
    ImageUtil.requireNonNull(image, "replaceImage image is null");
    ImageUtil.requireNonNull(this.workingLayer, "no layer exists to be worked with");

    if (!this.deck.containsKey(this.workingLayer)) {
      throw new IllegalArgumentException("you must first load an image before you can replace it");
    }

    // at this point, you know that image and workinglayer are non-null, and that there exists
    // an IPixelImage to be replaced.

    this.deck.put(this.workingLayer, image);
  }
}
