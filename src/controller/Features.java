package controller;

/**
 * Determines which keys control specific actions. In other words it is the application controller.
 */
public interface Features {

  /**
   * Loads an image when called upon by a user.
   */
  void loadImage();

  /**
   * Saves an image only when called upon.
   */
  void saveImage();

  /**
   * Represents either a horizontal or vertical flip.
   */
  void flip(String s);

  /**
   * Represents the brightening of an image.
   */
  void brighten(int inc);

  /**
   * Darkens an image when called upon.
   */
  void darken(int inc);

  /**
   *Greys an image in a certain way when the user asks.
   */
  void greyScale(String comp);

  /**
   * Blurs the image when the user asks.
   */
  void blur();

  /**
   * Creates a sepia tone version of the image.
   */
  void sepiaTone();

  /**
   * Sharpens the image when the user asks.
   */
  void sharpen();

  /**
   * Resizes an image down.
   */
  void downSize();

}