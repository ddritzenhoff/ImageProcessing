package controller;

import cs3500.imageprocessing.model.IModel2;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import view.IProcessingView;
import view.ProcessingView;

public class ProcessingController implements IProcessingController{

  protected final Scanner sc;
  protected final IModel2 model;
  protected final IProcessingView view;
  protected boolean hasQuit;


  public ProcessingController(IModel2 model, Readable rd, Appendable ap) throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }

    if (rd == null) {
      throw new IllegalArgumentException("readable cannot be null");
    }

    this.sc = new Scanner(rd);
    this.model = model;
    this.view = new ProcessingView(this.model, ap);
    this.hasQuit = false;
  }

  /**
   * Handles getting the next element from readable.
   *
   * @return The string to direct the next move.
   */
  protected String getNext() {
    String next;
    try {
      next = this.sc.next();
      return next;
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("No element available.\n");
    }
  }

  /**
   * Appends a message to an Appendable object.
   *
   * @param message the message to be written to the appendable.
   * @throws IllegalStateException when writing the to the appendable failed.
   */
  protected void write(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Could not transmit message.");
    }
  }

  /**
   * Determines whether the user is quitting the game.
   *
   * @param input the String input from the Readable object.
   * @return true if the user is quitting and false otherwise.
   */
  protected boolean quitting(String input) {
    return input.equals("q") || input.equals("Q");
  }

  protected boolean isValidCommand(String command) {
    // TODO: there must be a better way of doing this. This is atrocious

    switch (command) {
      case "create-layer":
      case "current":
      case "load":
      case "blur":
      case "sepia":
      case "sharpen":
      case "greyscale":
      case "save":
      case "invisible":
        return true;
      default: return false;
    }
  }

  protected void executeCommand(String command) {
    // at this point, we know that we have a valid string.
    if (quitting(command)) {
      write("Exiting.\n");
      this.hasQuit = true;
      return;
    }

    // TODO: use a switch case to get commands? Should you create function objects
    //  for each command? Really not too sure how to do this.

  }

  @Override
  public void startProcessing() {

    while(!hasQuit) {

      String command;
      // handling source pile
      while (!isValidCommand(command = getNext())) {
        write("Invalid command. Try again.\n");
      }

      executeCommand(command);
    }
  }
}