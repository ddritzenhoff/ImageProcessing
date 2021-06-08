package cs3500.imageProcessing.model;

public class Kernel implements IKernel{
  private final int size;
  ArrayList<ArrayList<Int>> values;

  public Kernel(int size) {
    this.size = size;
  }

}
