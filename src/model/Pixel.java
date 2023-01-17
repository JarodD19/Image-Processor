package model;

import java.awt.Color;
import java.util.Objects;

/**
 * Class that represents a pixel.
 */
public class Pixel {
  // made these default so they could be used in the model
  private long red;
  private long green;
  private long blue;

  private long lumaValue;

  private long intensity;

  private long maxValue;

  private Color color;

  /**
   * Constructs a pixel.
   *
   * @param red   represents the color red
   * @param green represents the color green
   * @param blue  represents the color blue
   * @throws IllegalArgumentException if one of the rgb values are negative.
   */
  public Pixel(long red, long green, long blue) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("RGB values cannot must be non-negative.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    // stores the luma value and rounds to the nearest long value
    this.lumaValue =
            Math.round((0.2126 * (this.red)) + (0.7152 *
                    (this.green)) + (0.0722 * (this.blue)));

    this.color = new Color((int) this.red, (int) this.green, (int) this.blue);

    this.intensity = (this.red + this.green + this.blue) / 3;
    this.maxValue = Math.max(this.blue, (Math.max(this.red, this.green)));
  }

  /**
   * Changes into a pixel with r g and b being luma, intensity, or maxvalue.
   *
   * @param val the value a client would want the greyscale to be based off of
   */
  void convertTo(long val) {
    this.red = val;
    this.green = val;
    this.blue = val;
  }

  /**
   * Changes into a pixel with r g and b being only the red value.
   *
   * @param val the value a client would want the greyscale to be based off of
   */
  void convertRed(long val) {
    this.red = val;
  }

  /**
   * Changes into a pixel with r g and b being only the blue value.
   *
   * @param val the value a client would want the greyscale to be based off of
   */
  void convertBlue(long val) {
    this.blue = val;
  }

  /**
   * Changes into a pixel with r g and b being only the green value.
   *
   * @param val the value a client would want the greyscale to be based off of
   */
  void convertGreen(long val) {
    this.green = val;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    } else {
      Pixel pix = (Pixel) o;
      return (this.red == pix.red && this.green == pix.green && this.blue == pix.blue);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }

  /**
   * Gets the value of the red value of a pixel.
   */
  public long getRed() {
    return this.red;
  }

  /**
   * Gets the value of the green value of a pixel.
   */
  public long getGreen() {
    return this.green;
  }

  /**
   * Gets the value of the blue value of a pixel.
   */
  public long getBlue() {
    return this.blue;
  }

  /**
   * Gets the value of the luma value of a pixel.
   */
  public long getLumaValue() {
    return this.lumaValue;
  }

  /**
   * Gets the value of the intensity of a pixel.
   */
  public long getIntensity() {
    return this.intensity;
  }

  /**
   * Gets the value of the maxValue of a pixel.
   */
  public long getMaxValue() {
    return this.maxValue;
  }

  /**
   * Gets the value of the color of a pixel.
   */
  public Color getColor() {
    return this.color;
  }


}
