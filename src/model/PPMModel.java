package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The model that represents the ppm.
 */
public class PPMModel extends AbstractModel {

  /**
   * Creates a model for a PPM file that holds no data, an empty file.
   * Used to initialize models as a PPM so that they can be loaded properly.
   */
  public PPMModel() {
    // creates a model for a PPM file that holds no data
  }

  /**
   * Uses a temporary variable to hold values of the file read by the Utils and then sets those
   * values to the new model.
   *
   * @param filePath  path of the file
   * @param imageName name of the image
   * @throws FileNotFoundException throws exception when the file cannot be found
   */
  public PPMModel(String filePath, String imageName) throws FileNotFoundException {
    PPMModel temp;
    try {
      temp = ImageUtil.readPPM(filePath);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
    this.imageName = imageName;

    this.fileType = filePath.substring(filePath.indexOf("."));

    this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

    this.imagePath = filePath;

    this.height = temp.height;

    this.width = temp.width;

    this.image = temp.getImage();

    this.maxValue = temp.maxValue;
  }

  /**
   * Creates the PPM file using a width, height, maxValue, Image.
   *
   * @param width    the width of the image
   * @param height   the height of the image
   * @param maxValue the max rgb value of any pixel in the image
   * @param image    the actual representation of the image with a 2d array
   */
  public PPMModel(int width, int height, int maxValue, Pixel[][] image) {
    super(width, height, maxValue, image);
  }

  @Override
  public BetterImageModel loadImage(String path, String name) throws FileNotFoundException {
    currentImages.put(name, new PPMModel(path, name));
    return currentImages.get(name);
  }

  @Override
  public void loadModel(String name) {
    this.imageName = name;
    currentImages.put(name, new PPMModel(width, height, maxValue, this.getImage()));
  }

  @Override
  public BetterImageModel fromModel(int width, int height,
                                    int maxValue, Pixel[][] image, String fileType) {
    PPMModel model = new PPMModel(width, height, maxValue, image);
    model.fileType = fileType;
    return model;
  }

  @Override
  public void saveImage(String path, String name) throws FileNotFoundException {
    this.imagePath = path;
    File file = new File(path);
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
    } catch (IOException e) {
      throw new FileNotFoundException("File does not exist.");
    }
    StringBuilder data = new StringBuilder();
    data.append("P3").append(System.lineSeparator())
            .append("# Created by ImageProcessor for Assignment 4")
            .append(System.lineSeparator());
    data.append(width).append(" ");
    data.append(height).append(System.lineSeparator());
    data.append(maxValue).append(System.lineSeparator());
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        data.append(pixel.getRed()).append(System.lineSeparator());
        data.append(pixel.getGreen()).append(System.lineSeparator());
        data.append(pixel.getBlue()).append(System.lineSeparator());
      }
    }
    try {
      writer.write(data.toString());
      writer.close();
    } catch (IOException e) {
      throw new FileNotFoundException("Cannot create file.");
    }
  }
}
