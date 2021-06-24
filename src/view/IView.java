package view;

import java.awt.image.BufferedImage;
import java.util.List;

public interface IView {
  void registerViewEventListener(IViewListener listener);
  void setText(String text);
  String getText();
  void askForFocus();

  void setMenu(List<String> s);
  String getClickedLayer();
  String getFileDest();

  void setImage(BufferedImage bufferedImage);

  List<Boolean> getVisibility();
  String getSaveAllFilePath();
  String getLoadedModelFileDest();

  void setVisibility(List<Boolean> b);
  void addCheckBox(String layerName);
  void updateButton();

  void updateCheckBoxes();
}
