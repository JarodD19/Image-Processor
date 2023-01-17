import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import model.BetterImageModel;
import model.ImageModel;
import model.PPMModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class meant to test the PPMmodel's methods.
 */
public class PPMModelTest {
  ImageModel pic1;
  ImageModel pic2;
  ImageModel pic3;
  BetterImageModel pic4;

  BetterImageModel pic5;

  BetterImageModel pic6;

  @Before
  public void setUp() {
    pic1 = new PPMModel();
    pic2 = new PPMModel();
    pic3 = new PPMModel();
    pic4 = new PPMModel();
    pic5 = new PPMModel();
    pic6 = new PPMModel();
    try {
      pic2 = pic2.loadImage("res/Exmaple.ppm", "Exmaple");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    try {
      pic5 = pic5.loadImage("res/Exmaple.ppm", "Exmaple");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }


  }

  @Test
  public void loadImage() {
    try {
      pic1 = pic1.loadImage("res/Smaple1.ppm", "Smaple");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    assertEquals("Smaple", pic1.getImageName());
    assertEquals(".ppm", pic1.getFileType());
    assertEquals("Smaple1.ppm", pic1.getFileName());
    assertEquals("res/Smaple1.ppm", pic1.getImagePath());
    assertEquals(pic1, pic1.getImageData("Smaple"));
    assertEquals(new Pixel(66, 34, 77), pic1.getPixelAt(0, 0));
    assertEquals(new Pixel(25, 235, 31), pic1.getPixelAt(0, 1));
  }

  @Test
  public void saveImage() {
    File file = new File("res/ExmapleTesting.ppm");
    assertTrue(file.delete());
    try {
      pic3 = pic3.loadImage("res/ExmapleTesting.ppm", "ExmapleSaveTest");
      fail("File has not been saved yet so this load will fail");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    try {
      pic2.saveImage("res/ExmapleTesting.ppm", "ExmapleTesting");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    try {
      pic3 = pic3.loadImage("res/ExmapleTesting.ppm", "ExmapleSaveTest");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
      // and if the image saves correctly.
      assertEquals("ExmapleSaveTest", pic3.getImageName());
      assertEquals(".ppm", pic3.getFileType());
      assertEquals("ExmapleTesting.ppm", pic3.getFileName());
      assertEquals("res/ExmapleTesting.ppm", pic3.getImagePath());
      assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
      assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    }
  }


  @Test
  public void redGreyscale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.redGreyscale();
    assertEquals(new Pixel(94, 94, 94), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 181, 181), pic2.getPixelAt(0, 2));

  }

  @Test
  public void greenGreyscale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.greenGreyscale();
    assertEquals(new Pixel(100, 100, 100), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(184, 184, 184), pic2.getPixelAt(0, 2));
  }

  @Test
  public void blueGreyscale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.blueGreyscale();
    assertEquals(new Pixel(97, 97, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(197, 197, 197), pic2.getPixelAt(0, 2));
  }

  @Test
  public void valueGreyScale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.valueGreyScale();
    assertEquals(new Pixel(100, 100, 100), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(197, 197, 197), pic2.getPixelAt(0, 2));
  }

  @Test
  public void intensityGreyScale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.intensityGreyScale();
    assertEquals(new Pixel(97, 97, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(187, 187, 187), pic2.getPixelAt(0, 2));
  }

  @Test
  public void lumaGreyScale() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.lumaGreyScale();

    assertEquals(new Pixel(99, 99, 99), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(184, 184, 184), pic2.getPixelAt(0, 2));
  }

  @Test
  public void brighten() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.brighten(80);
    assertEquals(new Pixel(174, 180, 177), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(80, 80, 80), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(239, 242, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void darken() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.darken(97);
    assertEquals(new Pixel(0, 6, 3), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(84, 87, 100), pic2.getPixelAt(0, 2));
  }

  @Test
  public void flipHorizontal() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.flipHorizontal();
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 10));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 9));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 8));
  }

  @Test
  public void flipVertical() {
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(0, 2));
    pic2.flipVertical();
    assertEquals(new Pixel(94, 100, 97), pic2.getPixelAt(10, 0));
    assertEquals(new Pixel(0, 0, 0), pic2.getPixelAt(10, 1));
    assertEquals(new Pixel(181, 184, 197), pic2.getPixelAt(10, 2));
  }

  @Test
  public void greyScale() {
    assertEquals(new Pixel(94, 100, 97), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic5.getPixelAt(0, 2));
    pic5.greyScale();
    assertEquals(new Pixel(98, 98, 98), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(184, 184, 184), pic5.getPixelAt(0, 2));
  }

  @Test
  public void blur() {
    assertEquals(new Pixel(94, 100, 97), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic5.getPixelAt(0, 2));
    pic5.blur();
    assertEquals(new Pixel(23, 25, 24), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(47, 37, 41), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(85, 52, 63), pic5.getPixelAt(0, 2));
  }

  @Test
  public void sharpen() {
    assertEquals(new Pixel(94, 100, 97), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic5.getPixelAt(0, 2));
    pic5.sharpen();
    assertEquals(new Pixel(17, 68, 53), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(41, 66, 64), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(169, 171, 184), pic5.getPixelAt(0, 2));
  }

  @Test
  public void sepiaTone() {
    assertEquals(new Pixel(94, 100, 97), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic5.getPixelAt(0, 2));
    pic5.sepiaTone();
    assertEquals(new Pixel(132, 117, 91), pic5.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic5.getPixelAt(0, 1));
    assertEquals(new Pixel(249, 222, 173), pic5.getPixelAt(0, 2));
  }

  @Test
  public void getImageName() {
    assertEquals("Exmaple", pic2.getImageName());
  }

  @Test
  public void getImagePath() {
    assertEquals("res/Exmaple.ppm", pic2.getImagePath());
  }

  @Test
  public void getFileType() {
    assertEquals(".ppm", pic2.getFileType());
  }

  @Test
  public void getFileName() {
    assertEquals("Exmaple.ppm", pic2.getFileName());
  }

  @Test
  public void fromModel() {
    pic4 = pic4.fromModel(pic5.getWidth(), pic5.getHeight(), pic5.getMaxValue(), pic5.getImage(),
            "Exmpale.ppm");
    assertEquals(new Pixel(94, 100, 97), pic4.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic4.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic4.getPixelAt(0, 2));
  }

  @Test
  public void getImageData() {
    pic4 = pic4.getImageData(pic5.getImageName());
    assertEquals(new Pixel(94, 100, 97), pic4.getPixelAt(0, 0));
    assertEquals(new Pixel(0, 0, 0), pic4.getPixelAt(0, 1));
    assertEquals(new Pixel(181, 184, 197), pic4.getPixelAt(0, 2));
  }
}