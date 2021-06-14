
import static org.junit.Assert.assertEquals;

import cs3500.imageprocessing.model.Blur;
import cs3500.imageprocessing.model.Greyscale;
import cs3500.imageprocessing.model.IPixel;
import cs3500.imageprocessing.model.IPixelImage;
import cs3500.imageprocessing.model.ITransformation;
import cs3500.imageprocessing.model.Pixel;
import cs3500.imageprocessing.model.PixelImage;
import cs3500.imageprocessing.model.Sepia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 *  Tests for ITransformations. Tests invalid inputs and the effects of ITransformations.
 */
public class ITransformationTest {

  Pixel whitePixel;
  IPixel blackPixel;
  List<List<IPixel>> testPixelArray;
  IPixelImage testPixelImage;
  List<IPixel> pixelList;

  ITransformation blur;
  ITransformation sepia;
  ITransformation greyscale;
  ITransformation sharpen;

  @Before
  public void init() {
    whitePixel = new Pixel(255,255,255);
    blackPixel = new Pixel(0,0,0);
    pixelList  = Arrays.asList(whitePixel,blackPixel,whitePixel);
    testPixelArray = new ArrayList<>();
    testPixelArray.add(pixelList);
    testPixelArray.add(pixelList);
    testPixelArray.add(pixelList);
    testPixelImage = new PixelImage(testPixelArray);

    blur = new Blur();
    sepia = new Sepia();
    greyscale = new Greyscale();
    sharpen = new Greyscale();


  }

  @Test
  public void testApplyGreyscale() {

    double[][] testMatrix =
        {{.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};

    IPixelImage testGreyscale = greyscale.apply(testPixelImage);
    IPixel newPixel = new Pixel(254,254,254);
    newPixel.applyMatrix(testMatrix);
    assertEquals(testGreyscale.getPixel(0,0),newPixel);
  }

  @Test
  public void testApplySepia() {

    double[][] testMatrix =
        {{.393, .769, .189},
            {.349, .686, .168},
            {.272, .534, .131}};

    assertEquals(new Pixel(255,255,255), testPixelImage.getPixel(0,0));
    IPixelImage testSepia = sepia.apply(testPixelImage);

    //get a pixel, and compare a pixel.
    IPixel newPixel = new Pixel(255,255,255);
    newPixel.applyMatrix(testMatrix);
    assertEquals(testSepia.getPixel(0,0),newPixel);
  }

  @Test
  public void testApplyBlur() {

    assertEquals(new Pixel(255,255,255), testPixelImage.getPixel(0,0));
    IPixelImage testBlur = blur.apply(testPixelImage);

    IPixel newPixel = new Pixel(94,94,94);
    IPixel newPixel2 = new Pixel(122,122,122);

    assertEquals(newPixel, testBlur.getPixel(0,0));
    assertEquals(newPixel2, testBlur.getPixel(1,1));

  }

  @Test
  public void testApplySharpen() {

    assertEquals(new Pixel(255,255,255), testPixelImage.getPixel(0,0));
    IPixelImage testSharpen = sharpen.apply(testPixelImage);

    IPixel newPixel = new Pixel(254,254,254);
    IPixel newPixel2 = new Pixel(0,0,0);

    assertEquals(newPixel, testSharpen.getPixel(0,0));
    assertEquals(newPixel2, testSharpen.getPixel(1,1));

  }






}
