import cs3500.imageprocessing.model.IModel;
import cs3500.imageprocessing.model.ISwingModel;
import cs3500.imageprocessing.model.ITransformation;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

/**
 * A Mock model used to test whether the parameters are correctly being worked on.
 */
public class MockModel implements IModel {

  final StringBuilder log;

  /**
   * Used to build a MockModel object.
   *
   * @param log field used to check the passed in parameters.
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void addLayer(String layerName) {
    this.log.append(String.format("addLayer: %s", layerName));
  }

  @Override
  public void addImageToLayer(String imageFileName) {
    this.log.append(String.format("addImageToLayer: %s", imageFileName));
  }

  @Override
  public void setWorkingLayer(String layerName) {
    this.log.append(String.format("setWorkingLayer: layerName = %s", layerName));
  }

  @Override
  public void applyTransformation(ITransformation transformation) {
    this.log.append(
        String.format("applyTransformation: transformation = %s", transformation.toString()));
  }

  @Override
  public void exportLayer(String newFileName) {
    this.log.append(String.format("exportLayer: newFileName = %s", newFileName));
  }

  @Override
  public void setVisiblity(String layerName, boolean isVisible) {
    this.log.append(
        String.format("setVisiblity: layerName = %s, isVisible = %b", layerName, isVisible));
  }

  @Override
  public void exportAll(String directoryName) {
    this.log.append(String.format("exportAll: directoryName = %s", directoryName));
  }

  @Override
  public void deleteLayer() {
    this.log.append("deleteLayer");
  }

  @Override
  public void generateCheckerboard(int sizeTile, int numSquares) {
    log.append(String.format("generateCheckerboard: sizeTile = %d, numSquares = %d", sizeTile,
        numSquares));
  }

  @Override
  public List<String> list() {
    return null; // stub
  }
  

  @Override
  public BufferedImage topLayerImage() {
    return null;
  }

  @Override
  public void loadModel(String fileDirectory) {
    log.append("loadModel: ").append(fileDirectory);
  }

  @Override
  public List<Boolean> getVisibility() {
    return null;
  }

  @Override
  public void exportDirectory(String fileDirectory) {
    log.append("exportDirectory").append(fileDirectory);
  }
}
