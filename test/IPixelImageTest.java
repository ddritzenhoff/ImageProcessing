
import static org.junit.Assert.assertEquals;

import cs3500.imageprocessing.model.IPixel;
import cs3500.imageprocessing.model.IPixelImage;
import cs3500.imageprocessing.model.Pixel;
import cs3500.imageprocessing.model.PixelImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * tests for the IPixelImage class and its subsequent methods.
 * Creates a mini 3x3 array of Pixels to test operations on.
 * Tests invalid inputs and effects on IPixelImages.
 */
public class IPixelImageTest {
  IPixel whitePixel;
  IPixel blackPixel;
  List<List<IPixel>> testPixelArray;
  IPixelImage testPixelImage;
  List<IPixel> pixelList;



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

  }


  @Test
  public void getPixels() {
    IPixelImage test = new PixelImage(testPixelArray);
    assertEquals(test.getPixels(),testPixelArray);
  }

  @Test
  public void getPixel() {
    assertEquals(blackPixel,testPixelImage.getPixel(1,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelOutOfBoundsRowNegative() {
    assertEquals(blackPixel,testPixelImage.getPixel(-1,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelOutOfBoundsRowPositive() {
    assertEquals(blackPixel,testPixelImage.getPixel(100,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelOutOfBoundsRowIndexNegative() {
    assertEquals(blackPixel,testPixelImage.getPixel(2,-10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelOutOfBoundsRowIndexPositive() {
    assertEquals(blackPixel,testPixelImage.getPixel(2,100));
  }

  @Test
  public void getNumRows() {
    assertEquals(3,testPixelImage.getNumRows());
    testPixelArray.add(pixelList);
    testPixelImage  = new PixelImage(testPixelArray);
    assertEquals(4,testPixelImage.getNumRows());
  }

  @Test
  public void getNumPixelsInRow() {
    assertEquals(3, testPixelImage.getNumPixelsInRow());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getNumPixelsInRowIllegalAdd() {
    assertEquals(3, testPixelImage.getNumPixelsInRow());
    testPixelArray.get(1).add(blackPixel);
    testPixelImage = new PixelImage(testPixelArray);

  }


}
