package view;

import java.awt.image.BufferedImage;

public interface IView {
  void registerViewEventListener(IViewListener listener);
  void setText(String text);
  String getText();
  void askForFocus();

  void setMenu(String[] s);
  String getClickedLayer();
  String getFileDest();

  void setImage(BufferedImage bufferedImage);

  Boolean[] getVisibility();
  String getSaveAllFilePath();
}
