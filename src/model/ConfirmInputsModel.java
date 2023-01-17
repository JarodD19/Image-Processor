package model;

import java.util.Objects;

/**
 * This class is used as a mock for our model in order to test inputs.
 * Does so with the string builder object created inside.
 */
public class ConfirmInputsModel extends AbstractModel {
  final StringBuilder log;

  /**
   * What constructs the mock class with only an appendable object.
   * This will allow me to confirm inputs with he stringbuilder.
   *
   * @param log the appendable that takes in the inputs
   */
  public ConfirmInputsModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
    this.imageName = "Smaple1";
    this.imagePath = "res/Smaple1.con";
    this.fileName = "Smaple1.con";
    this.fileType = ".con";
    this.image = new Pixel[5][];
  }

  @Override
  public ConfirmInputsModel loadImage(String path, String name) {
    log.append(String.format("path = %s, name = %s",
            path, name));
    return this;
    // doesn't matter what we return we only care about inputs
  }

  @Override
  public void loadModel(String name) {
    log.append(String.format("name = %s",
            name));
    // doesn't matter what we return we only care about inputs
  }

  @Override
  public BetterImageModel fromModel(int width, int height,
                                    int maxValue, Pixel[][] image, String fileType) {
    log.append(String.format("width = %d, height = %d, maxValue = %d",
            width, height, maxValue));
    return this;
  }

  @Override
  public void saveImage(String path, String name) {
    log.append(String.format("path = %s, name = %s",
            path, path));
    // doesn't matter what we return we only care about inputs
  }

}
