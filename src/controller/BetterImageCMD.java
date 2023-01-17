package controller;

import model.BetterImageModel;
import model.Pixel;

/**
 * Interface meant to hold all methods used in the controller. As well as new image file types
 * that are more popular.
 */
public interface BetterImageCMD extends ImageCMD {

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
   * Processes only part of an image for manipulation.
   *
   * @param mask the mask being processed with
   * @param orig the original pixel array to be acted on
   */
  void partialImageManipulation(BetterImageModel mask, Pixel[][] orig);
}
