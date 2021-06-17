package cs3500.imageprocessing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the model to process different images. This particular implementation lets
 * you add images to 'storage' and transform them using different operations.
 */
public class ProcessingModel implements IModel {

  private final Map<String, ILayer> layers;
  private String currentLayer;


  public ProcessingModel(Map<String, ILayer> layers) {
    this.layers = layers;
  }

  /**
   * Constructs a IModel object.
   */
  public ProcessingModel() {
    this(new HashMap<>());
  }


  //TODO: Create a layer.
  //TODO: be able to select a layer: i.e (current first) ;
  //TODO: load a image into a layer. image can be of any kind.
  //TODO: saving stuff -- (method will be called saveMultiLayerImage)

  //TODO: visibility. my idea is to have a hashmap of <String,boolean> that will have true or
  // false for visibility. visibility is dependent on when we are RENDERING, and because of this,
  // visibility is something that will be saved in the contents of the file(a txt file to describe our file),
  // we should be able to access it as a field of our processingModel


  /**
   * this method will do the thing:
   * A multi-layered image can be saved simply as a collection of files:
   * one for each layer (as regular images), and one (text) file that
   * stores the locations of all the layer files.
   */
  public void saveMultiLayerImage() {
    for(Map.Entry<String, IPixelImage> image : images.entrySet()) {

    }

  }

  @Override
  public void addLayer(String fileName) {

    if (layers.keySet().size() == 0) {
      currentLayer = fileName;
    }

    // TODO: create a dummy pixelImage.

    ILayer layer = new Layer(fileName, true, new PixelImage(new ArrayList<>()));
    this.layers.putIfAbsent(fileName, layer);

  }

  @Override
  public void addImageToLayer(String fileName, ILayer layer) {
    ImageUtil.requireNonNull(fileName, "addImageToLayer filename.");
    ImageUtil.requireNonNull(layer, "addImageToLayer layer");

    if (layers.containsKey(fileName)) {
      throw new IllegalArgumentException("registry already has a file of this name.");
    }

    layers.putIfAbsent(fileName, layer);
  }

  @Override
  public void removeImage(String fileName) {
    ImageUtil.requireNonNull(fileName, "remove image");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("invalid filename");
    }
    images.size();
    images.remove(fileName);
  }

  @Override
  public void replaceImage(String fileName, IPixelImage image) {
    ImageUtil.requireNonNull(fileName, "replace image fileName");
    ImageUtil.requireNonNull(image, "replace image IPixelImage");
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("invalid filename");
    }
    images.replace(fileName, image);
  }

  @Override
  public void chainTransformations(List<ITransformation> transforms, String fileName,
      String newFileName) {
    ImageUtil.requireNonNull(transforms, "list transforms");
    checkRegistry(fileName, newFileName);

    IPixelImage newImage = new ChainedTransformation(transforms).apply(images.get(fileName));

    images.putIfAbsent(newFileName, newImage);
  }

  @Override
  public void applyTransformation(ITransformation transform, String fileName, String newFileName) {
    ImageUtil.requireNonNull(transform, "apply transformation transform");
    checkRegistry(fileName, newFileName);

    this.images.putIfAbsent(newFileName, transform.apply(images.get(fileName)));
  }

  @Override
  public void generateCheckerboard(int sizeTile, int numSquares, String newFileName) {
    if (sizeTile < 1 || numSquares < 1) {
      throw new IllegalArgumentException("invalid parameters to make a checkerboard");
    }

    if (images.containsKey(newFileName)) {
      throw new IllegalArgumentException("registry already has a file with this name");
    }

    IPixelImage newCb = new Checkerboard(sizeTile, numSquares);
    this.images.putIfAbsent(newFileName,
        newCb);
  }

  @Override
  public void importPPM(String directoryName, String fileName) {
    ImageUtil.requireNonNull(fileName, "import ppm filename");
    ImageUtil.requireNonNull(fileName, "import ppm directoryName");
    addImage(fileName, ImageUtil.ppmToPixelImage(directoryName));
  }

  @Override
  public void exportPPM(String fileName) {
    ImageUtil.requireNonNull(fileName, "export ppm filename");

    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }

    images.get(fileName).render("ppm", fileName);
  }

  public String printRegistry() {
    return images.keySet().toString();
  }

  @Override
  public IPixelImage getImage(String fileName) {
    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }
    return new PixelImage(images.get(fileName).getPixels());
  }


  private void checkRegistry(String fileName, String newFileName) throws IllegalArgumentException {
    ImageUtil.requireNonNull(fileName, "chain transformation filename");
    ImageUtil.requireNonNull(newFileName, "newFileName does not exist");

    if (!images.containsKey(fileName)) {
      throw new IllegalArgumentException("registry does not have this file.");
    }

    if (images.containsKey(newFileName)) {
      throw new IllegalArgumentException("registry already has a file with this name");
    }
  }

}
