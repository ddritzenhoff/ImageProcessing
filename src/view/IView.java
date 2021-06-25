package view;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Interface to handle all of the gui view events.
 */
public interface IView {

  /**
   * Registers a listener such that it can react when an event occurs.
   * @param listener the function to trigger an event handler when an action occurs.
   */
  void registerViewEventListener(IViewListener listener);

  /**
   * Gets the current working layer name.
   * @return the current working layer name.
   */
  String getText();

  /**
   * Transfers focus from one element to another.
   */
  void askForFocus();

  /**
   * Recreates the menu with specified names.
   * @param s the menu name to be set.
   */
  void setMenu(List<String> s);

  /**
   * Getting the layer that was just clicked.
   * @return the string representation of the layer that was clicked.
   */
  String getClickedLayer();

  /**
   * Gets the file to be loaded into the GUI.
   * @return the string representation of the path to the file.
   */
  String getFileDest();

  /**
   * Sets the image to be loaded into a layer.
   * @param bufferedImage the image to be loaded into the layer.
   */
  void setImage(BufferedImage bufferedImage);

  /**
   * Returns the visibility status of the layers.
   * @return true if the layer is visible and false otherwise. B/c this applies to multiple layers,
   *         a list is returned with each element corresponding to a layer.
   */
  List<Boolean> getVisibility();

  /**
   * Gets the file path from the save all command.
   * @return the string representation of the path to the file which describes the contents of the
   *         project (images and visibility statuses, for example).
   */
  String getSaveAllFilePath();

  /**
   * Gets the file path to the model to be loaded into the GUI.
   * @return the string representation of the path to the file which contains the model to be
   *         loaded into the GUI.
   */
  String getLoadedModelFileDest();

  /**
   * Sets the visibility statuses of all the layers.
   * @param b true if the layer should be visible and false otherwise.
   */
  void setVisibility(List<Boolean> b);

  /**
   * Adds a layer to the GUI.
   * @param layerName the name of the layer to be added.
   */
  void addLayer(String layerName);

  /**
   * Removes a layer from the GUI.
   * @param layerName the name of the layer to be removed.
   */
  void removeLayer(String layerName);

  /**
   * Gets the path to the executable script file.
   * @return the path to the script file which can then be executed.
   */
  String getScript();

  /**
   * writes an error or message to the views' dialog box.
   * @param s error message to be written.
   */
  void writeError(String s);

  /**
   * returns the user inputs box width.
   * @return integer representing a box width.
   */
  int getBoxWidth();

  /**
   * returns the user input of number of boxes.
   * @return integer representing the number of boxes.
   */
  int getNumBoxes();
}
