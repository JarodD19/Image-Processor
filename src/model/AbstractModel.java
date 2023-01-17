package model;

import java.util.HashMap;

/**
 * The class that is meant to initialize fields of the models and create constructors.
 */
public abstract class AbstractModel implements BetterImageModel {

  protected static HashMap<String, BetterImageModel> currentImages;

  protected Pixel[][] image;

  protected int width;

  protected int height;

  protected int maxValue;

  protected String fileName = "";

  protected String imageName = "";

  protected String imagePath = "";

  protected String fileType = "";

  static {
    currentImages = new HashMap<String, BetterImageModel>();
  }

  /**
   * The constructor of the model that takes in no fields.
   */
  public AbstractModel() {
    // when a model is needed only to make sure it is not null
  }

  public AbstractModel(String filename) {
    // used for when a model needs to be created only for its data
  }

  /**
   * The constructor of the model that uses width, height, maxValue, and Image.
   *
   * @param width    the width of the image.
   * @param height   the height of the image.
   * @param maxValue the max rgb value of any pixel in the image.
   * @param image    the image represented by a 2d array.
   */
  public AbstractModel(int width, int height, int maxValue, Pixel[][] image) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.image = image;
  }

  @Override
  public void redGreyscale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getRed());
      }
    }
  }

  @Override
  public void greenGreyscale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getGreen());
      }
    }

  }

  @Override
  public void blueGreyscale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getBlue());
      }
    }
  }

  @Override
  public void valueGreyScale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getMaxValue());
      }
    }
  }

  @Override
  public void intensityGreyScale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getIntensity());
      }
    }
  }

  @Override
  public void lumaGreyScale() {
    for (Pixel[] p : image) {
      for (Pixel pixel : p) {
        pixel.convertTo(pixel.getLumaValue());
      }
    }
  }

  @Override
  public void brighten(int increment) {
    for (Pixel[] p2 : image) {
      for (Pixel pixel : p2) {
        long max = Math.max(pixel.getRed(), Math.max(pixel.getGreen(), pixel.getBlue()));
        long diff = this.maxValue - max;
        if (diff < increment) {
          pixel.convertRed(pixel.getRed() + diff);
          pixel.convertGreen(pixel.getGreen() + diff);
          pixel.convertBlue(pixel.getBlue() + diff);
        } else {
          pixel.convertRed(pixel.getRed() + increment);
          pixel.convertGreen(pixel.getGreen() + increment);
          pixel.convertBlue(pixel.getBlue() + increment);
        }
      }
    }

    // find the pixel that needs to be "clamped"
    // find the difference of the pixel's og value and 255
    // add that value to the pixel
  }

  @Override
  public void darken(int increment) {
    for (Pixel[] p2 : image) {
      for (Pixel pixel : p2) {
        long min = Math.min(pixel.getRed(), Math.min(pixel.getGreen(), pixel.getBlue()));
        if (min < increment) {
          pixel.convertRed(pixel.getRed() - min);
          pixel.convertGreen(pixel.getGreen() - min);
          pixel.convertBlue(pixel.getBlue() - min);
        } else {
          pixel.convertRed(pixel.getRed() - increment);
          pixel.convertGreen(pixel.getGreen() - increment);
          pixel.convertBlue(pixel.getBlue() - increment);
        }
      }
    }
  }

  @Override
  public void flipHorizontal() {
    Pixel temp;
    int wTemp = width;
    // i is height j is width
    if (wTemp % 2 == 1) {
      wTemp++;
    }
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < wTemp / 2; j++) {
        temp = this.image[i][j];
        this.image[i][j] = this.image[i][this.width - j - 1];
        this.image[i][this.width - j - 1] = temp;
      }
    }

  }

  @Override
  public void flipVertical() {
    Pixel temp;
    int hTemp = height;
    // i is the row
    // j is the column
    // setting
    if (hTemp % 2 == 1) {
      hTemp++;
    }
    for (int i = 0; i < hTemp / 2; i++) {
      for (int j = 0; j < this.width; j++) {
        temp = this.image[i][j];
        this.image[i][j] = this.image[this.height - i - 1][j];
        this.image[this.height - i - 1][j] = temp;
      }
    }
  }

  @Override
  public void partialImageManipulation(BetterImageModel mask, Pixel[][] orig)
          throws IllegalArgumentException {
    if (mask.getHeight() != this.getHeight() || mask.getWidth() != this.getWidth()) {
      throw new IllegalArgumentException("Mask and image size dont match!");
    }
    Pixel[][] maskImg = mask.getImage();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (maskImg[i][j].getRed() == 255 && maskImg[i][j].getGreen() == 255 &&
                maskImg[i][j].getBlue() == 255) {
          this.image[i][j] = orig[i][j];
        }
      }
    }

  }

  @Override
  public void downSize(int width, int height) {
    if (width == this.width && height == this.height) {
      return;
    }
    Pixel[][] temp = new Pixel[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double origWidth = this.width;
        double origHeight = this.height;
        double xPos = ((double) j / (double) width) * origWidth;
        double yPos = ((double) i / (double) height) * origHeight;
        int origX = (int) xPos;
        int origY = (int) yPos;

        if (xPos - (int) xPos != 0 && yPos - (int) yPos != 0) {
          Pixel cA = this.image[(int) Math.floor(yPos)][(int) Math.floor(xPos)];
          Pixel cB = this.image[(int) Math.floor(yPos)][(int) Math.ceil(xPos)];
          Pixel cC = this.image[(int) Math.ceil(yPos)][(int) Math.floor(xPos)];
          Pixel cD = this.image[(int) Math.ceil(yPos)][(int) Math.ceil(xPos)];

          double mRedVal = (double) cB.getRed() * (xPos - Math.floor(xPos)) + cA.getRed() *
                  (Math.ceil(xPos) - xPos);
          double mGreenVal = (double) cB.getGreen() * (xPos - Math.floor(xPos)) + cA.getGreen() *
                  (Math.ceil(xPos) - xPos);
          double mBlueVal = (double) cB.getBlue() * (xPos - Math.floor(xPos)) + cA.getBlue() *
                  (Math.ceil(xPos) - xPos);

          double nRedVal = (double) cD.getRed() * (xPos - Math.floor(xPos)) + cC.getRed() *
                  (Math.ceil(xPos) - xPos);
          double nGreenVal = (double) cD.getGreen() * (xPos - Math.floor(xPos)) + cC.getGreen() *
                  (Math.ceil(xPos) - xPos);
          double nBlueVal = (double) cD.getBlue() * (xPos - Math.floor(xPos)) + cC.getBlue() *
                  (Math.ceil(xPos) - xPos);

          int cpRedVal = (int) Math.round(nRedVal * (yPos - Math.floor(yPos)) + mRedVal *
                  (Math.ceil(yPos) - yPos));
          int cpGreenVal = (int) Math.round(nGreenVal * (yPos - Math.floor(yPos)) + mGreenVal *
                  (Math.ceil(yPos) - yPos));
          int cpBlueVal = (int) Math.round(nBlueVal * (yPos - Math.floor(yPos)) + mBlueVal *
                  (Math.ceil(yPos) - yPos));

          temp[i][j] = new Pixel(cpRedVal, cpGreenVal, cpBlueVal);
        } else {
          temp[i][j] = this.image[origY][origX];
        }
      }
    }
    this.image = temp;
    this.height = height;
    this.width = width;
  }

  @Override
  public void blur() {
    float[][] blur = {
            {0.0625f, 0.125f, 0.0625f},
            {0.125f, 0.25f, 0.125f},
            {0.0625f, 0.125f, 0.0625f}};
    this.image = this.filter(blur);

  }

  @Override
  public void sepiaTone() {
    float[][] sepiaTone = {{0.393f, 0.769f, 0.189f}, {0.349f, 0.686f, 0.168f},
        {0.272f, 0.534f, 0.131f}};
    this.image = this.cTransform(sepiaTone);
  }

  @Override
  public void greyScale() {
    float[][] greyScale = {{0.2126f, 0.7152f, 0.0722f},
        {0.2126f, 0.7152f, 0.0722f}, {0.2126f, 0.7152f, 0.0722f}};
    this.image = this.cTransform(greyScale);
  }

  @Override
  public void sharpen() {
    float[][] sharpen = {{-0.125f, -0.125f, -0.125f, -0.125f, -0.125f},
        {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f}, {-0.125f, 0.25f, 1.0f, 0.25f, -0.125f},
        {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
        {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f}};
    this.image = this.filter(sharpen);

  }

  /**
   * Runs through a 2d array of pixels and applies a filter on the array by multiplying values
   * to the corresponding coordinates and summing them.
   *
   * @param filter the filter used to "process" the image whether that be blurring or sharpening.
   * @return a new image represented by a 2d array of pixels that is used to represent
   */
  private Pixel[][] filter(float[][] filter) {
    Pixel[][] tempImg = new Pixel[height][width];
    int kernalDim = filter.length;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;
        for (int k = -kernalDim / 2, y = 0; k <= kernalDim / 2 && y < kernalDim; k++, y++) {
          for (int l = -kernalDim / 2, x = 0; l <= kernalDim / 2 && x < kernalDim; l++, x++) {
            if (this.getPixelAt(i + k, j + l) != null) {
              red += (float) this.getPixelAt(i + k, j + l).getRed() * filter[y][x];
              green += (float) this.getPixelAt(i + k, j + l).getGreen() * filter[y][x];
              blue += (float) this.getPixelAt(i + k, j + l).getBlue() * filter[y][x];
            }
          }
        }
        float[] newVal = this.clamp(new float[]{red, green, blue});
        tempImg[i][j] = new Pixel((long) newVal[0], (long) newVal[1], (long) newVal[2]);
      }
    }
    return tempImg;
  }

  private Pixel[][] cTransform(float[][] tMatrix) throws IllegalArgumentException {
    Pixel[][] tempImg = new Pixel[height][width];
    if (tMatrix.length != 3) {
      throw new IllegalArgumentException("Can not perform transformation.");
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        long[] rgb = {getPixelAt(i, j).getRed(), getPixelAt(i, j).getGreen(),
                getPixelAt(i, j).getBlue()};
        float red = 0f;
        float green = 0f;
        float blue = 0f;
        red = tMatrix[0][0] * rgb[0] + tMatrix[0][1] * rgb[1] + tMatrix[0][2] * rgb[2];
        green = tMatrix[1][0] * rgb[0] + tMatrix[1][1] * rgb[1] + tMatrix[1][2] * rgb[2];
        blue = tMatrix[2][0] * rgb[0] + tMatrix[2][1] * rgb[1] + tMatrix[2][2] * rgb[2];
        float[] newVal = this.clamp(new float[]{red, green, blue});
        tempImg[i][j] = new Pixel((long) newVal[0], (long) newVal[1], (long) newVal[2]);
      }
    }
    return tempImg;
  }

  private float[] clamp(float[] rgb) {
    if (rgb[0] > 255) {
      rgb[0] = 255;
    }
    if (rgb[1] > 255) {
      rgb[1] = 255;
    }
    if (rgb[2] > 255) {
      rgb[2] = 255;
    }
    if (rgb[0] < 0) {
      rgb[0] = 0;
    }
    if (rgb[1] < 0) {
      rgb[1] = 0;
    }
    if (rgb[2] < 0) {
      rgb[2] = 0;
    }
    return rgb;
  }

  public String getImageName() {
    return this.imageName;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public String getFileType() {
    return this.fileType;
  }

  public String getFileName() {
    return this.fileName;
  }

  /**
   * Returns the pixel at the given position in this models image.
   *
   * @param row the row of the image.
   * @param col the column of the image.
   * @return A pixel value of an image.
   */
  public Pixel getPixelAt(int row, int col) {
    if (row < 0 || col < 0 || row >= this.height || col >= this.width) {
      return null;
    }
    return this.image[row][col];
  }

  public BetterImageModel getImageData(String s) {
    return currentImages.get(s);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Gets a copy of the image of this model.
   *
   * @return a 2D pixel array representing the image of this model.
   */
  public Pixel[][] getImage() {
    Pixel[][] temp = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        temp[i][j] = new Pixel(this.getPixelAt(i, j).getRed(),
                this.getPixelAt(i, j).getGreen(), this.getPixelAt(i, j).getBlue());
      }
    }
    return temp;
  }

  /**
   * Will load the model by putting the name and model's attributes into a map.
   *
   * @param name of the model also the key for the model's attributes
   */
  public abstract void loadModel(String name);

  public abstract BetterImageModel fromModel(int width, int height, int maxValue, Pixel[][] image,
                                             String fileType);
}

