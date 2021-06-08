package cs3500.imageProcessing.model;

public interface IPixel {
  int getR();
  int getG();
  int getB();

  void scaleChannels(double scalar);
  void scaleRGB(double r, double g , double b);

}
