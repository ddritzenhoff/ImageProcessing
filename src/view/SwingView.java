package view;

import cs3500.imageprocessing.model.ImageUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents the GUI version of the view. The user may interact with buttons to make changes
 * to the project.
 */
public class SwingView  extends JFrame implements ActionListener, IView {

  private final JButton loadFromModelButton;
  private final JButton deleteLayerButton;
  private final JTextField jTextField;
  private String layerName;
  //  private final JLabel jLabel;

  private final JButton  addLayerButton;
  private final List<IViewListener> viewListners;
  private List<String> layerList;

  private JPanel imagePanel;
  private JScrollPane imageScrollPane;

  //Radio panels for layer selection
  private JPanel radioPanel;
  private JLabel radioDisplay;
  private ArrayList<JRadioButton> radioButtons;


  //check boxes
  private JPanel checkBoxPanel;
  private List<JCheckBox> checkBoxes;

  private String script;

  JPanel line_end_panel;;
  JPanel page_end_panel;

  //dialog boxes
  private JPanel dialogBoxesPanel = new JPanel();


  //Opening files.
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel errorDisplay;

  private JLabel modelFileDisplay;
  private  JPanel errorPanel;

  ButtonGroup rGroup1 = new ButtonGroup();





  //TODO:menu: will have all of the layers.
  //TODO: menu will also show the visibility of each layer?
  //TODO: show image of topmost visible layer.
  //TODO: save menu -> look at the given code -> saveas png/ppm/jpg
  //TODO: load txt -> look at the given code "loading and executing a script from a file"
  //TODO: load images -> look at the given code
  //TODO: add layer : layerName text field

  /**
   * Constructs a SwingView object.
   */
  public SwingView() {
    super();
    layerList= new ArrayList<>();

    radioDisplay = new JLabel();
    script = "";

    setTitle("OOD Image Processing");
    setSize( new Dimension(1000,1000));
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    setLayout( new FlowLayout() );
    setLayout(new BorderLayout(10,10));

    // layerList = new String[]{""};

    JMenuBar menuBar = new JMenuBar();

    // creating menu 1
    JMenu file = new JMenu("File");
    JMenuItem m1 = new JMenuItem("Export");
    JMenuItem m2 = new JMenuItem("Save All");
    JMenuItem m3 = new JMenuItem("Open");
    JMenuItem m4 = new JMenuItem("Load Script");

    m1.setActionCommand("export");
    m2.setActionCommand("save-all");
    m3.setActionCommand("load-all");
    m4.setActionCommand("load-script");

    m1.addActionListener(this);
    m2.addActionListener(this);
    m3.addActionListener(this);
    m4.addActionListener(this);

    file.add(m1);
    file.add(m2);
    file.add(m3);
    file.add(m4);

    menuBar.add(file);
    // creating menu 2
    JMenu operation = new JMenu("Transform");
    JMenuItem op1 = new JMenuItem("Blur");
    JMenuItem op2 = new JMenuItem("Sepia");
    JMenuItem op3 = new JMenuItem("Greyscale");
    JMenuItem op4 = new JMenuItem("Sharpen");
    op1.setActionCommand("Blur");
    op2.setActionCommand("Sepia");
    op3.setActionCommand("Greyscale");
    op4.setActionCommand("Sharpen");
    op1.addActionListener(this);
    op2.addActionListener(this);
    op3.addActionListener(this);
    op4.addActionListener(this);
    operation.add(op1);
    operation.add(op2);
    operation.add(op3);
    operation.add(op4);

    menuBar.add(operation);

    this.setJMenuBar(menuBar);



    // creating menu 3
    JMenu layerOperations = new JMenu("Layer");
    JMenuItem lo1 = new JMenuItem("Add Layer");
    lo1.addActionListener(this);
    lo1.setActionCommand("add layer");
    lo1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        layerName = JOptionPane.showInputDialog("Layer Name:");
      }
    });
    JMenuItem lo2 = new JMenuItem("Delete Layer");
    lo2.addActionListener(this);
    lo2.setActionCommand("delete-layer");
    JMenuItem lo3 = new JMenuItem("Add Image to Layer");
    lo3.addActionListener(this);
    lo3.setActionCommand("add image to layer");
    layerOperations.add(lo1);
    layerOperations.add(lo2);
    layerOperations.add(lo3);
    menuBar.add(layerOperations);






    //dialog boxes


    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Console"));
    dialogBoxesPanel.setLayout(new GridLayout(3,1));

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Panel"));
    imagePanel.setPreferredSize(new Dimension(500, 600));
    imageScrollPane = new JScrollPane();
    imagePanel.add(imageScrollPane);
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    add(imagePanel, BorderLayout.CENTER);




    // mainPanel = new JPanel();
    //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.viewListners = new ArrayList<>();


    //setLayout(new GridLayout(1,1));


    loadFromModelButton = new JButton("Load model file");
    addLayerButton = new JButton("Add Layer");


    radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Layer Choices"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    radioButtons = new ArrayList<>();

    checkBoxPanel = new JPanel();
    checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Visibility Checkboxes"));
    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));
    checkBoxes = new ArrayList<>();

    line_end_panel = new JPanel(new GridLayout(3,1,50,10));
    line_end_panel.add(radioPanel);
    line_end_panel.add(checkBoxPanel);
    line_end_panel.setPreferredSize(new Dimension(200,500));

    jTextField = new JTextField(30);


    loadFromModelButton.setActionCommand("load");
    addLayerButton.setActionCommand("add layer");

    loadFromModelButton.addActionListener(this);
    addLayerButton.addActionListener(this);

    this.add(line_end_panel, BorderLayout.LINE_END);

/////////////// opening file
    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    fileOpenDisplay = new JLabel("Image path will appear here");
    fileopenPanel.add(fileOpenDisplay);
    dialogBoxesPanel.add(fileopenPanel);

    ////////////

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveDisplay = new JLabel("File save path will appear here.");
    filesavePanel.add(fileSaveDisplay);


    modelFileDisplay =  new JLabel("");

    deleteLayerButton = new JButton("Delete Layer");
    deleteLayerButton.setActionCommand("delete-layer");
    deleteLayerButton.addActionListener(this);



    page_end_panel = new JPanel(new GridLayout(3,2,10,0));
    page_end_panel.setMaximumSize(new Dimension (200,500));


    errorPanel = new JPanel();
    errorPanel.setLayout(new FlowLayout());
    errorDisplay = new JLabel("Error Panel");
    errorPanel.add(errorDisplay);
    dialogBoxesPanel.add(errorPanel);

    //page_end_panel.add(fileOpenButton);

    add(page_end_panel,BorderLayout.PAGE_END);
    errorDisplay.setText("Running ...");
    add(dialogBoxesPanel, BorderLayout.PAGE_START);
    this.setVisible(true);
    setFocusable(true);
    requestFocus();

    pack();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    switch( e.getActionCommand() ){
      case "Blur":
        emitBlurEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "Sepia":
        emitSepiaEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "Greyscale":
        emitGreyscaleEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "Sharpen":
        emitSharpenEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "visibility":
        emitVisibilityEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "current working layer":
        emitWorkingLayerEvent();
        break;

      case "add layer":
        emitAddLayerEvent();
        break;

      case "add image to layer":
        emitAddImageToLayerEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "delete-layer" :
        emitDeleteLayerEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;

      case "save-all" :
        emitSaveAllEvent();
        break;

      case "export" :
        emitExportEvent();
        break;

      case "load-all":
        emitLoadAllEvent();
        emitShowTopMostVisibleImageLayerEvent();
        this.validate();
        this.repaint();
        break;

      case "load-script":
        emitLoadScriptEvent();
        break;
    }
    this.askForFocus();

  } //IView


  public void registerViewEventListener(IViewListener listener){
    this.viewListners.add(Objects.requireNonNull(listener));

  }

  public void askForFocus(){
    this.setFocusable(true);
    this.requestFocus();
  }



  /*
  EMITTERS
   */

  /**
   * emits to every IViewListener in the list of IViewListeners  to handle a sepia event.
   */
  private void emitSepiaEvent() {
    for ( IViewListener listener : this.viewListners ) {
      listener.handleSepiaEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a blur event.
   */
  private void emitBlurEvent() {
    for ( IViewListener listener : this.viewListners ) {
      listener.handleBlurEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a GreyScale event.
   */
  private void emitGreyscaleEvent() {
    for ( IViewListener listener : this.viewListners ) {
      listener.handleGreyscaleEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners
   * to handle a sharpen event.
   */
  private void emitSharpenEvent() {
    for ( IViewListener listener : this.viewListners ) {
      listener.handleSharpenEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * visibility change event.
   */
  private void emitVisibilityEvent() {
    for ( IViewListener listener : this.viewListners ) {
      listener.handleVisibilityEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * change working layer event.
   */
  private void emitWorkingLayerEvent() {


    for (IViewListener listener : this.viewListners) {
      listener.handleWorkingLayerEvent();
    }
    if (layerList.size() ==0) {
      writeError("no available layers");
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * added layer event.
   */
  private void emitAddLayerEvent() {
    for (IViewListener listener : this.viewListners ) {
      listener.handleAddLayerEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * show the top most visible layer event.
   */
  private void emitShowTopMostVisibleImageLayerEvent() {
    for (IViewListener listener : this.viewListners ) {
      listener.showTopMostVisibleImageLayerEvent();
    }
    imagePanel.validate();
    imagePanel.repaint();
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * delete layer event.
   */
  private void emitDeleteLayerEvent() {
    for (IViewListener listener : this.viewListners ) {
      listener.handleDeleteLayerEvent();
    }
  }


  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * add image to layer event.
   */
  private void emitAddImageToLayerEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, PPM, & GIF Images", "png", "ppm", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
    }
    for (IViewListener listener : this.viewListners ) {
      listener.handleAddImageToLayerEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * load-all event.
   */
  private void emitLoadAllEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "load model .txt file", "txt");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      modelFileDisplay.setText(f.getAbsolutePath());
    }
    for (IViewListener listener : this.viewListners ) {
      listener.handleLoadAllEvent();
    }

  }


  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * save-all event.
   */
  private void emitSaveAllEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "TXT File", "txt");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
    }
    for (IViewListener listener : this.viewListners ){
      listener.handleSaveAllEvent();
    }
  }


  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * load-script event. This runs the script from the designated file location.
   */
  private void emitLoadScriptEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "load script .txt file", "txt");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      script = f.getAbsolutePath();
    }
    for (IViewListener listener : this.viewListners ){
      listener.handleLoadScriptEvent();
    }
  }

  /**
   * emits to every IViewListener in the list of IViewListeners to handle a
   * export event. This runs the script from the designated file location.
   */
  private void emitExportEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, PPM, & GIF Images", "png", "ppm", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileSaveDisplay.setText(f.getAbsolutePath());
    }
    for (IViewListener listener : this.viewListners ){
      listener.handleExportEvent();
    }
  }


  /*
  UTILITY
   */

  /**
   * adds a layer with the given layer name to the views buttons/checkboxes.
   *
   * @param layerName name of the layer
   */
  public void addLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "addLayer view");

    JRadioButton rd = new JRadioButton(layerName);

    rd.setActionCommand("current working layer");
    rd.addActionListener(this);
    radioButtons.add(rd);
    radioPanel.add(rd);
    rGroup1.add(rd);


    radioPanel.revalidate();
    radioPanel.repaint();

    JCheckBox newBox =  new JCheckBox(layerName, true);
    newBox.setActionCommand("visibility");
    newBox.addActionListener(this);
    checkBoxes.add(newBox);
    checkBoxPanel.add(newBox);

    checkBoxPanel.invalidate();
    checkBoxPanel.revalidate();
    checkBoxPanel.repaint();

    //TODO: fix radio buttons for empty list.
    if(radioButtons.size() > 0) {
      radioButtons.get(radioButtons.size()-1).setSelected(true);
    }
  }

  public void removeLayer(String layerName) {
    ImageUtil.requireNonNull(layerName, "removeLayer view" );

    rGroup1.remove(radioButtons.get(layerList.indexOf(layerName)));
    radioButtons.remove(layerList.indexOf(layerName));
    radioPanel.remove(layerList.indexOf(layerName));

    radioPanel.revalidate();
    radioPanel.repaint();

    checkBoxes.remove(layerList.indexOf(layerName));
    checkBoxPanel.remove(layerList.indexOf(layerName));

    checkBoxPanel.validate();
    checkBoxPanel.repaint();
    //TODO: fix radio buttons for empty list.
    if(radioButtons.size() > 0) {
      radioButtons.get(radioButtons.size()-1).setSelected(true);
    }


  }


  /*
  OBSERVERS / RETRIEVERS
   */

  public String getText() {
    return layerName;
  }

  @Override
  public String getScript() {
    return script;
  }

  public void setText(String text){
    this.jTextField.setText(text);
  }


  @Override
  public void setMenu(List<String> s) {
    layerList.clear();
    for(String string : s){
      layerList.add(string);
    }
  }





  public String getClickedLayer() {

    //JRadioButton j;
    for (int i = 0 ; i < radioButtons.size() ; i++) {
      if(radioButtons.get(i).isSelected()) {
        return radioButtons.get(i).getText();
      }
    }
    if(radioButtons.size() > 0) {
      radioButtons.get(radioButtons.size()-1).setSelected(true);
      return layerList.get(layerList.size()-1);
    } else {
      errorDisplay.setText("no layers available to select.");
    }
    return null;
    //  errorDisplay.setText(e.getMessage());

    //TODO: write to console

  }

  //return a map of booleans for every single box.
  public List<Boolean> getVisibility() {
    List<Boolean> arr = new ArrayList<>();
    //JRadioButton j;
    for (int i = 0 ; i < checkBoxes.size() ; i++) {
      arr.add( checkBoxes.get(i).isSelected());
      System.out.print("idk test" + arr.get(i));
    }

    return arr;

  }


  public String getSaveAllFilePath() {
    return fileSaveDisplay.getText();
  }

  @Override
  public String getFileDest() {
    return fileOpenDisplay.getText();
  }

  @Override
  public String getLoadedModelFileDest() {
    return modelFileDisplay.getText();
  }

  @Override
  public void setImage(BufferedImage bufferedImage) {
    ImageUtil.requireNonNull(bufferedImage, "setImage");

    this.imagePanel.removeAll();

    this.imageScrollPane.removeAll();


    JLabel imageLabel = new JLabel(new ImageIcon(bufferedImage));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 600));
    this.imagePanel.add(imageScrollPane);


    this.imagePanel.validate();
    this.imagePanel.repaint();

  }

  @Override
  public void setVisibility(List<Boolean> b) {
    ImageUtil.requireNonNull(b, "setVisibility boolean list");

    checkBoxes = new ArrayList<>();
    checkBoxPanel.removeAll();

    radioButtons = new ArrayList<>();
    radioPanel.removeAll();
    radioDisplay.removeAll();

    for (int i = 0 ; i < b.size() ; i++) {
      JCheckBox oldBox = new JCheckBox( layerList.get(i),b.get(i));
      oldBox.setActionCommand("visibility");
      oldBox.addActionListener(this);
      checkBoxes.add(oldBox);
      checkBoxPanel.add(oldBox);

      JRadioButton oldButton = new JRadioButton(layerList.get(i));
      oldButton.setActionCommand("current working layer");
      oldButton.addActionListener(this);
      radioButtons.add(oldButton);
      radioPanel.add(oldButton);
      rGroup1.add(oldButton);

    }

    radioPanel.revalidate();
    radioPanel.repaint();

    checkBoxPanel.revalidate();
    checkBoxPanel.repaint();

  }

  @Override
  public void writeError(String s) {
    ImageUtil.requireNonNull(s, "writeError");
    errorDisplay.setText(s);
  }




}
