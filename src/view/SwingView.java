package view;

import cs3500.imageprocessing.model.ImageUtil;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SwingView  extends JFrame implements ActionListener, IView {
  private final JButton blurButton;
  private final JButton sepiaButton;
  private final JButton loadFromModelButton;
  private final JTextField jTextField;
  private final JLabel jLabel;
  private final JLabel clickableImageLabel;
  private final List<IViewListener> viewListners;
  private String[] layerList;

  /// ta
  private JLabel layerBoxDisplay;
  private JPanel mainPanel;

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
    setSize( new Dimension(1000,1000));

    mainPanel = new JPanel();
    //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    this.viewListners = new ArrayList<>();


    setDefaultCloseOperation( EXIT_ON_CLOSE );

    setLayout( new FlowLayout() );

    blurButton = new JButton("Blur Image");
    sepiaButton = new JButton("Sepia Image");
    loadFromModelButton = new JButton("Load");

    jTextField = new JTextField(30);
    jLabel = new JLabel("Text to display.");
    JMenuItem test = new JMenuItem("layer");
    JLabel tst = new JLabel();
    tst.add(new JMenu().add(test));


    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setBorder(BorderFactory.createTitledBorder("Select Layer"));
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    add(comboboxPanel);

    layerBoxDisplay = new JLabel("Choose the layer you wish to work on.");
    comboboxPanel.add(layerBoxDisplay);
    String[] options = {"Like it", "Love it", "Gotta have it"};
    JComboBox<String> combobox = new JComboBox<String>();
    //the event listener when an option is selected
    combobox.setActionCommand("Size options");
    combobox.addActionListener(this);
    for (int i = 0; i < options.length; i++) {
      combobox.addItem(options[i]);
    }

    comboboxPanel.add(combobox);


    //adding images
    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Before and After Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    add(imagePanel);

    //these will be the IPixelImages converted to bufferedImages.
    String[] images = {"res/jpegcar.jpg", "res/jpegcar.jpg"};
    JLabel[] imageLabel = new JLabel[images.length];
    JScrollPane[] imageScrollPane = new JScrollPane[images.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(new ImageIcon(images[i]));
      imageScrollPane[i].setPreferredSize(new Dimension(400, 600));
      imagePanel.add(imageScrollPane[i]);
    }
/////
    blurButton.setActionCommand("Blur");
    sepiaButton.setActionCommand("Sepia");
    loadFromModelButton.setActionCommand("load");


    //clickableImageLabel = new JLabel(new ImageIcon("res/"));

    clickableImageLabel = new JLabel (new ImageIcon(ImageUtil.pixelImageToBufferedImage(
        ImageUtil.imageWrapperImport("res/car.ppm"))));




    blurButton.addActionListener( this );
    sepiaButton.addActionListener( this );
    loadFromModelButton.addActionListener(this);
//    addKeyListener(this);
//    addMouseListener(this);
   // clickableImageLabel.addMouseListener(this);


    add(tst);
    add(test);
    add(blurButton);
    add(jTextField);
    add(jLabel);
    add(sepiaButton);
    add(loadFromModelButton);
    add(clickableImageLabel);

    this.setVisible(true);
    setFocusable(true);
    requestFocus();

   // pack();
  }

  public void registerViewEventListener(IViewListener listener){
    this.viewListners.add(Objects.requireNonNull(listener));

  }


  public void setText(String text){
    this.jTextField.setText(text);
  }

  public String getText(){
    return this.jTextField.getText();
  }

  public void askForFocus(){
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setMenu(String[] s) {
    layerList = s;

  }

  protected void emitSepiaEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleSepiaEvent();
    }
  }

  protected void emitBlurEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleBlurEvent( new SaveEvent() );
    }
  }

  protected void emitLoadEvent(){
    for ( IViewListener listener : this.viewListners ){
      listener.handleLoadEvent( new SaveEvent() );
    }
  }

  protected void emitWorkingLayerEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleWorkingLayerEvent(new workingLayerEvent());
    }
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
        break;
      case "Sepia":
        emitSepiaEvent();
        break;
      case "load":
        emitLoadEvent();
        break;
      case "working layer":
        emitWorkingLayerEvent();
        break;

    }
    this.askForFocus();


  } //IView

}
