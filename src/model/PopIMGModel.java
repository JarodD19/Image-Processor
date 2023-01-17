package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class that represents a popular image model, either a png, jgp, or a bpm file type.
 * This will make our application more usable.
 */
public class PopIMGModel extends AbstractModel {

  /**
   * Creates the PopIMGModel using a width, height, maxValue, Image.
   *
   * @param width    the width of the image.
   * @param height   the height of the image.
   * @param maxValue the max rgb value of any pixel in the image.
   * @param image    the actual representation of the image with a 2d array.
   */
  public PopIMGModel(int width, int height, int maxValue, Pixel[][] image) {
    super(width, height, maxValue, image);
  }

  /**
   * Creates a model for a Popular image file that holds no data, an empty file.
   * Used to initialize models as a PPM so that they can be loaded properly.
   */
  public PopIMGModel() {
    // creates a model for a PPM file that holds no data
  }

  /**
   * Constructor meant to initialize an object of a PopIMGModel with a filepath and a filename.
   *
   * @param filePath  the file path of a model of a popular image
   * @param imageName the image name of a model of a popular image
   * @throws FileNotFoundException when the file with the path cannot be found
   */
  public PopIMGModel(String filePath, String imageName) throws FileNotFoundException {
    PopIMGModel temp;
    try {
      temp = ImageUtil.readImage(filePath);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found.");
    }
    this.imageName = imageName;

    this.fileType = filePath.substring(filePath.indexOf("."));

    this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

    this.imagePath = filePath;

    this.height = temp.height;

    this.width = temp.width;

    this.image = temp.image;

    this.maxValue = temp.maxValue;
  }

  @Override
  public BetterImageModel loadImage(String path, String name) throws FileNotFoundException {
    currentImages.put(name, new PopIMGModel(path, name));
    return currentImages.get(name);
  }

  @Override
  public void loadModel(String name) {
    this.imageName = name;
    currentImages.put(name, new PopIMGModel(width, height, maxValue, this.getImage()));
  }

  @Override
  public BetterImageModel fromModel(int width, int height, int maxValue,
                                    Pixel[][] image, String fileType) {
    PopIMGModel model = new PopIMGModel(width, height, maxValue, image);
    model.fileType = fileType;
    return model;
  }

  @Override
  public void saveImage(String path, String name) throws FileNotFoundException {
    this.imagePath = path;
    File file = new File(path);
    BufferedImage img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        img.setRGB(j, i, this.image[i][j].getColor().getRGB());
      }
    }
    try {
      ImageIO.write(img, path.substring(path.indexOf(".") + 1), file);
    } catch (IOException e) {
      throw new FileNotFoundException("Can not create file.");
    }
  }
}

