
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.imageProcessing.model.Blur;
import cs3500.imageProcessing.model.Greyscale;
import cs3500.imageProcessing.model.IPixel;
import cs3500.imageProcessing.model.IPixelImage;
import cs3500.imageProcessing.model.ITransformation;
import cs3500.imageProcessing.model.Pixel;
import cs3500.imageProcessing.model.PixelImage;
import cs3500.imageProcessing.model.Sepia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


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

  ITransformation AbstractColorTransform;

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
    //AbstractColorTransform.apply

     blur = new Blur();
     sepia= new Sepia();
     greyscale = new Greyscale();
     sharpen = new Greyscale();


  }

  @Test
  public void testApplyGreyscale() {

    double[][] testMatrix =
        {{.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};

    assertEquals(new Pixel(255,255,255), testPixelImage.getPixel(0,0));
    IPixelImage testGreyscale = greyscale.apply(testPixelImage);

    //get a pixel, and compare a pixel.

    //System.out.println(testBlur);

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

    //System.out.println(testBlur);

    IPixel newPixel = new Pixel(255,255,255);
    newPixel.applyMatrix(testMatrix);
    assertEquals(testSepia.getPixel(0,0),newPixel);
  }






}
