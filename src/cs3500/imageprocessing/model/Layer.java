package cs3500.imageprocessing.model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

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

//    IPixelImage sepiaB = ImageUtil.ppmToPixelImage("res/sepia car.ppm");
//    IPixelImage sharpB = ImageUtil.ppmToPixelImage("res/sharpened car.ppm");
    IPixelImage sharpB =  new ChainedTransformation(
        Arrays.asList(new Sepia())).apply(
        ImageUtil.bufferedImageToIPixelImage(
            ImageUtil.normalImageToBufferedImage("res/jpegcar.jpg")));
    IPixelImage sepiaB = new ChainedTransformation(
        Arrays.asList(new Blur(), new Blur(), new Blur()
        , new Blur(), new Blur())).apply(
            ImageUtil.bufferedImageToIPixelImage(
                ImageUtil.normalImageToBufferedImage("res/jpegcar.jpg")));
    ;
//    ImageUtil.pixelImageToTxtFile("testPixelImageToTextFile","res/",
//        testCar);

//    IPixelImage back  = ImageUtil.txtFileToPixelImage(
//        "testPixelImageToTextFile.txt", "res/" );
    BufferedImage BI = ImageUtil.pixelImageToBufferedImage(new Greyscale().apply( new Blur().apply(testCar)));
   // BufferedImage BI = ImageUtil.normalImageToBufferedImage("res/jpegcar.jpg");
   // ImageUtil.saveBufferedImage("test saving bufferedimage", BI, "jpeg"); //WORKS
    IPixelImage bufferedImagetoIPixelImage = ImageUtil.bufferedImageToIPixelImage(BI);

    //bufferedImagetoIPixelImage.render("ppm"," bufferedImagetoIPixelImage");

   // BufferedImage t2 = ImageUtil.pixelImageToBufferedImage(bufferedImagetoIPixelImage);

//    ImageUtil.saveBufferedImage("IPixelImagetoBufferedImage",
//        t2, "gif");

    //instantiate testModel
    ProcessingModel testModel = new ProcessingModel("testModel");

    //add layers to testModel
    testModel.addLayer("first");
    testModel.addImageToLayer("first",testCar);
    testModel.addLayer("second");
    testModel.addImageToLayer("second",bufferedImagetoIPixelImage);

    //save testModel. saves all of the sub images in sub directories.
    testModel.saveMultiLayerImage();

    //make a new model by loading in the saved model.
    ProcessingModel newModel = new ProcessingModel("testModel.txt","res/");
    newModel.deleteLayer("first");
    //save that model
    newModel.saveMultiLayerImage();

    //turns off the second layer
    newModel.toggle("second");

   // newModel.saveTopMostVisible("savethetop!","png");
    newModel.addLayer("third");
    newModel.addImageToLayer("third",new Sepia().apply(bufferedImagetoIPixelImage));

    //turns off the third layer.
    newModel.toggle("third");

    newModel.addLayer("fourth");
    newModel.addImageToLayer("fourth",new Checkerboard(100,10));
    //turns off the fourth layer.
    newModel.toggle("fourth");
    newModel.toggle("fourth");

    newModel.addLayer("5");
    newModel.addImageToLayer("5",new Sharpen().apply(sharpB));

    newModel.addLayer("6");
    newModel.addImageToLayer("6",sepiaB);


    newModel.blendLayers("5","6","blended5");

    newModel.saveTopMostVisible("should be the blendedImage","png");
    //newModel.saveMultiLayerImage();

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
