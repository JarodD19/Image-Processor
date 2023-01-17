package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static PPMModel readPPM(String filename) throws FileNotFoundException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] image = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        // this produces a pixel for every i j "value" using the corresponding r g b values
        image[i][j] = new Pixel(r, g, b);
      }
    }
    return new PPMModel(width, height, maxValue, image);
  }

  /**
   * Read an image file in a non-ASCII format and return the correct ImageModel.
   *
   * @param filename the path of the file.
   */
  public static PopIMGModel readImage(String filename) throws FileNotFoundException {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new FileNotFoundException("File not found.");
    }

    int width = img.getWidth();
    int height = img.getHeight();
    Pixel[][] image = new Pixel[height][width];
    int maxValue = 255;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int thisPix = img.getRGB(j, i);
        //found pieces of the following code on stack overflow
        //asked a ta if this was alright. he said it was okay to use.
        image[i][j] = new Pixel((thisPix >> 16) & 0xff,
                (thisPix >> 8) & 0xff, (thisPix) & 0xff);
      }
    }
    return new PopIMGModel(width, height, maxValue, image);
  }

}

