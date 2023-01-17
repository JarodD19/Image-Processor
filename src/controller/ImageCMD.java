package controller;

import java.io.IOException;

/**
 * Interface meant to hold all methods used in the controller.
 */
public interface ImageCMD {

  /**
   * Load an image from the specified path and refer it to henceforth in
   * the program by the given image name.
   *
   * @param path the path the client specifies
   * @param name the name the client specifies for the image
   */
  void loadImage(String path, String name) throws IOException;

  /**
   * Save the image with the given name to the specified
   * path which should include the name of the file.
   *
   * @param path the path the client specifies
   * @param name the name the client specifies for the image
   */
  void saveImage(String path, String name) throws IOException;

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
}
