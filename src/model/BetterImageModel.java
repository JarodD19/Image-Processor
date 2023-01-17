package model;

/**
 * Interface representing the methods needed for the newest implementation of image processing
 * for assignment 4. Objects of this interface have the ability to use these image processing
 * methods and that of the ImageModel.
 */
public interface BetterImageModel extends ImageModel {
  /**
   * Also called a Gaussian Blur, will blur the image by applying a filter to every channel of a
   * pixel.
   */
  void blur();

  /**
   * A characteristic reddish brown color which uses a color transformation supplied by a matrix.
   */
  void sepiaTone();

  /**
   * Sharpens the image by accentuating edges.
   */
  void sharpen();

  /**
   * GreyScale of the image using the luma value.
   */
  void greyScale();

  /**
   * Downsizes image to entered width and height.
   * @param width Desired width of downsized image.
   * @param height Desired height of downsized image.
   */
  void downSize(int width, int height);

  /**
   * Processes only part of an image for manipulation.
   * @param mask the mask being processed with
   * @param orig the original pixel array to be acted on
   */
  void partialImageManipulation(BetterImageModel mask, Pixel[][] orig);

  /**
   * For each type of model this method will return its specific type with the supplied parameters.
   *
   * @param width    the width of the model
   * @param height   the height of the model
   * @param maxValue the maximum value of the model
   * @param image    the image of the model
   * @return the model with the supplied information
   */
  BetterImageModel fromModel(int width, int height, int maxValue, Pixel[][] image, String fileType);
}
