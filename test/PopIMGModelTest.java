import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import model.BetterImageModel;
import model.Pixel;
import model.PopIMGModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class meant to test PopIMGModel's methods.
 */
public class PopIMGModelTest {
  BetterImageModel pic1;
  BetterImageModel pic2;
  BetterImageModel pic3;

  @Before
  public void setUp() {
    pic1 = new PopIMGModel();
    pic2 = new PopIMGModel();
    pic3 = new PopIMGModel();
    try {
      pic2 = pic2.loadImage("res/minio.png", "min");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
  }

  @Test
  public void redGreyscale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.redGreyscale();
    assertEquals(new Pixel(139, 139, 139), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 142, 142), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 145, 145), pic2.getPixelAt(0, 2));
  }

  @Test
  public void greenGreyscale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.greenGreyscale();
    assertEquals(new Pixel(190, 190, 190), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(193, 193, 193), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(197, 197, 197), pic2.getPixelAt(0, 2));
  }

  @Test
  public void blueGreyscale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.blueGreyscale();
    assertEquals(new Pixel(247, 247, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(250, 250, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(255, 255, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void valueGreyScale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.valueGreyScale();
    assertEquals(new Pixel(247, 247, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(250, 250, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(255, 255, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void intensityGreyScale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.intensityGreyScale();
    assertEquals(new Pixel(192, 192, 192), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(195, 195, 195), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(199, 199, 199), pic2.getPixelAt(0, 2));
  }

  @Test
  public void lumaGreyScale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.lumaGreyScale();
    assertEquals(new Pixel(183, 183, 183), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(186, 186, 186), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(190, 190, 190), pic2.getPixelAt(0, 2));
  }

  @Test
  public void brighten() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.brighten(80);
    assertEquals(new Pixel(147, 198, 255), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(147, 198, 255), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void darken() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.darken(97);
    assertEquals(new Pixel(42, 93, 150), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(45, 96, 153), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(48, 100, 158), pic2.getPixelAt(0, 2));
  }

  @Test
  public void flipHorizontal() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.flipHorizontal();
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 851));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 850));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 849));
  }

  @Test
  public void flipVertical() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.flipVertical();
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(479, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(479, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(479, 2));
  }

  @Test
  public void blur() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.blur();
    assertEquals(new Pixel(78, 106, 139), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(106, 144, 187), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(108, 147, 190), pic2.getPixelAt(0, 2));
  }

  @Test
  public void sepiaTone() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.sepiaTone();
    assertEquals(new Pixel(247, 220, 171), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(251, 223, 174), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(255, 228, 178), pic2.getPixelAt(0, 2));
  }

  @Test
  public void greyScale() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.lumaGreyScale();
    assertEquals(new Pixel(183, 183, 183), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(186, 186, 186), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(190, 190, 190), pic2.getPixelAt(0, 2));
  }

  @Test
  public void sharpen() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    pic2.sharpen();
    assertEquals(new Pixel(156, 212, 255), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(211, 255, 255), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(166, 224, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void partialLumaTest() throws FileNotFoundException {
    pic3 = pic3.loadImage("res/miniomask.png", "minmask");
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    assertEquals(new Pixel(131, 185, 247), pic2.getPixelAt(0, 848));
    assertEquals(new Pixel(153, 207, 255), pic2.getPixelAt(0, 849));
    assertEquals(new Pixel(154, 208, 255), pic2.getPixelAt(0, 850));
    Pixel[][] temp = pic2.getImage();
    pic2.lumaGreyScale();
    pic2.partialImageManipulation(pic3, temp);
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
    assertEquals(new Pixel(178, 178, 178), pic2.getPixelAt(0, 848));
    assertEquals(new Pixel(199, 199, 199), pic2.getPixelAt(0, 849));
    assertEquals(new Pixel(200, 200, 200), pic2.getPixelAt(0, 850));
  }

  @Test
  public void downSizeTest() throws FileNotFoundException {
    pic3 = pic3.loadImage("res/Bmpsmaple.bmp", "bmp");
    assertEquals(500, pic3.getHeight());
    assertEquals(500, pic3.getWidth());
    pic3.downSize(250, 250);
    assertEquals(250, pic3.getHeight());
    assertEquals(250, pic3.getWidth());
  }

  @Test
  public void getImageName() {
    assertEquals("min", pic2.getImageName());
  }

  @Test
  public void getImagePath() {
    assertEquals("res/minio.png", pic2.getImagePath());
  }

  @Test
  public void getFileType() {
    assertEquals("res/minio.png", pic2.getImagePath());
  }

  @Test
  public void getFileName() {
    assertEquals("res/minio.png", pic2.getImagePath());
  }

  @Test
  public void getPixelAt() {
    assertEquals(new Pixel(139, 190, 247), pic2.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic2.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic2.getPixelAt(0, 2));
  }

  @Test
  public void getImageData() {
    pic1 = pic1.getImageData(pic2.getImageName());
    assertEquals(new Pixel(139, 190, 247), pic1.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic1.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic1.getPixelAt(0, 2));
  }

  @Test
  public void getWidth() {
    assertEquals(852, pic2.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(480, pic2.getHeight());
  }

  @Test
  public void getMaxValue() {
    assertEquals(255, pic2.getMaxValue());
  }

  @Test
  public void getImage() {
    pic3 = new PopIMGModel(pic2.getWidth(), pic2.getHeight(), pic2.getMaxValue(), pic2.getImage());
    assertEquals(new Pixel(139, 190, 247), pic3.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic3.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic3.getPixelAt(0, 2));
  }

  @Test
  public void loadImage() {
    try {
      pic3 = pic3.loadImage("res/minio.png", "min");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    assertEquals("min", pic3.getImageName());
    assertEquals(".png", pic3.getFileType());
    assertEquals("minio.png", pic3.getFileName());
    assertEquals("res/minio.png", pic3.getImagePath());
    assertEquals(pic3, pic3.getImageData("min"));
    assertEquals(new Pixel(139, 190, 247), pic3.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic3.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic3.getPixelAt(0, 2));
  }

  @Test
  public void loadModel() {
    pic2.loadModel("min2");
    pic3 = pic3.getImageData("min2");
    assertEquals(new Pixel(139, 190, 247), pic3.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic3.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic3.getPixelAt(0, 2));
  }

  @Test
  public void fromModel() {
    pic3 = pic3.fromModel(pic2.getWidth(), pic2.getHeight(), pic2.getMaxValue(), pic2.getImage(),
            "minio.png");
    assertEquals(new Pixel(139, 190, 247), pic3.getPixelAt(0, 0));
    assertEquals(new Pixel(142, 193, 250), pic3.getPixelAt(0, 1));
    assertEquals(new Pixel(145, 197, 255), pic3.getPixelAt(0, 2));
  }

  @Test
  public void saveImage() {
    File file = new File("res/minioTest.png");
    assertTrue(file.delete());
    try {
      pic3 = pic3.loadImage("res/minioTest.png", "minTest");
      fail("File has not been saved yet so this load will fail");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    try {
      pic2.saveImage("res/minioTest.png", "minTest");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
    }
    try {
      pic3 = pic3.loadImage("res/minioTest.png", "minTest");
    } catch (FileNotFoundException e) {
      // Exception will not be thrown if project has the correct res package
      // and if the image saves correctly.
      assertEquals("minTest", pic3.getImageName());
      assertEquals(".png", pic3.getFileType());
      assertEquals("minTest.png", pic3.getFileName());
      assertEquals("res/miniotest.png", pic3.getImagePath());
      assertEquals(new Pixel(139, 190, 247), pic3.getPixelAt(0, 0));
      assertEquals(new Pixel(142, 193, 250), pic3.getPixelAt(0, 1));
      assertEquals(new Pixel(145, 197, 255), pic3.getPixelAt(0, 2));
    }
  }
}