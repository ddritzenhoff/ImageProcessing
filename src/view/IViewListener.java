package view;

public interface IViewListener {
  void handleBlurEvent(IViewEvent event);
  void handleSepiaEvent();
  void handleLoadEvent(IViewEvent event);
  void handleWorkingLayerEvent(IViewEvent event);
}
