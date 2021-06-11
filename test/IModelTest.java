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
  public void addImageInvalidID() {
    testModel.addImage("bad!",testPixelImage);
    testModel.addImage("bad!",testModel.generateCheckerboard(2,2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addImageValid() {
    testModel.addImage("bad!",testPixelImage);
    testModel.addImage("bad!",testModel.generateCheckerboard(2,2));
  }

  @Test
  public void addImageSeveralValid() {
    testModel.addImage("tpi",testPixelImage);
    assertEquals("[tpi]", testModel.printRegistry());
    testModel.addImage("cb1",testModel.generateCheckerboard(2,2));
    assertEquals("[cb1, tpi]", testModel.printRegistry());
    testModel.addImage("tpi2",testPixelImage);
    assertEquals("[cb1, tpi2, tpi]", testModel.printRegistry());
    testModel.addImage("cb2",testModel.generateCheckerboard(2,2));
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
    testModel.addImage("cb1",testModel.generateCheckerboard(2,2));
    testModel.removeImage("tpi");
    testModel.removeImage("tpi");
  }


  @Test
  public void addImageSeveralValidRemoveSeveralValid() {
    // Adding images:
    testModel.addImage("tpi",testPixelImage);
    assertEquals("[tpi]", testModel.printRegistry());
    testModel.addImage("cb1",testModel.generateCheckerboard(2,2));
    assertEquals("[cb1, tpi]", testModel.printRegistry());
    testModel.addImage("tpi2",testPixelImage);
    assertEquals("[cb1, tpi2, tpi]", testModel.printRegistry());
    testModel.addImage("cb2",testModel.generateCheckerboard(2,2));
    assertEquals("[cb2, cb1, tpi2, tpi]", testModel.printRegistry());

    // Removing images:
    testModel.removeImage("tpi");
    assertEquals("[cb2, cb1, tpi2]", testModel.printRegistry());
    testModel.removeImage("cb1");
    assertEquals("[cb2, tpi2]", testModel.printRegistry());

  }

  @Test
  public void replaceImage() {
    //TODO: test.
    IPixelImage checkerboard = testModel.generateCheckerboard(193,5);
    testModel.addImage("tpi",testPixelImage);
    testModel.replaceImage("tpi",testModel.generateCheckerboard(193,5));
    assertNotEquals(testPixelImage,testModel.getImage("tpi"));
    assertTrue(testModel.getImage("tpi").equals(checkerboard));

  }

  @Test(expected = IllegalArgumentException.class)
  public void replaceImageInvalidKey() {
    testModel.addImage("tpi",testPixelImage);
    testModel.replaceImage("tpiINVALID",testModel.generateCheckerboard(193,5));

  }

  @Test(expected = IllegalArgumentException.class)
  public void replaceImageInvalidValue() {
    testModel.addImage("tpi",testPixelImage);
    testModel.replaceImage("tpi",null);
  }


  @Test
  public void chainTransformations() {
    List<ITransformation> commands = Arrays.asList(sepia, sepia, sepia);
    testModel.addImage("tpi",testPixelImage);
    IPixelImage testChained = testModel.chainTransformations(commands, "tpi");
    assertEquals(new Pixel(255,255,236), testChained.getPixels().get(0).get(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void chainTransformationsInvalidFileName() {
    List<ITransformation> commands = Arrays.asList(sepia, sepia, sepia);
    testModel.addImage("tpi2",testPixelImage);
    IPixelImage testChained = testModel.chainTransformations(commands, "tpi");

  }

  @Test //TODO: empty list just doesnt modify any pixels.
  public void chainTransformationsEmptyList() {
    List<ITransformation> commands = Arrays.asList();
    testModel.addImage("tpi",testPixelImage);
    IPixelImage testChained = testModel.chainTransformations(commands, "tpi");
    assertEquals(new Pixel(255,255,255), testChained.getPixels().get(0).get(0));

  }

  @Test(expected = IllegalArgumentException.class)
  public void chainTransformationsInvalidListElement() {
    List<ITransformation> commands = Arrays.asList(sepia, null, sepia);
    testModel.addImage("tpi2",testPixelImage);
    IPixelImage testChained = testModel.chainTransformations(commands, "tpi");
    assertEquals(new Pixel(255,255,238), testChained.getPixels().get(0).get(0));

  }

  @Test
  public void applyTransformation() {
    IPixelImage checkerboard = testModel.generateCheckerboard(193,5);
    testModel.addImage("tpi2",testPixelImage);
    testModel.addImage("checkerboard",checkerboard);

    IPixelImage newImagePostSepiaFilter = testModel.applyTransformation(sepia,"tpi2");

    IPixel blackSepiaPixel = newImagePostSepiaFilter.getPixel(1,1);
    assertEquals(blackSepiaPixel, new Pixel(0,0,0));

    IPixel whiteSepiaPixel = newImagePostSepiaFilter.getPixel(0,0);
    assertEquals(new Pixel(255,255,238), whiteSepiaPixel);

  }

  @Test
  public void generateCheckerboard() {
  }

  @Test
  public void importPPM() {
  }

  @Test
  public void exportPPM() {
  }

  @Test
  public void printRegistry() {
  }

  //TODO : tests for other methods i wrote
}