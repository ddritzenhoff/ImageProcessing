
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.imageProcessing.model.IPixel;
import cs3500.imageProcessing.model.Pixel;
import org.junit.Test;


public class IPixelTest {
  IPixel blackPixel;
  IPixel whitePixel;

  @Before
  public void setUp() throws Exception {
    IPixel blackPixel = new Pixel(0,0,0);
    IPixel whitePixel = new Pixel(0,0,0);
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
    assertTrue(normalPixel.getR() == 200
        && normalPixel.getG() == 130
        && normalPixel.getB() == 1);
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
    assertEquals(whitePixel.get)
  }

  @Test
  public void testNegativeScaleChannelsWhitePixel() {

    whitePixel.scaleChannels(.5);
    assertEquals(whitePixel.get)
  }

  @Test
  public void testScaleChannelsBlackPixel() {

    whitePixel.scaleChannels(.5);
    assertEquals(blackPixel.get)
  }

  @Test
  public void testScaleChannelsNegativeBlackPixel() {

    whitePixel.scaleChannels(-.5);
    assertEquals(blackPixel.get)
  }

  @Test
  public void testScaleChannelsNegativeWhitePixel() {

    whitePixel.scaleChannels(-.5);
    assertEquals(whitePixel.getR())
  }

  @org.junit.Test
  public void testApplyMatrix() {
  }

  @org.junit.Test
  public void testClamp() {
  }

  @org.junit.Test
  public void testAddValues() {
  }
}