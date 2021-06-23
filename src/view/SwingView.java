package view;

import cs3500.imageprocessing.model.ImageUtil;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingView  extends JFrame implements ActionListener, IView {

  private final JButton loadFromModelButton;
  private final JButton deleteLayerButton;
  private final JTextField jTextField;
  //  private final JLabel jLabel;
  private JLabel clickableImageLabel;
  private final JButton  addLayerButton;
  private final List<IViewListener> viewListners;
  private String[] layerList = {};

  private JPanel imagePanel;
  private JScrollPane imageScrollPane;

  //Radio panels for layer selection
  private JPanel radioPanel;
  private JLabel radioDisplay;
  private JRadioButton[] radioButtons = {};


  //check boxes
  private JPanel checkBoxPanel;
  private JCheckBox[] checkBoxes;


  //dialog boxes
  private JPanel dialogBoxesPanel = new JPanel();


  //Opening files.
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;




  /// ta
  private JLabel layerBoxDisplay;
  //private JPanel mainPanel;

  //TODO:menu: will have all of the layers.
  //TODO: menu will also show the visibility of each layer?
  //TODO: show image of topmost visible layer.
  //TODO: save menu -> look at the given code -> saveas png/ppm/jpg
  //TODO: load txt -> look at the given code "loading and executing a script from a file"
  //TODO: load images -> look at the given code
  //TODO: add layer : layerName text field
  public SwingView() {
    super();

    setTitle("OOD Image Processing");
    setSize( new Dimension(600,1000));
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    setLayout( new FlowLayout() );

    // layerList = new String[]{""};

    JMenuBar menuBar = new JMenuBar();

// creating menu 1
    JMenu file = new JMenu("File");
    JMenuItem m1 = new JMenuItem("Save");
    JMenuItem m2 = new JMenuItem("Save All");
    JMenuItem m3 = new JMenuItem("Export");
    JMenuItem m4 = new JMenuItem("Open");
    m1.setActionCommand("save-top");
    m3.setActionCommand("export");
     //these are the same^ ?

    m2.setActionCommand("save-all");
    m4.setActionCommand("load-all");

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


    //dialog boxes


    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    this.add(dialogBoxesPanel);

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Before and After Image"));
    imagePanel.setPreferredSize(new Dimension(500, 600));
    imageScrollPane = new JScrollPane();
    imagePanel.add(imageScrollPane);
    add(imagePanel);

    deleteLayerButton = new JButton("Delete Layer");
    deleteLayerButton.setActionCommand("delete-layer");
    deleteLayerButton.addActionListener(this);
    add(deleteLayerButton);


    // mainPanel = new JPanel();
    //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.viewListners = new ArrayList<>();


     //setLayout(new GridLayout(1,1));


    loadFromModelButton = new JButton("Load model file");
    addLayerButton = new JButton("Add Layer");

    radioPanel = new JPanel();

    checkBoxPanel = new JPanel();

    jTextField = new JTextField(30);


    loadFromModelButton.setActionCommand("load");
    addLayerButton.setActionCommand("add layer");

    loadFromModelButton.addActionListener(this);
    addLayerButton.addActionListener(this);



/////////////// opening file
    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Add Image to Layer");
    fileOpenButton.setActionCommand("add image to layer");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    ////////////

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("save-all");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("");
    filesavePanel.add(fileSaveDisplay);



//
//    //adding images
//    //show an image with a scrollbar
//    imagePanel = new JPanel();
//    //a border around the panel with a caption
     imagePanel.setBorder(BorderFactory.createTitledBorder("Before and After Image"));
//    imagePanel.setLayout( new FlowLayout());
//    //imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
//    this.add(imagePanel);
//
//    imageScrollPane = new JScrollPane();
//    imageScrollPane.setPreferredSize(new Dimension(500, 600));
//    imageScrollPane.setVisible(true);
//    clickableImageLabel = new JLabel();
//    imageScrollPane.add(clickableImageLabel);
//    clickableImageLabel.setIcon( new ImageIcon(ImageUtil.pixelImageToBufferedImage(
//    ImageUtil.imageWrapperImport("res/car.ppm"))));
//
//    //his.add(imageScrollPane);
//  //  imageScrollPane.add(clickableImageLabel);
//    imagePanel.add(imageScrollPane);
//    //imagePanel.setMaximumSize(null);
//    add(imagePanel);

    //these will be the IPixelImages converted to bufferedImages.
//    String[] images = {"res/jpegcar.jpg", "res/jpegcar.jpg"};
//    JLabel[] imageLabel = new JLabel[images.length];
//    JScrollPane[] imageScrollPane = new JScrollPane[images.length];
//
////    for (int i = 0; i < imageLabel.length; i++) {
////      imageLabel[i] = new JLabel();
////      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
////      imageLabel[i].setIcon(new ImageIcon(images[i]));
////      imageScrollPane[i].setPreferredSize(new Dimension(500, 600));
////      imagePanel.add(imageScrollPane[i]);
////    }

//    JPanel imagePanel2 = new JPanel();
//    //a border around the panel with a caption
//    imagePanel2.setBorder(BorderFactory.createTitledBorder("Before and After Image"));
//    imagePanel2.setLayout(new GridLayout(1, 0, 10, 10));
//    //imagePanel.setMaximumSize(null);
//    add(imagePanel2);

/////


    //test.setActionCommand("working layer");


    //clickableImageLabel = new JLabel(new ImageIcon("res/"));
    clickableImageLabel = new JLabel();
//    clickableImageLabel = new JLabel (new ImageIcon(ImageUtil.pixelImageToBufferedImage(
//        ImageUtil.imageWrapperImport("res/car.ppm"))));




//    addKeyListener(this);
//    addMouseListener(this);
    // clickableImageLabel.addMouseListener(this);
//


    add(jTextField);

    add(loadFromModelButton);
    add(addLayerButton);
    add(clickableImageLabel);

    add(fileOpenButton);


    this.setVisible(true);
    setFocusable(true);
    requestFocus();

    updateButton();
    updateCheckBoxes();


    //pack();
  }

  public void registerViewEventListener(IViewListener listener){
    this.viewListners.add(Objects.requireNonNull(listener));

  }


  public void setText(String text){
    this.jTextField.setText(text);
  }

  public String getText() {
    return this.jTextField.getText();
  }

  public void askForFocus(){
    this.setFocusable(true);
    this.requestFocus();
  }

  public void updateButton() {
    //radio buttons
    this.remove(radioPanel);
    radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Layer Choices"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    radioButtons = new JRadioButton[layerList.length];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();

    for (int i = 0; i < radioButtons.length; i++) {

      radioButtons[i] = new JRadioButton(layerList[i]);

      radioButtons[i].setActionCommand("current working layer");
      radioButtons[i].addActionListener(this);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);

    }
    //radioButtons[0].setSelected(true);
    //radioPanel.add(radioDisplay);
//      radioPanel.validate();
//      radioPanel.repaint();


    this.add(radioPanel);
    this.validate();
    this.repaint();

  }

  void updateCheckBoxes() {

    this.remove(checkBoxPanel);

    checkBoxPanel = new JPanel();
    checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Visibility Checkboxes"));

    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));

    checkBoxes = new JCheckBox[layerList.length];
    ButtonGroup group = new ButtonGroup();
    for (int i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i] = new JCheckBox(layerList[i]);
      checkBoxes[i].setSelected(true);
      checkBoxes[i].setActionCommand("visibility");
      //checkBoxes[i].addItemListener(this);
      checkBoxes[i].addActionListener(this);

      //	group.add(checkBoxes[i]);
      checkBoxPanel.add(checkBoxes[i]);
    }

    JLabel checkboxDisplay = new JLabel("Which one did the user touch?");
    checkBoxPanel.add(checkboxDisplay);
//    checkBoxPanel.invalidate();
//    checkBoxPanel.revalidate();
//    checkBoxPanel.repaint();
    this.add(checkBoxPanel);
    this.validate();
    this.repaint();
  }

  @Override
  public void setMenu(String[] s) {
    layerList = s.clone();
    System.out.println(s.length);
  }

  protected void emitSepiaEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleSepiaEvent();
    }
  }

  protected void emitBlurEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleBlurEvent();
    }
  }

  protected void emitGreyscaleEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleGreyscaleEvent();
    }
  }

  protected void emitSharpenEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleSharpenEvent();
    }
  }

  protected void emitVisibilityEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleVisibilityEvent();
    }
  }

  protected void emitWorkingLayerEvent() {

    for (IViewListener listener : this.viewListners) {
      listener.handleWorkingLayerEvent();
    }

  }

  protected void emitAddLayerEvent() {
    //radioDisplay.invalidate();
    //read in the text form the text field, and make that the layer name.
    //check for validity
    for (IViewListener listener : this.viewListners ){
      listener.handleAddLayerEvent();
    }

  }
  protected void emitShowTopMostVisibleImageLayerEvent(){
    for (IViewListener listener : this.viewListners ){
      listener.showTopMostVisibleImageLayerEvent();
    }
//    this.validate();
//    this.repaint();
  }

  protected void emitDeleteLayerEvent() {
    for (IViewListener listener : this.viewListners ){
      listener.handleDeleteLayerEvent();
    }
  }

  protected void emitAddImageToLayerEvent() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, PPM, & GIF Images", "png", "ppm", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
    }
    for (IViewListener listener : this.viewListners ){
      listener.handleAddImageToLayerEvent();
    }

  }

  protected void emitLoadAllEvent() {
    for (IViewListener listener : this.viewListners ){
      listener.handleLoadAllEvent();
    }
  }

  protected void emitSaveAllEvent() {
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

  public String getSaveAllFilePath() {
    return fileSaveDisplay.getText();

  }

  protected void updateJLabel() {


  }

  protected void updateFrame() {
    updateButton();
    updateCheckBoxes();

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
        //emitShowTopMostVisibleImageLayerEvent();
        break;
      case "add layer":
        emitAddLayerEvent();
        updateFrame();
        break;
      case "add image to layer":
        emitAddImageToLayerEvent();
        emitShowTopMostVisibleImageLayerEvent();
        break;
      case "delete-layer" :
        emitDeleteLayerEvent();
        emitShowTopMostVisibleImageLayerEvent();
        updateFrame();
        break;
      case "save-all" :
        emitSaveAllEvent();
        break;
      case "load-all":
        emitLoadAllEvent();
        break;



    }
    this.askForFocus();

  } //IView

  public String getClickedLayer() {
    ;
    //JRadioButton j;
    for (int i = 0 ; i < radioButtons.length ; i++) {
      if(radioButtons[i].isSelected()) {
        return radioButtons[i].getText();
      }
    }
    return null;

  }

  //return a map of booleans for every single box.
  public Boolean[] getVisibility() {
    Boolean[] arr = new Boolean[checkBoxes.length];
    //JRadioButton j;
    for (int i = 0 ; i < checkBoxes.length ; i++) {
      arr[i] = checkBoxes[i].isSelected();
    }
    return arr;

  }

  @Override
  public String getFileDest() {
    return fileOpenDisplay.getText();
  }

  public void openFile() {


  }
  //TODO: fix to work with visibility.

  public void setImage(BufferedImage bufferedImage) {

    this.imagePanel.removeAll();

    imageScrollPane.removeAll();


    JLabel imageLabel = new JLabel(new ImageIcon(bufferedImage));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 600));
    imagePanel.add(imageScrollPane);


    imagePanel.validate();
    imagePanel.repaint();

  }

  public void setVisibility() {


  }


}
