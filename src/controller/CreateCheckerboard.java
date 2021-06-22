package controller;

import cs3500.imageprocessing.model.IModel;
import java.util.Scanner;

/**
 * Represents a function-object to create generate a checkboard IPixelImage within the working
 * layer. This is 'triggered' with the 'generate-checkerboard' command.
 */
public class CreateCheckerboard implements ICommand {

  private final int sizeTile;
  private final int numSquares;

  /**
   * Constructs a CreateCheckerboard object.
   *
   * @param sizeArguments the arguments to the checkerboard.
   */
  public CreateCheckerboard(String sizeArguments) {
    // generate-checkerboard 100 8

    Scanner parser = new Scanner(sizeArguments);

    if (parser.hasNextInt()) {
      this.sizeTile = parser.nextInt();
    } else {
      throw new IllegalArgumentException();
    }

    if (parser.hasNextInt()) {
      this.numSquares = parser.nextInt();
    } else {
      throw new IllegalArgumentException();
    }

    parser.close();
  }

  @Override
  public void apply(IModel model) {
    model.generateCheckerboard(this.sizeTile, this.numSquares);
  }
}
