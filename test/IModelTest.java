import static org.junit.Assert.*;

import cs3500.imageProcessing.model.Blur;
import cs3500.imageProcessing.model.Checkerboard;
import cs3500.imageProcessing.model.Greyscale;
import cs3500.imageProcessing.model.IModel;
import cs3500.imageProcessing.model.IPixel;
import cs3500.imageProcessing.model.IPixelImage;
import cs3500.imageProcessing.model.ITransformation;
import cs3500.imageProcessing.model.Pixel;
import cs3500.imageProcessing.model.PixelImage;
import cs3500.imageProcessing.model.ProcessingModel;
import cs3500.imageProcessing.model.Sepia;
import cs3500.imageProcessing.model.Sharpen;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class IModelTest {
  IModel testModel;

  IPixel whitePixel;
  IPixel blackPixel;
  List<List<IPixel>> testPixelArray;
  IPixelImage testPixelImage;
  List<IPixel> pixelList;

  ITransformation sepia = new Sepia();
  ITransformation blur = new Blur();
  ITransformation greyscale = new Greyscale();
  ITransformation sharpen = new Sharpen();

  List<ITransformation> commands = Arrays.asList(blur, blur, sepia);

  @Before
  public void setUp() throws Exception {
    testModel = new ProcessingModel();

    whitePixel = new Pixel(255,255,255);
    blackPixel = new Pixel(0,0,0);
    pixelList  = Arrays.asList(whitePixel,blackPixel,whitePixel);
    testPixelArray = new ArrayList<>();
    testPixelArray.add(pixelList);
    testPixelArray.add(pixelList);
    testPixelArray.add(pixelList);

    testPixelImage = new PixelImage(testPixelArray);

  }

  @Test(expected = IllegalArgumentException.class)
  public void addImageNullFileName() {
    testModel.addImage(null,testPixelImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addImageNullPixelImage() {
    testModel.addImage("bad!",testPixelImage);
    testModel.addImage("test!",null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addImageInvalidIDAreadyExists() {
    testModel.addImage("bad!",testPixelImage);
    testModel.addImage("bad!",new PixelImage(testPixelArray));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addImageValid() {
    testModel.addImage("bad!",testPixelImage);
    testModel.generateCheckerboard(2,2, "testCheckerboard");
    IPixelImage testImage = testModel.getImage("testCheckerboard");

    testModel.addImage("testCheckerboard", testImage);
  }

  @Test
  public void addImageSeveralValid() {
    testModel.addImage("tpi",testPixelImage);
    assertEquals("[tpi]", testModel.printRegistry());
    IPixelImage newCb = new Checkerboard(2,2);
    testModel.addImage("cb1", newCb);

    assertEquals("[cb1, tpi]", testModel.printRegistry());
    testModel.addImage("tpi2",testPixelImage);
    assertEquals("[cb1, tpi2, tpi]", testModel.printRegistry());
    IPixelImage newCb2 = new Checkerboard(2,2);
    testModel.addImage("cb2",newCb2);
    assertEquals("[cb2, cb1, tpi2, tpi]", testModel.printRegistry());

  }

  @Test(expected = IllegalArgumentException.class)
  public void removeImageInvalidFileName() {
    testModel.addImage("tpi",testPixelImage);
    testModel.removeImage("invalidKey.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeImageValidThenInvalidFileName() {
    testModel.addImage("tpi",testPixelImage);
    IPixelImage newCb = new Checkerboard(2,2);
    testModel.addImage("cb1",newCb);
    testModel.removeImage("tpi");
    testModel.removeImage("tpi");
  }


  @Test
  public void addImageSeveralValidRemoveSeveralValid() {
    // Adding images:
    testModel.addImage("tpi",testPixelImage);
    assertEquals("[tpi]", testModel.printRegistry());
    IPixelImage newCb = new Checkerboard(2,2);
    testModel.addImage("cb1",newCb);
    assertEquals("[cb1, tpi]", testModel.printRegistry());
    testModel.addImage("tpi2",testPixelImage);
    assertEquals("[cb1, tpi2, tpi]", testModel.printRegistry());
    IPixelImage newCb2 = new Checkerboard(2,2);
    testModel.addImage("cb2",newCb2);
    assertEquals("[cb2, cb1, tpi2, tpi]", testModel.printRegistry());

    // Removing images:
    testModel.removeImage("tpi");
    assertEquals("[cb2, cb1, tpi2]", testModel.printRegistry());
    testModel.removeImage("cb1");
    assertEquals("[cb2, tpi2]", testModel.printRegistry());

  }

  @Test
  public void replaceImage() {
    IPixelImage checkerboard = new Checkerboard(193,5);
    testModel.addImage("tpi",testPixelImage);
    testModel.replaceImage("tpi",checkerboard);
    assertNotEquals(testPixelImage,testModel.getImage("tpi"));
    assertTrue(testModel.getImage("tpi").equals(checkerboard));

  }

  @Test(expected = IllegalArgumentException.class)
  public void replaceImageInvalidKey() {
    testModel.addImage("tpi",testPixelImage);
    IPixelImage cb1 = new Checkerboard(193,5);
    testModel.replaceImage("tpiINVALID",cb1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void replaceImageInvalidValue() {
    testModel.addImage("tpi",testPixelImage);
    testModel.replaceImage("tpi",null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void alterMapWithAlreadyPresentValuesChainedTransformation() {
    testModel.addImage("tpi",testPixelImage);
    List<ITransformation> commands = Arrays.asList(sepia, blur, sepia, blur,
        sharpen, greyscale, blur, sepia);
   testModel.chainTransformations(commands,"tpi","tpi");
  }

  @Test(expected = IllegalArgumentException.class)
  public void alterMapWithAlreadyPresentValuesSingleTransformation() {
    testModel.addImage("tpi",testPixelImage);
    testModel.applyTransformation(blur, "tpi" , "tpi");
  }

  @Test(expected = IllegalArgumentException.class)
  public void alterMapWithAlreadyPresentValuesCheckerboard() {
    testModel.generateCheckerboard(2,2,"test");
    testModel.generateCheckerboard(2,2,"test");
  }

  @Test
  public void chainTransformations() {
    List<ITransformation> commands = Arrays.asList(sepia, sepia, sepia);
    testModel.addImage("tpi",testPixelImage);
    testModel.chainTransformations(commands, "tpi", "newtpi");
    IPixelImage testChained = testModel.getImage( "newtpi");
    assertEquals(new Pixel(255,255,236), testChained.getPixels().get(0).get(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void chainTransformationsInvalidFileName() {
    List<ITransformation> commands = Arrays.asList(sepia, sepia, sepia);
    testModel.addImage("tpi2",testPixelImage);
    testModel.chainTransformations(commands, "tpi",  "tipi3");

  }

  @Test //TODO: empty list just doesnt modify any pixels.
  public void chainTransformationsEmptyList() {
    List<ITransformation> commands = Arrays.asList();
    testModel.addImage("tpi",testPixelImage);
    testModel.chainTransformations(commands, "tpi", "newtpi");
    IPixelImage testChained = testModel.getImage( "tpi");
    assertEquals(new Pixel(255,255,255), testChained.getPixels().get(0).get(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void chainTransformationsInvalidListElement() {
    List<ITransformation> commands = Arrays.asList(sepia, null, sepia);
    testModel.addImage("tpi2",testPixelImage);
    testModel.chainTransformations(commands, "tpi","tpichained");
    IPixelImage testChained = testModel.getImage("tpichained");
    assertEquals(new Pixel(255,255,238), testChained.getPixels().get(0).get(0));

  }

  @Test
  public void applyTransformation() {
    IPixelImage checkerboard = new Checkerboard(193,5);
    testModel.addImage("tpi2",testPixelImage);
    testModel.addImage("checkerboard",checkerboard);
    testModel.applyTransformation(sepia,"tpi2", "sepiatpi2");
    IPixelImage newImagePostSepiaFilter =  testModel.getImage("sepiatpi2");

    IPixel blackSepiaPixel = newImagePostSepiaFilter.getPixel(1,1);
    assertEquals(blackSepiaPixel, new Pixel(0,0,0));

    IPixel whiteSepiaPixel = newImagePostSepiaFilter.getPixel(0,0);
    assertEquals(new Pixel(255,255,238), whiteSepiaPixel);

  }

  @Test(expected = IllegalArgumentException.class)
  public void generateCheckerboardInvalidName() {
    testModel.generateCheckerboard(100,2, "testCb");
    testModel.generateCheckerboard(100,2, "testCb");
  }

  @Test(expected = IllegalArgumentException.class)
  public void generateCheckerboardInvalidSquares() {
    testModel.generateCheckerboard(100,-1, "test");
  }

  @Test (expected = IllegalArgumentException.class)
  public void generateCheckerboardInvalidSize() {
    testModel.generateCheckerboard(-1,100,"test");
  }

  @Test
  public void generateCheckerboardValid() {
    testModel.generateCheckerboard(100,2, "testCb");
    IPixelImage cb = new Checkerboard(100,2);
    assertEquals("[testCb]",testModel.printRegistry());
    assertTrue(cb.equals(testModel.getImage("testCb")));
  }


  @Test(expected = IllegalArgumentException.class)
  public void importPPMInvalidFile() {
    testModel.importPPM("src/files/koalaINVALID.ppm", "koalaImport!");
    assertEquals("[koalaImport!]", testModel.printRegistry());
    testModel.importPPM("src/files/manhattan-small.ppm", "manhattan!");

  }

  @Test
  public void importPPMValid() {
    testModel.importPPM("src/files/koala.ppm", "koalaImport!");
    assertEquals("[koalaImport!]", testModel.printRegistry());
    testModel.importPPM("src/files/manhattan-small.ppm", "manhattan!");
    assertEquals("[koalaImport!, manhattan!]", testModel.printRegistry());
  }

  @Test(expected = IllegalArgumentException.class)
  public void exportPPMInvalidName() {
    testModel.importPPM("src/files/koala.ppm", "koalaImport!");
    assertEquals("[koalaImport!]", testModel.printRegistry());

    testModel.exportPPM("koalaImport!6");
  }

  @Test
  public void exportPPM() {

//    List<ITransformation> commands = Arrays.asList(sepia, blur, sepia, blur, sharpen, greyscale, blur, sepia);
//
//    testModel.chainTransformations(commands, "koalaImport!",  "Heavily altered koala");
//    testModel.chainTransformations(commands, "manhattan!",  "Heavily altered manhattan");

    testModel.importPPM("src/files/car.ppm", "car");
    testModel.importPPM("src/files/hotairballoon.ppm", "hotairballoon");
//
//    testModel.applyTransformation(blur,"hotairballoon", "blurred hotairballoon");
//    testModel.applyTransformation(sharpen,"hotairballoon", "sharpened hotairballoon");
//    testModel.applyTransformation(sepia,"hotairballoon", "sepia hotairballoon");
//    testModel.applyTransformation(greyscale,"hotairballoon", "greyscale hotairballoon");
//
//    testModel.applyTransformation(blur,"car", "blurred car");
//    testModel.applyTransformation(sharpen,"car", "sharpened car");
//    testModel.applyTransformation(sepia,"car", "sepia car");
//    testModel.applyTransformation(greyscale,"car", "greyscale car");
//
//    testModel.exportPPM("blurred car");
//    testModel.exportPPM("sharpened car");
//    testModel.exportPPM("sepia car");
//    testModel.exportPPM("greyscale car");
//
//    testModel.exportPPM("blurred hotairballoon");
//    testModel.exportPPM("sharpened hotairballoon");
//    testModel.exportPPM("sepia hotairballoon");
//    testModel.exportPPM("greyscale hotairballo//testModel.exportPPM("Heavily altered manhattan");

  }

  @Test
  public void printRegistry() {
    testModel.addImage("tpi",testPixelImage);
    assertEquals("[tpi]", testModel.printRegistry());
    IPixelImage newCb = new Checkerboard(2,2);
    testModel.addImage("cb1", newCb);

    assertEquals("[cb1, tpi]", testModel.printRegistry());
    testModel.addImage("tpi2",testPixelImage);
    assertEquals("[cb1, tpi2, tpi]", testModel.printRegistry());
  }

}