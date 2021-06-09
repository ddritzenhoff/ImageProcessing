package cs3500.imageProcessing.model;

public interface IPixel {

  //TODO: JAVADOC
  int getR();

  //TODO: JAVADOC
  int getG();

  //TODO: JAVADOC
  int getB();

  //TODO: JAVADOC
  void scaleChannels(double scalar);

  //TODO: JAVADOC
  void applyMatrix(double[][] matrix);

  //TODO: JAVADOC
  void clamp();

  //TODO: JAVADOC
  void addValues(IPixel tempPixel);


}
