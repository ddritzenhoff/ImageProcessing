package view;

public interface IViewListener {
  void handleBlurEvent();
  void handleSepiaEvent();
  void handleGreyscaleEvent();
  void handleSharpenEvent();


  void handleWorkingLayerEvent();
  void handleAddLayerEvent();
  void handleAddImageToLayerEvent();
  void handleVisibilityEvent();
  void handleDeleteLayerEvent();

  void handleSaveAllEvent();
  void handleLoadAllEvent();
  void loadVisibility();

  void showTopMostVisibleImageLayerEvent();
}
