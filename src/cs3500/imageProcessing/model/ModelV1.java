package cs3500.imageProcessing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ModelV1 implements IModel{
  protected final Map<String, IImage> images;

  public ModelV1() {
    this.images = new HashMap<>();
  }

  @Override
  public void addImage(String id, IImage image) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(image);

    // TODO: QUESTION: stuff here.
    if (images.containsKey(id)) {
      throw new IllegalStateException("")
    }

    images.putIfAbsent(id, image);
  }

  @Override
  public void removeImage(String id, IImage image) {

  }

  @Override
  public void replaceImage(String id, IImage image) {

  }

  @Override
  public IImage applyTransformation(ITransform transform) {
    return null;
  }
}
