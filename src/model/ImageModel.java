package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface used to hold all the methods that will be used by ImageModels.
 * These methods include the mutating of Images and saving them and getting them from files.
 */
public interface ImageModel {

  /**
   * Load an image from the specified path and refer it to henceforth
   * in the program by the given image name.
   *
   * @param path the path the client specifies
   * @param name the name the client specifies for the image
   * @return an image model whenever client wants to load an image
   * @throws IOException if transmission of the image to the provided data destination fails
   */
  BetterImageModel loadImage(String path, String name) throws FileNotFoundException;

  /**
   * Save the image with the given name to the specified
   * path which should include the name of the file.
   *
   * @param path the path the client specifies
   * @param name the name the client specifies for the image
   */
  void saveImage(String path, String name) throws FileNotFoundException;

  /**
   * Makes all components of rgb the value of the red pixel.
   */
  void redGreyscale();

  /**
   * Makes all components of rgb the value of the green pixel.
   */
  void greenGreyscale();

  /**
   * Makes all components of rgb the value of the red pixel.
   */
  void blueGreyscale();

  /**
   * Makes all components of rgb the value of the max value.
   */
  void valueGreyScale();

  /**
   * Makes all components of rgb the value of the intensity.
   */
  void intensityGreyScale();

  /**
   * Makes all components of rgb the value of the luma.
   */
  void lumaGreyScale();

  /**
   * Brightens the image by a certain degree.
   *
   * @param increment the degree to which the method brightens an image.
   */
  void brighten(int increment);

  /**
   * Darkens the image by a certain degree.
   *
   * @param increment the degree to which the method darkens an image.
   */
  void darken(int increment);

  /**
   * Flips an image horizontally.
   */
  void flipHorizontal();

  /**
   * Flips an image vertically.
   */
  void flipVertical();

  // 122 43 55
  // 122 122 122
  // 43 43 43

  /**
   * Gets the image name from the model.
   * @return a string with the image's name.
   */
  String getImageName();

  /**
   * Gets the image path from the model.
   * @return a string with the image's path.
   */
  String getImagePath();

  /**
   * Gets the file type from the model.
   * @return a string with the file type.
   */
  String getFileType();

  /**
   * Gets the file name from the model.
   * @return a string with the name of the file.
   */
  String getFileName();

  /**
   * Gets the pixel at a certain row and col of the image.
   * @param row the row of the image.
   * @param col the column of the image.
   * @return returns the pixel at the row and column specified.
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Gets the image file path from the model.
   * @param s the string representing the name.
   * @return The entire file path of the model.
   */
  public BetterImageModel getImageData(String s);

  /**
   * Gets the width of a model.
   * @return The width of the model.
   */
  public int getWidth();

  /**
   * Gets the height of a model.
   * @return The height of the model.
   */
  public int getHeight();

  /**
   * Gets the maxValue of a model.
   * @return The maxValue of the model.
   */
  public int getMaxValue();

  /**
   * Gets the image of a model.
   * @return The image of the model.
   */
  public Pixel[][] getImage();

  /**
   * Uses name as key and model as value in the map.
   * @param name of the model
   */
  void loadModel(String name);
}

