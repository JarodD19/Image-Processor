import java.io.FileNotFoundException;
import java.io.IOException;

import model.BetterImageModel;
import model.Pixel;

/**
 * The model used to mock the methods when testing the controller.
 * This allows us to see whether the controller correctly calls the corresponding methods.
 */
public class MockModel implements BetterImageModel {
  private static Appendable log;

  private String fileExt;

  static {
    log = new StringBuilder();
  }

  public MockModel(StringBuilder log) {
    MockModel.log = log;
  }

  /**
   * Creates a new MockModel through the loadImage method.
   *
   * @param s1 Unused path string.
   * @param s2 Unused name String.
   */
  public MockModel(String s1, String s2) {
    // Used to test methods for the controller when the loadImage() method is ran on models.
  }

  @Override
  public BetterImageModel loadImage(String path, String name) {
    try {
      log.append("This load image function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new MockModel(path, name);
  }

  @Override
  public void saveImage(String path, String name) throws FileNotFoundException {
    try {
      log.append("This save image function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void redGreyscale() {
    try {
      log.append("The red greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void greenGreyscale() {
    try {
      log.append("The green greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void blueGreyscale() {
    try {
      log.append("The blue greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void valueGreyScale() {
    try {
      log.append("The value greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void intensityGreyScale() {
    try {
      log.append("The intensity greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void lumaGreyScale() {
    try {
      log.append("The luma greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void brighten(int increment) {
    try {
      log.append("The brighten function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void darken(int increment) {
    try {
      log.append("The darken function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void flipHorizontal() {
    try {
      log.append("The horizontal flip function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void flipVertical() {
    try {
      log.append("The vertical flip function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getImageName() {
    return "Smaple1.mock";
  }

  @Override
  public String getImagePath() {
    return "res/Smaple1.mock";
  }

  @Override
  public String getFileType() {
    return "mock";
  }

  @Override
  public String getFileName() {
    return "Smaple1";
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return null;
  }

  @Override
  public BetterImageModel getImageData(String s) {
    return this;
  }

  @Override
  public int getWidth() {
    return 5;
  }

  @Override
  public int getHeight() {
    return 5;
  }

  @Override
  public int getMaxValue() {
    return 255;
  }

  @Override
  public Pixel[][] getImage() {
    return new Pixel[5][];
  }

  @Override
  public void blur() {
    try {
      log.append("The blur function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sepiaTone() {
    try {
      log.append("The sepia tone function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void sharpen() {
    try {
      log.append("The sharpen function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void greyScale() {
    try {
      log.append("The greyscale function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public BetterImageModel fromModel(int width, int height, int maxValue, Pixel[][] image,
                                    String fileType) {
    return this;
  }

  @Override
  public void loadModel(String name) {
    try {
      log.append("The load model function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void downSize(int width, int height) {
    try {
      log.append("The down size function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void partialImageManipulation(BetterImageModel mask, Pixel[][] orig) {
    try {
      log.append("The partial Image Manipulation function was called. ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
