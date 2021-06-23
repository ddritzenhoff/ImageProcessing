package view;

public interface IViewListener {
  void handleBlurEvent();
  void handleSepiaEvent();
  void handleGreyscaleEvent();
  void handleSharpenEvent();

  void handleLoadEvent();
  void handleWorkingLayerEvent();
  void handleAddLayerEvent();
  void handleAddImageToLayerEvent();
  void handleVisibilityEvent();

  void showTopMostVisibleImageLayerEvent();
}
