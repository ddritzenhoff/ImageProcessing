package cs3500.imageprocessing.model;

import java.awt.image.BufferedImage;

public class Layer implements ILayer {
  private int order;
  private boolean visibility; // status of the visibility of the layer

  //private String fileName; // file name for the specific image in the layer. // this might not be needed anymore.
  //private String fileLocation; // file destination for the specific image in the layer.  // this might not be needed anymore.
  private IPixelImage image; // actual image within the layer


//  public Layer(boolean visibility,
//      IPixelImage image, int order, String fileName, String fileLocation) {
//  //  this.fileName = fileName;
//    this.visibility = visibility;
//    this.image = image;
//    this.order = order;
//  }

  public Layer(boolean visibility,IPixelImage image, int order) {
    //  this.fileName = fileName;
    this.visibility = visibility;
    this.image = image;
    this.order = order;
  }




//  public String getFileName() {
//    return fileName;
//  }

  @Override
//  public String getFileLocation() {
//    return null;
//  }

  public boolean getVisibility() {
    return visibility;
  }



  public IPixelImage getImage() {
    return image;
  }

  public int getOrder() {
    return order;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
        sb.append(order).append(" ").
          append(visibility).append(" ").append("\n");
//        .append(fileName).append(" ").append(fileLocation).append("\n");
    // .append(image.toString()).append(" ");

    return sb.toString();

  }


  public static void main(String[] args) {
    IPixelImage testCar = ImageUtil.ppmToPixelImage("res/sepia car.ppm");
    ImageUtil.pixelImageToTxtFile("testPixelImageToTextFile","res/",
        testCar);

//    IPixelImage back  = ImageUtil.txtFileToPixelImage(
//        "testPixelImageToTextFile.txt", "res/" );
    BufferedImage BI = ImageUtil.pixelImageToBufferedImage(new Greyscale().apply( new Blur().apply(testCar)));
   // BufferedImage BI = ImageUtil.normalImageToBufferedImage("res/jpegcar.jpg");
   // ImageUtil.saveBufferedImage("test saving bufferedimage", BI, "jpeg"); //WORKS
    IPixelImage bufferedImagetoIPixelImage = ImageUtil.bufferedImageToIPixelImage(BI);

    //bufferedImagetoIPixelImage.render("ppm"," bufferedImagetoIPixelImage");

    BufferedImage t2 = ImageUtil.pixelImageToBufferedImage(bufferedImagetoIPixelImage);

    ImageUtil.saveBufferedImage("IPixelImagetoBufferedImage",
        t2, "gif");


    //back.render("ppm","final");

//    testImage2.render("jpg");
//    testImage2.render("ppm");
//    testImage2.render("png");
//    testImage2.render("ppm");
//
//    IPixelImage rect = new Checkerboard(50, 10).returnPixelImage();
//    rect.render(".ppm");
//    rect.render("ppm");
//    // IPixelImage testImage = new PixelImage().generatePPM("src/Koala.ppm");
//    //testImage.render();
//
//    // testImage.render();
//    Blur test = new Blur(testImage2);
//    test.apply("k").render("ppm");
//
//    Greyscale testGreyscale = new Greyscale(rect);
//    testGreyscale.apply("koalaTestGreyscale").render("png");

    // testImage.render();
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }


  public void setImage(IPixelImage image) {
    this.image = image;
  }
}
