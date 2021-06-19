package cs3500.imageprocessing.model;

import controller.IProcessingController;
import controller.ProcessingController;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layer implements ILayer {
  private int order;
  private boolean visibility; // status of the visibility of the layer
  private IPixelImage image; // actual image within the layer
  static List<String> orderedList = new ArrayList<>();
  private String layerName;
  private boolean status;


  public Layer(boolean visibility,IPixelImage image, String layerName) {
    //  this.fileName = fileName;
    this.layerName = layerName;
    this.visibility = visibility;
    this.image = image;
    this.order = orderedList.indexOf(layerName);
    this.status = (image != null);
  }


  public boolean getVisibility() {
    return visibility;
  }



  public IPixelImage getImage() {
    return new PixelImage(image.getPixels());
  }

  public int getOrder() {
    return orderedList.indexOf(layerName);
    //return order;
  }

  public String getLayerName() {
    return this.layerName;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(getOrder()).append(" ").
        append(visibility).append(" ").append("\n");
//        .append(fileName).append(" ").append(fileLocation).append("\n");
    // .append(image.toString()).append(" ");

    return sb.toString();

  }

  public Boolean getStatus() {
    return this.status;
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
    ProcessingModel testModel = new ProcessingModel();

    //add layers to testModel
    testModel.addLayer("first");
    testModel.addImageToLayer("res/jpegcar.jpg");
    testModel.addLayer("second");
    testModel.addImageToLayer("res/jpegcar.jpg");

    //save testModel. saves all of the sub images in sub directories.
    testModel.exportAll("testModelexportdominik");

    //make a new model by loading in the saved model.
    ProcessingModel newModel = new ProcessingModel("testModelexportdominik.txt");
    //newModel.load("testModelexportdominik.txt");

    newModel.addLayer("third");
    newModel.addImageToLayer("res/jpegcar.jpg");

    newModel.setWorkingLayer("second");
    newModel.deleteLayer();

    //save that model
    newModel.exportAll("testModelexportdominik2ndround");


    //turns off the second layer


    newModel.setVisiblity("third", false);
    //newModel.toggle("second");

    // newModel.saveTopMostVisible("savethetop!","png");
    newModel.addLayer("fourth");
    newModel.addImageToLayer("res/jpegcar.jpg");

    newModel.setVisiblity("fourth", false);
    newModel.exportAll("testModelexportdominik3ndround");

    newModel.addLayer("fifth");
    newModel.addImageToLayer("res/jpegcar.jpg");
    newModel.setWorkingLayer("fifth");
    newModel.applyTransformation(new Sepia());

    newModel.setWorkingLayer("fourth");
    newModel.applyTransformation(new Greyscale());
    newModel.applyTransformation(new Blur());
    newModel.applyTransformation(new Blur());
    newModel.applyTransformation(new Blur());
    newModel.applyTransformation(new Blur());
    newModel.setVisiblity("fourth", true);

    newModel.setVisiblity("fifth", false);

    newModel.exportLayer("shouldbesomething.png"); // does a png for now.

    newModel.exportAll("testModelexportdominik3rdround");

    IModel newModel3 = new ProcessingModel("testModelexportdominik3rdround.txt");
    newModel3.setVisiblity("fifth",true);

    newModel3.exportAll("testModelexportdominik4thround");

    newModel3.exportLayer("shouldbesomethingsepia.png"); // does a png for now.

    Readable rd = new InputStreamReader(System.in);

    IProcessingController processingController = new ProcessingController(newModel3, rd, System.out);

    processingController.startProcessing();


    //newModel.saveTopMostVisible("should be the blendedImage","png");
    //newModel.addImageToLayer("third",new Sepia().apply(bufferedImagetoIPixelImage));

    //turns off the third layer.
    //newModel.toggle("third");
//
//    newModel.addLayer("fourth");
//    newModel.addImageToLayer("fourth",new Checkerboard(100,10));
//    //turns off the fourth layer.
//    newModel.toggle("fourth");
//    newModel.toggle("fourth");
//
//    newModel.addLayer("5");
//    newModel.addImageToLayer("5",new Sharpen().apply(sharpB));
//
//    newModel.addLayer("6");
//    newModel.addImageToLayer("6",sepiaB);
//
//
//    newModel.blendLayers("5","6","blended5");
//
//    newModel.saveTopMostVisible("should be the blendedImage","png");
//
//    newModel.deleteLayer("fourth");
//    newModel.deleteLayer("5");
//    newModel.saveMultiLayerImage();
//
//
//    ProcessingModel pm2 = new ProcessingModel("testModel.txt.txt","");
//    pm2.saveMultiLayerImage();


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
