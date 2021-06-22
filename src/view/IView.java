package view;

public interface IView {
  void registerViewEventListener(IViewListener listener);
  void setText(String text);
  String getText();
  void askForFocus();

  void setMenu(String[] s);
}
