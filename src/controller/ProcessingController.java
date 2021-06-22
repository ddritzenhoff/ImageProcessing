package controller;

import cs3500.imageprocessing.model.IModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import view.IProcessingView;
import view.ProcessingView;

/**
 * Represents the Controller responsible for handling user input. Translates string commands into
 * actual changes within the model and view.
 */
public class ProcessingController implements IProcessingController {

  protected final Scanner sc;
  protected final IModel model;
  protected final IProcessingView view;
  Map<String, Function<Scanner, ICommand>> knownCommands;

  /**
   * Constructs a ProcessingController object.
   *
   * @param model the model to be worked with.
   * @param rd    the readable from which to get the commands.
   * @param ap    the appendable to which updates will be sent to the user through the view.
   * @throws IllegalArgumentException when any of the three inputs are null.
   */
  public ProcessingController(IModel model, Readable rd, Appendable ap)
      throws IllegalArgumentException {

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

    this.knownCommands = new HashMap<>();
    initCommands();
  }

  /**
   * Adds all the commands that are currently supported.
   */
  protected void initCommands() {
    this.knownCommands.put("create-layer", s -> new AddLayer(s.next()));
    this.knownCommands.put("current", s -> new SetWorkingLayer(s.next()));
    this.knownCommands.put("load", s -> new AddImageToLayer(s.next()));
    this.knownCommands.put("blur", s -> new BlurCMD());
    this.knownCommands.put("sharpen", s -> new SharpenCMD());
    this.knownCommands.put("sepia", s -> new SepiaCMD());
    this.knownCommands.put("greyscale", s -> new GreyscaleCMD());
    this.knownCommands.put("save", s -> new ExportLayer(s.next()));
    this.knownCommands.put("invisible", s -> new MakeInvisible(s.next()));
    this.knownCommands.put("visible", s -> new MakeVisible(s.next()));
    this.knownCommands.put("remove", s -> new RemoveLayer());
    this.knownCommands.put("save-all", s -> new ExportAll(s.next()));
    this.knownCommands.put("generate-checkerboard", s -> new CreateCheckerboard(s.nextLine()));
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
   * Tries to execute the command specified by the user. If it fails, the user is notified and asked
   * to try again.
   *
   * @param cmd the command to be executed.
   */
  protected void executeCommand(Function<Scanner, ICommand> cmd) {
    try {
      ICommand c = cmd.apply(sc);
      c.apply(this.model);
    } catch (IllegalArgumentException e) {
      write("One of the arguments was incorrect. Please try again. " + e.getMessage());
    } catch (IllegalStateException e) {
      write("Hit an invalid state. Please try again. " + e.getMessage());
    }
  }

  @Override
  public void startProcessing() {

    while (this.sc.hasNext()) {
      String in = this.sc.next();

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }

      Function<Scanner, ICommand> cmd = this.knownCommands.getOrDefault(in, null);

      if (cmd == null) {
        write("no such command exists. Please try again.\n");
      } else {
        executeCommand(cmd);
      }
    }
  }
}
