
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.imageprocessing.model.IPixel;
import cs3500.imageprocessing.model.Pixel;
import org.junit.Before;
import org.junit.Test;

/**
 * tests for the IPixel class. Tests invalid inputs, and operations on pixels.
 */
public class IPixelTest {
  IPixel blackPixel;
  IPixel whitePixel;

  @Before
  public void init() {

    blackPixel = new Pixel(0,0,0);
    whitePixel = new Pixel(255,255,255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionPixelConstructorLargeR() {
    IPixel illegalR = new Pixel(1000, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionPixelConstructorNegativeB() {
    IPixel illegalB = new Pixel(255, 0, -100);
  }

  @Test
  public void testNormalPixelConstructor() {
    IPixel normalPixel = new Pixel(0, 0, 0);
    assertTrue(normalPixel.getR() == 0
        && normalPixel.getG() == 0
        && normalPixel.getB() == 0);

    IPixel normalPixel2 = new Pixel(200, 130, 1);
    assertTrue(normalPixel2.getR() == 200
        && normalPixel2.getG() == 130
        && normalPixel2.getB() == 1);
  }

  @Test
  public void testGetR() {
    assertTrue(blackPixel.getR() == 0);
    assertTrue(whitePixel.getR() == 255);
  }

  @Test
  public void testGetG() {
    assertTrue(blackPixel.getG() == 0);
    assertTrue(whitePixel.getG() == 255);
  }

  @Test
  public void testGetB() {
    assertTrue(blackPixel.getB() == 0);
    assertTrue(whitePixel.getB() == 255);
  }

  @Test
  public void testScaleChannelsWhitePixel() {
    whitePixel.scaleChannels(.5);
    assertEquals(whitePixel.getR(), 255 / 2);
    assertEquals(whitePixel.getG(), 255 / 2);
    assertEquals(whitePixel.getB(), 255 / 2);
  }

  @Test
  public void testNegativeScaleChannelsWhitePixel() {

    whitePixel.scaleChannels(-.5);
    assertEquals( -255 / 2, whitePixel.getR());
    assertEquals( -255 / 2,whitePixel.getG());
    assertEquals(-255 / 2,whitePixel.getB());
  }

  @Test
  public void testScaleChannelsBlackPixel() {

    whitePixel.scaleChannels(.5);
    assertEquals(0,blackPixel.getR());
  }

  @Test
  public void testScaleChannelsNegativeBlackPixel() {
    blackPixel.scaleChannels(-.5);
    assertEquals(0,blackPixel.getR());
    assertEquals(0,blackPixel.getG());
    assertEquals(0,blackPixel.getB());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testApplyMatrixNullMatrix() {
    double[][] testMatrix = null;
    blackPixel.applyMatrix(testMatrix);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyMatrixInvalidSizeMatrix() {
    double[][] testMatrix =
        {{.2126, .7152, 0 , 0 },
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};
    blackPixel.applyMatrix(testMatrix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyMatrixInvalidSizeMatrixTooSmallColumns() {
    double[][] testMatrix =
        {{.2126, .7152 },
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};
    blackPixel.applyMatrix(testMatrix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyMatrixInvalidSizeMatrixTooSmallRows() {
    double[][] testMatrix =
        {{.2126, .7152},
            {.2126, .7152, .0722}};
    blackPixel.applyMatrix(testMatrix);
  }

  @Test
  public void testApplyMatrixBlackPixel() {
    double[][] testMatrix =
        {{.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};

    blackPixel.applyMatrix(testMatrix);
    assertEquals(0,blackPixel.getR());
    assertEquals(0,blackPixel.getG());
    assertEquals(0,blackPixel.getB());

  }

  @Test
  public void testApplyMatrixWhitePixel() {

    double[][] testMatrix =
        {{.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};
    whitePixel.applyMatrix(testMatrix);
    assertEquals(254,whitePixel.getR());
    assertEquals(254,whitePixel.getG());
    assertEquals(254,whitePixel.getB());

  }

  @Test
  public void testApplyMatrixNormalPixel() {

    double[][] testMatrix =
        {{.2126, .7152, .0722},
            {.2126, .7152, .0722},
            {.2126, .7152, .0722}};
    IPixel normalPixel = new Pixel(103, 94, 25);
    normalPixel.applyMatrix(testMatrix);
    assertEquals(90,normalPixel.getR());
    assertEquals(90,normalPixel.getG());
    assertEquals(90,normalPixel.getB());

  }

  @Test
  public void testClampNoEffect() {
    IPixel normalPixel = new Pixel(103, 94, 25);
    normalPixel.clamp();
    assertEquals(103,normalPixel.getR());
    assertEquals(94,normalPixel.getG());
    assertEquals(25,normalPixel.getB());
  }

  @Test
  public void testClampShrinkSomeFields() {
    IPixel normalPixel = new Pixel(205, 20, 205);
    normalPixel.scaleChannels(1.5);
    normalPixel.clamp();
    assertEquals(255,normalPixel.getR());
    assertEquals(30,normalPixel.getG());
    assertEquals(255,normalPixel.getB());
  }

  @Test
  public void testClampNegativeFixSomeFieldsSomeShrink() {
    IPixel normalPixel = new Pixel(100, 80, 130);
    normalPixel.scaleChannels(-1.5);
    normalPixel.clamp();
    assertEquals(0,normalPixel.getR());
    assertEquals(0,normalPixel.getG());
    assertEquals(0,normalPixel.getB());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddValuesNullPixel() {
    IPixel normalPixel = new Pixel(100, 80, 130);
    IPixel addingPixel = null;
    normalPixel.addValues(addingPixel);
  }

  @Test
  public void testAddValuesWithoutClamp() {
    IPixel normalPixel = new Pixel(100, 80, 100);
    IPixel addingPixel = new Pixel(100, 80, 190);
    normalPixel.addValues(addingPixel);
    assertEquals(200,normalPixel.getR());
    assertEquals(160,normalPixel.getG());
    assertEquals(290,normalPixel.getB());

  }



  @Test
  public void testAddValuesNoEffect() {
    IPixel normalPixel = new Pixel(100, 80, 130);
    normalPixel.addValues(blackPixel);
    assertEquals(100,normalPixel.getR());
    assertEquals(80,normalPixel.getG());
    assertEquals(130,normalPixel.getB());
  }

  @Test
  public void testPixelToString() {
    IPixel normalPixel = new Pixel(100, 80, 130);
    assertEquals("\n100\n80\n130" ,normalPixel.toString());
    assertEquals("\n0\n0\n0" ,blackPixel.toString());
  }


}