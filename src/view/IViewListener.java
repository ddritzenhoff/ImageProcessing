package view;

/**
 * Interface to handle all of the listener events.
 */
public interface IViewListener {

  /**
   * Handle the case when a blur event is triggered.
   */
  void handleBlurEvent();

  /**
   * Handle the case when a sepia event is triggered.
   */
  void handleSepiaEvent();

  /**
   * Handle the case when a greyscale event is triggered.
   */
  void handleGreyscaleEvent();

  /**
   * Handle the case when a sharpen event is triggered.
   */
  void handleSharpenEvent();

  /**
   * Handle the case when the working layer event is triggered.
   */
  void handleWorkingLayerEvent();

  /**
   * Handle the case when the add layer event is triggered.
   */
  void handleAddLayerEvent();

  /**
   * Handle the case when the add image to layer event is triggered.
   */
  void handleAddImageToLayerEvent();

  /**
   * Handle the case when the working layer event is triggered.
   */
  void handleVisibilityEvent();

  /**
   * Handle the case when the delete layer event is triggered.
   */
  void handleDeleteLayerEvent();

  /**
   * Handle the case when the save all event is triggered.
   */
  void handleSaveAllEvent();

  /**
   * Handle the case when the load all event is triggered.
   */
  void handleLoadAllEvent();

  /**
   * Handle the case when the export event is triggered.
   */
  void handleExportEvent();

  /**
   * Handle the case when the load script event is triggered.
   */
  void handleLoadScriptEvent();

  /**
   * Handle the case when the load visibility event is triggered.
   */
  void loadVisibility();

  /**
   * Handle the case when the top most visible layer event is triggered.
   */
  void showTopMostVisibleImageLayerEvent();
}
