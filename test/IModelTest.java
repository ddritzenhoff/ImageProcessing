//
//import static org.junit.Assert.assertEquals;
//
//import cs3500.imageprocessing.model.Blur;
//import cs3500.imageprocessing.model.Checkerboard;
//import cs3500.imageprocessing.model.Greyscale;
//import cs3500.imageprocessing.model.IModel;
//import cs3500.imageprocessing.model.IPixel;
//import cs3500.imageprocessing.model.IPixelImage;
//import cs3500.imageprocessing.model.ITransformation;
//import cs3500.imageprocessing.model.ImageUtil;
//import cs3500.imageprocessing.model.PixelImage;
//import cs3500.imageprocessing.model.ProcessingModel;
//import cs3500.imageprocessing.model.Sepia;
//import cs3500.imageprocessing.model.Sharpen;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * Tests for our model implementation.
// * Tests all of the methods in the interface and their effects.
// * Tests invalid inputs and outcomes of IModel manipulations.
// */
//public class IModelTest {
//  IModel testModel;
//
//  IPixel whitePixel;
//  IPixel blackPixel;
//  List<List<IPixel>> testPixelArray;
//  String testPixelImage;
//  IPixelImage testPixelImage2;
//  List<IPixel> pixelList;
//
//  ITransformation sepia = new Sepia();
//  ITransformation blur = new Blur();
//  ITransformation greyscale = new Greyscale();
//  ITransformation sharpen = new Sharpen();
//
//  List<ITransformation> commands = Arrays.asList(blur, blur, sepia);
//
//  @Before
//  public void setUp() throws Exception {
//    testModel = new ProcessingModel();
//
//    whitePixel = new Pixel(255,255,255);
//    blackPixel = new Pixel(0,0,0);
//    pixelList  = Arrays.asList(whitePixel,blackPixel,whitePixel);
//    testPixelArray = new ArrayList<>();
//    testPixelArray.add(pixelList);
//    testPixelArray.add(pixelList);
//    testPixelArray.add(pixelList);
//
//    testPixelImage2 = new PixelImage(testPixelArray);
//    BufferedImage bi = ImageUtil.pixelImageToBufferedImage(testPixelImage2);
//    ImageUtil.saveBufferedImage("testImage", bi,"png");
//    testPixelImage = "res/testImage.png";
//
//  }
//
//  /**
//   * abstracts out the code of adding several images and layers to our testModel.
//   */
//  @Test
//  public void setUpImages() {
//    testModel = new ProcessingModel();
//    testModel.addLayer("testCheckerboard");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//
//
//    testModel.addLayer("jpegcar");
//    testModel.addImageToLayer("res/jpegcar.jpg");
//
//
//    testModel.addLayer("testCheckerboard3");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//
//
//    testModel.addLayer("sepia-car");
//    testModel.addImageToLayer("res/sepia car.ppm");
//    assertEquals(testModel.toString(),
//        "[testCheckerboard, jpegcar,"
//            + " testCheckerboard3, "
//            + "sepia-car]");
//
//  }
//
//
//
//
//  @Test(expected = IllegalArgumentException.class)
//  public void makeLayerNullFileName() {
//    testModel.addLayer("test");
//    testModel.addImageToLayer(null);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void makeLayerNoFileNameExists() {
//    testModel.addLayer("test");
//    testModel.addImageToLayer("blahblah");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void makeLayerAlreadyExistsName() {
//    testModel.addLayer("test");
//    testModel.addLayer("test");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void addImagetoLayerNullFileName() {
//    testModel.addLayer("test");
//    testModel.addImageToLayer(null);
//  }
//
//
//  @Test(expected = IllegalArgumentException.class)
//  public void addImageNullPixelImage() {
//    testModel.addLayer("test");
//    testModel.addImageToLayer(testPixelImage);
//    testModel.addImageToLayer(null);
//
//  }
//
//  @Test
//  public void addImageValid() {
//    testModel.addLayer("testCheckerboard");
//    //testModel.generateCheckerboard(2, 2);
//    ImageUtil.saveBufferedImage("testCheckerboard",
//        ImageUtil.pixelImageToBufferedImage(new Checkerboard(2,2)), "png");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//    assertEquals("[testCheckerboard]", testModel.toString());
//  }
//
//  @Test
//  public void addImageSeveralValid() {
//    testModel.addLayer("testCheckerboard");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//    assertEquals("[testCheckerboard]", testModel.toString());
//
//
//    testModel.addLayer("testCheckerboard2");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//    assertEquals("[testCheckerboard, testCheckerboard2]", testModel.toString());
//
//    testModel.addLayer("testCheckerboard3");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//    assertEquals("[testCheckerboard, testCheckerboard2, testCheckerboard3]",
//        testModel.toString());
//
//    testModel.addLayer("testCheckerboard4");
//    testModel.addImageToLayer("res/testCheckerboard.png");
//    assertEquals("[testCheckerboard, testCheckerboard2, testCheckerboard3, "
//        + "testCheckerboard4]", testModel.toString());
//
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSetWorkingLayerDoesntExist() {
//    testModel.setWorkingLayer("this doesnt exist!");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSetWorkingLayerNull() {
//    testModel.setWorkingLayer(null);
//  }
//
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSetWorkingLayerWasAlreadyDeleted() {
//    testModel.setWorkingLayer(null);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void removeLayerInvalidFileName() {
//    setUpImages();
//    assertEquals("[testCheckerboard, jpegcar, testCheckerboard3, sepia-car]",
//        testModel.toString());
//    testModel.setWorkingLayer("this layer doesnt exist");
//    testModel.deleteLayer();
//
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void removeLayerNoSelectedLayer() {
//    testModel.deleteLayer();
//  }
//
//  @Test
//  public void removeLayerOrderPreserved() {
//    setUpImages();
//    assertEquals("[testCheckerboard, jpegcar, testCheckerboard3, sepia-car]",
//        testModel.toString());
//    testModel.setWorkingLayer("jpegcar");
//    testModel.deleteLayer();
//    assertEquals("[testCheckerboard, testCheckerboard3, sepia-car]",
//        testModel.toString());
//  }
//
//  @Test
//  public void removeLayerOrderPreservedAddNewLayer() {
//    setUpImages();
//    assertEquals("[testCheckerboard, jpegcar, testCheckerboard3, sepia-car]",
//        testModel.toString());
//    testModel.setWorkingLayer("jpegcar");
//    testModel.deleteLayer();
//    assertEquals("[testCheckerboard, testCheckerboard3, sepia-car]",
//        testModel.toString());
//    testModel.addLayer("add jpeg car");
//    testModel.addImageToLayer("res/sepia car.ppm");
//    assertEquals("[testCheckerboard, testCheckerboard3, sepia-car, add jpeg car]",
//        testModel.toString());
//  }
//
//  @Test
//  public void applyTransformation() {
//    IPixelImage checkerboard = new Checkerboard(193,5);
//
//    testModel.addLayer("testPixelImage");
//    testModel.addImageToLayer(testPixelImage);
//    testModel.applyTransformation(sepia);
//    testModel.exportLayer("topMostSepia.png");
//    IPixelImage topMostSepia = ImageUtil.bufferedImageToIPixelImage(
//        ImageUtil.normalImageToBufferedImage("res/topMostSepia.png"));
//    IPixelImage newImagePostSepiaFilter =  topMostSepia;
//
//    IPixel blackSepiaPixel = newImagePostSepiaFilter.getPixel(1,1);
//    assertEquals(blackSepiaPixel, new Pixel(0,0,0));
//
//    IPixel whiteSepiaPixel = newImagePostSepiaFilter.getPixel(0,0);
//    assertEquals(new Pixel(255,255,238), whiteSepiaPixel);
//
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void generateCheckerboardInvalidSquares() {
//    testModel.addLayer("testcheckerboard");
//    testModel.generateCheckerboard(100,-1);
//  }
//
//  @Test (expected = IllegalArgumentException.class)
//  public void generateCheckerboardInvalidSize() {
//    testModel.addLayer("testcheckerboard");
//    testModel.generateCheckerboard(-1,100);
//  }
//
//  @Test
//  public void generateCheckerboardValid() {
//    ProcessingModel testModel2 = new ProcessingModel();
//    testModel2.addLayer("testcheckerboard");
//    testModel2.generateCheckerboard(100,2);
//    IPixelImage cb = new Checkerboard(100,2);
//    assertEquals("[testcheckerboard]",testModel2.toString());
//  }
//
//  @Test
//  public void testVisibility() {
//    setUpImages();
//
//    testModel.addLayer("testPixelImage");
//    testModel.addImageToLayer(testPixelImage);
//    testModel.applyTransformation(sepia);
//    testModel.setVisiblity("testPixelImage", false);
//    testModel.exportLayer("topMostSepia.png");
//    IPixelImage topMostSepia = ImageUtil.bufferedImageToIPixelImage(
//        ImageUtil.normalImageToBufferedImage("res/topMostSepia.png"));
//    IPixelImage newImagePostSepiaFilter =  topMostSepia;
//
//    //this is simply to show that the new top layer is not the layer "testPixelImage"
//
//    IPixel newSepiaPixel = newImagePostSepiaFilter.getPixel(1,1);
//    assertEquals(newSepiaPixel, new Pixel(174,155,120));
//
//  }
//
//
//  @Test
//  public void testToString() {
//    IModel testModel2 = new ProcessingModel();
//    testModel2.addLayer("testCheckerboard");
//    testModel2.addImageToLayer("res/testCheckerboard.png");
//    assertEquals(testModel.toString(),
//        "[testCheckerboard]");
//
//
//    testModel2.addLayer("jpegcar");
//    testModel2.addImageToLayer("res/jpegcar.jpg");
//    assertEquals(testModel.toString(),"[testCheckerboard, jpegcar]");
//
//
//    testModel2.addLayer("testCheckerboard3");
//    testModel2.addImageToLayer("res/testCheckerboard.png");
//    assertEquals(testModel2.toString(),"[testCheckerboard, jpegcar," + " testCheckerboard3]");
//
//    testModel2.addLayer("sepia-car");
//    testModel2.addImageToLayer("res/sepia car.ppm");
//    assertEquals(testModel2.toString(),
//        "[testCheckerboard, jpegcar,"
//            + " testCheckerboard3, "
//            + "sepia-car]");
//
//  }
//
//}