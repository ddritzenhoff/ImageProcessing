package view;

import cs3500.imageprocessing.model.ImageUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JComponent;
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
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingView  extends JFrame implements ActionListener, IView{

  private final JButton loadFromModelButton;
  private final JButton deleteLayerButton;
  private final JTextField jTextField;
  //  private final JLabel jLabel;

  private final JButton  addLayerButton;
  private final List<IViewListener> viewListners;
  private List<String> layerList;

  private JPanel imagePanel;
  private JScrollPane imageScrollPane;

  //Radio panels for layer selection
  private JPanel radioPanel;
  private JLabel radioDisplay;
  private JRadioButton[] radioButtons = {};


  //check boxes
  private JPanel checkBoxPanel;
  private List<JCheckBox> checkBoxes;

  JPanel line_end_panel;;
  JPanel page_end_panel;

  //dialog boxes
  private JPanel dialogBoxesPanel = new JPanel();


  //Opening files.
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  private JLabel modelFileDisplay;




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
    layerList= new ArrayList<>();

    setTitle("OOD Image Processing");
    setSize( new Dimension(1000,1000));
    setDefaultCloseOperation( EXIT_ON_CLOSE );
    setLayout( new FlowLayout() );
    setLayout(new BorderLayout(10,10));

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
    this.add(dialogBoxesPanel, BorderLayout.PAGE_END);

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Panel"));
    imagePanel.setPreferredSize(new Dimension(500, 600));
    imageScrollPane = new JScrollPane();
    imagePanel.add(imageScrollPane);
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    add(imagePanel, BorderLayout.CENTER);

    deleteLayerButton = new JButton("Delete Layer");
    deleteLayerButton.setActionCommand("delete-layer");
    deleteLayerButton.addActionListener(this);
    add(deleteLayerButton, BorderLayout.PAGE_START);


    // mainPanel = new JPanel();
    //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.viewListners = new ArrayList<>();


     //setLayout(new GridLayout(1,1));


    loadFromModelButton = new JButton("Load model file");
    addLayerButton = new JButton("Add Layer");


    radioPanel = new JPanel();
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


    modelFileDisplay =  new JLabel("");




    page_end_panel = new JPanel(new GridLayout(3,2,10,0));
    page_end_panel.setMaximumSize(new Dimension (200,500));

    page_end_panel.add(jTextField);
    page_end_panel.add(loadFromModelButton);
    page_end_panel.add(addLayerButton);
    page_end_panel.add(fileOpenButton);
    page_end_panel.add(dialogBoxesPanel);

    add(page_end_panel,BorderLayout.PAGE_END);



    this.setVisible(true);
    setFocusable(true);
    requestFocus();

   // updateButton();
    //updateCheckBoxes();


    pack();
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

    line_end_panel.remove(radioPanel);
    radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Layer Choices"));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    radioButtons = new JRadioButton[layerList.size()];

    //buttons groups are used to combine radio buttons. Only one radio
    // button in each group can be selected.
    ButtonGroup rGroup1 = new ButtonGroup();

    for (int i = 0; i < radioButtons.length; i++) {

      radioButtons[i] = new JRadioButton(layerList.get(i));

      radioButtons[i].setActionCommand("current working layer");
      radioButtons[i].addActionListener(this);
      rGroup1.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);

    }
    //radioButtons[0].setSelected(true);
    //radioPanel.add(radioDisplay);
//      radioPanel.validate();
//      radioPanel.repaint();
    line_end_panel.add(radioPanel);
    line_end_panel.validate();
    line_end_panel.repaint();

  }

  public void updateCheckBoxes() {

    line_end_panel.remove(checkBoxPanel);

    checkBoxPanel.removeAll();
    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));

    JCheckBox[] newBoxes;
    JCheckBox[] oldBoxes = new JCheckBox[checkBoxes.size()];

    for(int i = 0 ; i < checkBoxes.size() ; i ++ ) {
      oldBoxes[i] = new JCheckBox(checkBoxes.get(i).getText(),checkBoxes.get(i).isSelected() );
    }
    newBoxes = new JCheckBox[layerList.size()];

    for (int i = 0; i < layerList.size(); i++) {
      newBoxes[i] = new JCheckBox(layerList.get(i));
      newBoxes[i].setSelected(false);
      newBoxes[i].setActionCommand("visibility");
      //checkBoxes[i].addItemListener(this);
      newBoxes[i].addActionListener(this);

      //	group.add(checkBoxes[i]);
      checkBoxPanel.add(newBoxes[i]);
    }

    JLabel checkboxDisplay = new JLabel("Current Layer Selected");
    checkBoxPanel.add(checkboxDisplay);
    checkBoxPanel.invalidate();
    checkBoxPanel.revalidate();
    checkBoxPanel.repaint();
    line_end_panel.add(checkBoxPanel);
    line_end_panel.validate();
    line_end_panel.repaint();
  }


  public void addCheckBox(String layerName) {

//    /line_end_panel.remove(checkBoxPanel);
    JCheckBox newBox =  new JCheckBox(layerName, true);
    newBox.setActionCommand("visibility");
    newBox.addActionListener(this);
    checkBoxes.add(newBox);
    checkBoxPanel.add(newBox);

    checkBoxPanel.invalidate();
    checkBoxPanel.revalidate();
    checkBoxPanel.repaint();

  }

  @Override
  public void setMenu(List<String> s) {
    layerList.clear();
    for(String string : s){
      layerList.add(string);
    }
    //layerList = s.clone();
    System.out.println(s.size());
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
     // listener.loadVisibility();
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
    imagePanel.validate();
    imagePanel.repaint();
    this.validate();
    this.repaint();
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
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "load model .txt file", "txt");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      modelFileDisplay.setText(f.getAbsolutePath());
    }
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
    //updateCheckBoxes();

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
        updateButton();
        //updateFrame();
      //  emitShowTopMostVisibleImageLayerEvent();
       // updateFrame();
        this.validate();
        this.repaint();
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
  public List<Boolean> getVisibility() {
    List<Boolean> arr = new ArrayList<>();
    //JRadioButton j;
    for (int i = 0 ; i < checkBoxes.size() ; i++) {
      arr.add( checkBoxes.get(i).isSelected());
      System.out.print(arr.get(i));
    }

    return arr;

  }

  @Override
  public String getFileDest() {
    return fileOpenDisplay.getText();
  }

  public String getLoadedModelFileDest() {
    return modelFileDisplay.getText();
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

  public void setVisibility(List<Boolean> b) {
    //JRadioButton j;
    System.out.print("LIst: " + b);
    checkBoxes = new ArrayList<>();
    checkBoxPanel.removeAll();

    for (int i = 0 ; i < b.size() ; i++) {
      JCheckBox oldBox = new JCheckBox( layerList.get(i),b.get(i));
//      addCheckBox()
     // checkBoxes = new ArrayList<>(b.size());
     // checkBoxes.add
     // oldBox.setSelected(b.get(i));
      checkBoxes.add(oldBox);
      checkBoxPanel.add(oldBox);
    //  checkBoxes.set(i,  )
   //   checkBoxes[i].setSelected(b.get(i));


      //updateCheckBoxes();
      //checkBoxes[i].doClick();
    }
    checkBoxPanel.revalidate();
    checkBoxPanel.repaint();



  }



}
