package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.BetterImageModel;
import model.PPMModel;
import model.Pixel;
import model.PopIMGModel;
import view.TextImageView;

/**
 * The controller to handle user input as well as manage to model and view.
 */
public class ImageControllerImpl implements BetterImageCMD {
  private BetterImageModel model;

  private TextImageView view;

  private Readable readable;

  /**
   * Constructs the ImageControllerImpl.
   *
   * @param model    the model of PPMModel type
   * @param fileView the view of PPMView type
   * @param readable the readable input of readable type
   * @throws IllegalArgumentException if any objects are null
   */
  public ImageControllerImpl(BetterImageModel model, TextImageView fileView,
                             Readable readable) {
    if (model == null || fileView == null || readable == null) {
      throw new IllegalArgumentException("Objects cannot be null");
    }
    this.model = model;
    this.view = fileView;
    this.readable = readable;
  }

  /**
   * The main method that relinquishes control of the application to the controller.
   *
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  public void control() throws IllegalStateException {
    boolean online = true;
    Scanner sc = new Scanner(readable);
    //print the welcome message
    this.welcomeMessage();
    writeMessage("Type instruction: " + System.lineSeparator()); //prompt for the instruction name


    while (online) { //continue until the user quits
      if (sc.hasNext()) {
        String userInstruction = sc.next(); //take an instruction name
        if (userInstruction.equalsIgnoreCase("quit") ||
                userInstruction.equalsIgnoreCase("q")) {
          online = false;
        } else {

          processCommand(userInstruction, sc);
        }
      } else {
        throw new IllegalStateException("No Input.");
      }
    }
    //after the user has quit, print farewell message
    this.farewellMessage();

  }

  /**
   * Method to take in and process user input.
   *
   * @param userInstruction User input.
   * @param sc              Scanner.
   * @throws IllegalStateException throws when a file with the specified pathname does not exist.
   */
  protected void processCommand(String userInstruction, Scanner sc)
          throws IllegalArgumentException {
    String s1;
    String s2;
    String s3;
    String[] cmd = {"load-image", "save-image", "red-component", "green-component",
        "blue-component", "luma-component", "value-component", "intensity-component",
        "horizontal" +
        "-flip", "vertical-flip", "brighten", "darken", "blur", "menu", "sepia-tone",
        "sharpen", "grey-scale", "q", "quit"};

    Pixel[][] orig;
    try {
      switch (userInstruction) {
        case "load-image":
          this.loadImage(sc.next(), sc.next());
          break;
        case "save-image": //print a value from the cell
          try {
            this.saveImage(sc.next(), sc.next());
          } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
          }
          break;
        case "red-component":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.redGreyscale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.redGreyscale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "green-component":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.greenGreyscale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.greenGreyscale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "blue-component":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.blueGreyscale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.blueGreyscale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "luma-component":
          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.lumaGreyScale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.lumaGreyScale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }
          break;
        case "value-component":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.valueGreyScale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.valueGreyScale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "intensity-component":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.intensityGreyScale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.intensityGreyScale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "horizontal-flip":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.flipHorizontal();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.flipHorizontal();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "vertical-flip":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.flipVertical();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.flipVertical();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "brighten":
          int x = checkValidNum(sc.next());

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.brighten(x);
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.brighten(x);
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "darken":
          int y = checkValidNum(sc.next());

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.darken(y);
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.darken(y);
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "menu": //print the menu of supported instructions
          welcomeMessage();
          break;
        case "blur":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.blur();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.blur();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "sepia-tone":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.sepiaTone();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.sepiaTone();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "sharpen":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.sharpen();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.sharpen();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        case "grey-scale":

          s1 = sc.next();
          s2 = sc.next();
          this.change(s1);
          if (checkForCMD(cmd, sc)) {
            this.greyScale();
            loadModel(s2);
          } else {
            s3 = sc.next();
            orig = this.model.getImage();
            this.greyScale();
            this.partialImageManipulation(model.getImageData(s2), orig);
            loadModel(s3);
          }

          break;
        default: //error due to unrecognized instruction
          writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
      }
      writeMessage("Type instruction: " + System.lineSeparator()); //prompt for the instruction name

    } catch (FileNotFoundException e) {
      writeMessage(e.getMessage());
    }

  }

  private boolean checkForCMD(String[] cmd, Scanner sc) {
    for (String str : cmd) {
      Pattern pat = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
      if (sc.hasNext(pat)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Prints available commands to the user.
   *
   * @throws IllegalStateException if an IOException is thrown.
   */
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("load-image image-path image-name: Load an image from the specified path and refer"
            + " it to henceforth in the program by the given image name. "
            + System.lineSeparator());
    writeMessage("save-image image-path image-name: Save the image with the given name to the "
            + "specified path which should include the name of the file. "
            + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name: Flip an image horizontally to"
            + " create a new image, referred to henceforth by the given destination name. "
            + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name: Flip an image vertically to create "
            + "a new image, referred to henceforth by the given destination name.  "
            + System.lineSeparator());
    writeMessage("brighten increment image-name dest-image-name: brighten the image by the given "
            + "increment to create a new image, referred to henceforth by the given destination"
            + " name. The increment may be positive (brightening) or negative (darkening)"
            + System.lineSeparator());
    writeMessage("darken increment image-name dest-image-name: brighten the image by the given "
            + "increment to create a new image, referred to henceforth by the given destination"
            + " name. The increment may be negative (brightening) or positive (darkening)"
            + System.lineSeparator());
    writeMessage("component-type image-name dest-image-name: Create a greyscale image with the "
            + "component-type (Color representation to base greyscale off of) of the image with "
            + "the given name, and refer to it henceforth in the "
            + "program by the given destination name. These commands can be either red green, "
            + "blue, value, luma, intensity components should be supported. For example a user "
            + "can type red-component."
            + System.lineSeparator());
    writeMessage("blur image-name dest-image-name: Also called a Gaussian Blur, "
            + "will blur the image by applying a filter to every channel of a pixel"
            + System.lineSeparator());
    writeMessage("sepia-tone image-name dest-image-name: A characteristic reddish brown color"
            + " which uses a color transformation supplied by a matrix."
            + System.lineSeparator());
    writeMessage("sharpen image-name dest-image-name: Sharpens the image by accentuating edges."
            + System.lineSeparator());
    writeMessage("grey-scale image-name dest-image-name: GreyScale of the image "
            + "using the luma value."
            + System.lineSeparator());
    writeMessage("FILTER source-image mask-image dest-image: To apply a filter to part of an " +
            "Image type the command followed by the image" +
            "you want to edit, the mask, and the new images name."
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  /**
   * Welcomes user and prints commands.
   *
   * @throws IllegalStateException if an IOException is thrown.
   */
  private void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the image processing program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * Thanks the user and prints commands.
   *
   * @throws IllegalStateException if an IOException is thrown.
   */
  private void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using the image processing program!" + System.lineSeparator());
  }

  /**
   * Helper used to render messages as well as catch and throw exceptions.
   */
  protected void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void loadImage(String path, String name) throws FileNotFoundException {
    String ext = path.substring(path.indexOf(".") + 1);
    if (ext.equals("ppm")) {
      this.model = new PPMModel();
    } else if (ext.equals("jpg") || ext.equals("png") || ext.equals("bmp")) {
      this.model = new PopIMGModel();
    }
    try {
      this.model = model.loadImage(path, name);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
  }

  @Override
  public void saveImage(String path, String name) throws FileNotFoundException {
    change(name);
    convertFile(path);
    model.saveImage(path, name);
  }

  /**
   * Checks if the file extension is correct and creates a specific model type.
   *
   * @param path of the file that is needed to compare file type
   */
  private void convertFile(String path) {
    String s = path.substring(path.indexOf("."));
    if (s.equals(".mock")
            || s.equals(".con")) {
      return;
    }
    if (s.equals(".ppm")) {
      this.model = new PPMModel(this.model.getWidth(), this.model.getHeight(),
              this.model.getMaxValue(), this.model.getImage());
    } else {
      this.model = new PopIMGModel(this.model.getWidth(), this.model.getHeight(),
              this.model.getMaxValue(), this.model.getImage());
    }
  }

  @Override
  public void redGreyscale() {
    model.redGreyscale();

  }

  @Override
  public void partialImageManipulation(BetterImageModel model, Pixel[][] orig) {
    try {
      this.model.partialImageManipulation(model, orig);
    } catch (IllegalArgumentException e) {
      this.writeMessage(e.getMessage());
    }
  }

  @Override
  public void greenGreyscale() {
    model.greenGreyscale();

  }

  @Override
  public void blueGreyscale() {
    model.blueGreyscale();

  }

  @Override
  public void valueGreyScale() {
    model.valueGreyScale();

  }

  @Override
  public void intensityGreyScale() {
    model.intensityGreyScale();
  }

  @Override
  public void lumaGreyScale() {
    model.lumaGreyScale();
  }

  @Override
  public void brighten(int increment) {
    model.brighten(increment);
  }

  @Override
  public void darken(int increment) {
    model.darken(increment);
  }

  @Override
  public void flipHorizontal() {
    model.flipHorizontal();
  }

  @Override
  public void flipVertical() {
    model.flipVertical();
  }

  @Override
  public void blur() {
    model.blur();
  }

  @Override
  public void sepiaTone() {
    model.sepiaTone();
  }

  @Override
  public void sharpen() {
    model.sharpen();
  }

  @Override
  public void greyScale() {
    model.greyScale();
  }


  /**
   * Helper used to check whether user input is valid number, if not, catches the exception
   * and returns false.
   *
   * @param s String inputted by user.
   * @return true if the input is a valid number, false if not.
   */
  private int checkValidNum(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Expected integer value");
    }
  }

  /**
   * Will load the model by putting the name and model's attributes into a map.
   *
   * @param name of the model also the key for the model's attributes
   */
  private void loadModel(String name) {
    this.model.loadModel(name);
  }

  /**
   * Will apply the information from the model to the new model.
   *
   * @param s1 the supplied input from the user.
   */
  private void change(String s1) {
    this.model = this.model.fromModel(this.model.getImageData(s1).
                    getWidth(), this.model.getImageData(s1).getHeight(),
            this.model.getImageData(s1).getMaxValue(),
            this.model.getImageData(s1).getImage(), this.model.getFileType());
  }
}
