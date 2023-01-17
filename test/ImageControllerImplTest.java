import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;

import controller.ImageControllerImpl;
import model.BetterImageModel;
import model.ConfirmInputsModel;
import view.TextImageView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class meant to test the controller implementation.
 * Does so by making sure the controller calls the correct method in the mock model.
 */
public class ImageControllerImplTest {
  StringBuilder log;
  Appendable dest;
  BetterImageModel pic;

  TextImageView view;
  Appendable badAppendable;
  Readable badReadable;


  @Before
  public void init() {
    log = new StringBuilder();
    dest = new StringBuilder();
    view = new TextView(dest);
    pic = new MockModel(log);
    badAppendable = new AppendableThrow();
    badReadable = new ReadableThrow();
  }

  @Test
  public void testMockAppendable() {
    try {
      new ImageControllerImpl(pic, new TextView(badAppendable),
              new StringReader("save-image testfile testfile")).control();
      fail("FileNotFoundException thrown and caught by controller, throwing an "
              + "IllegalStateException");
    } catch (IllegalStateException e) {
      // IllegalStateException thrown and caught indicating the IOException handled correctly
    }
  }

  @Test
  public void testMockReadable() {
    try {
      new ImageControllerImpl(pic,
              new TextView(dest), badReadable).control();
      fail("FileNotFoundException thrown and caught by controller,"
              + " throwing an IllegalStateException");
    } catch (IllegalStateException e) {
      // IllegalStateException thrown and caught indicating the IOException handled correctly
    }
  }

  @Test
  public void invalidConstructorTest() {
    try {
      Readable input = new StringReader("load-image res/Smaple.ppm Smaple.ppm q");

      new ImageControllerImpl(null, view, input);
      fail("Null input");
    } catch (IllegalArgumentException e) {
      //caught exception
    }

    try {

      Readable input = new StringReader("load-image res/Smaple.ppm Smaple.ppm q");

      new ImageControllerImpl(pic, null, input);
      fail("Null input");
    } catch (IllegalArgumentException e) {
      //caught exception
    }

    try {


      new ImageControllerImpl(pic, view, null);
      fail("Null input");
    } catch (IllegalArgumentException e) {
      //caught exception
    }

    try {
      new ImageControllerImpl(null, null, null);
      fail("Null input");
    } catch (IllegalArgumentException e) {
      //caught exception
    }

  }

  @Test
  public void loadImageTest() {
    Readable input = new StringReader("load-image res/Smaple.mock Smaple.mock q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("This load image function was called. ", log.toString());
  }

  @Test
  public void saveImageTest() {
    Readable input = new StringReader("load-image res/Smaple.mock Smaple.mock " +
            "save-image res/Smaple.mock Smaple.mock q");
    new ImageControllerImpl(pic, view, input).control();

    assertEquals("This load image function was called. This save image function was called. "
            , log.toString());
  }

  @Test
  public void redGreyScaleTest() {
    Readable input = new StringReader("red-component Smaple.ppm Sample.ppm q");
    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The red greyscale function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void redGreyScaleTest2() {

    Readable input = new StringReader("red-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The red greyscale function was called." +
                    " The load model function was called. ", log.toString());
  }

  @Test
  public void greenGreyScaleTest() {

    Readable input = new StringReader("green-component Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The green greyscale function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void greenGreyScaleTest2() {

    Readable input = new StringReader("green-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The green greyscale function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void blueGreyScaleTest() {

    Readable input = new StringReader("blue-component Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The blue greyscale function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void blueGreyScaleTest2() {

    Readable input = new StringReader("blue-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The blue greyscale function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void valueGreyScaleTest() {

    Readable input = new StringReader("value-component Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The value greyscale function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void valueGreyScaleTest2() {

    Readable input = new StringReader("value-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The value greyscale function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void intensityGreyScaleTest() {

    Readable input = new StringReader("intensity-component Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The intensity greyscale function was called." +
                    " The load model function was called. ", log.toString());
  }

  @Test
  public void intensityGreyScaleTest2() {

    Readable input = new StringReader("intensity-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The intensity greyscale function was called. " +
                    "The load model function was called. ",
            log.toString());
  }

  @Test
  public void lumaGreyScaleTest() {

    Readable input = new StringReader("luma-component Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The luma greyscale function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void lumaGreyScaleTest2() {

    Readable input = new StringReader("luma-component Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The luma greyscale function was called." +
                    " The load model function was called. ", log.toString());
  }

  @Test
  public void brightenTest() {

    Readable input = new StringReader("brighten 10 Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The brighten function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void brightenTest2() {

    Readable input = new StringReader("brighten 10 Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The brighten function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void darkenTest() {

    Readable input = new StringReader("darken 10 Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The darken function was called. The load model function was called. ",
            log.toString());
  }

  @Test
  public void darkenTest2() {

    Readable input = new StringReader("darken 10 Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The darken function was called. The load model function was called. ",
            log.toString());
  }

  @Test
  public void flipHorizontalTest() {

    Readable input = new StringReader("horizontal-flip Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The horizontal flip function was called. The load model function was called. ",
            log.toString());
  }

  @Test
  public void flipHorizontalTest2() {

    Readable input = new StringReader("horizontal-flip Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The horizontal flip function was called. The load model function was called. ",
            log.toString());
  }

  @Test
  public void flipVerticalTest() {

    Readable input = new StringReader("vertical-flip Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The vertical flip function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void flipVerticalTest2() {

    Readable input = new StringReader("vertical-flip Smaple.ppm Smaple.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals(
            "The vertical flip function was called. " +
                    "The load model function was called. ", log.toString());
  }

  @Test
  public void sharpenTest() {

    Readable input = new StringReader("sharpen Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The sharpen function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void blurTest() {

    Readable input = new StringReader("blur Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The blur function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void sepiaToneTest() {

    Readable input = new StringReader("sepia-tone Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The sepia tone function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void greyScaleTest() {

    Readable input = new StringReader("grey-scale Smaple.ppm Sample.ppm q");


    new ImageControllerImpl(pic, view, input).control();

    assertEquals("The greyscale function was called." +
            " The load model function was called. ", log.toString());
  }

  @Test
  public void welcomeMessageTest() {

    Readable input = new StringReader("vertical-flip Smaple.ppm Sample.ppm q");

    TextImageView view = new TextView(dest);
    new ImageControllerImpl(pic, view, input).control();

    assertEquals("Welcome to the image processing program!" + System.lineSeparator() +
                    "Supported user instructions are: " + System.lineSeparator() +
                    "load-image image-path image-name: Load an image from the specified path and " +
                    "refer it to henceforth in the program by the given image name. "
                    + System.lineSeparator() +
                    "save-image image-path image-name: Save the image with the given name to the " +
                    "specified path which should include the name of the file. "
                    + System.lineSeparator() +
                    "horizontal-flip image-name dest-image-name: Flip an image horizontally to " +
                    "create a new image, referred to henceforth by the given destination name. "
                    + System.lineSeparator() +
                    "vertical-flip image-name dest-image-name: Flip an image vertically to create a"
                    +
                    " new image, referred to henceforth by the given destination name.  "
                    + System.lineSeparator() +
                    "brighten increment image-name dest-image-name: brighten the image by the given"
                    +
                    " increment to create a new image, referred to henceforth by the given " +
                    "destination name. The increment may be positive (brightening) or negative " +
                    "(darkening)" + System.lineSeparator() +
                    "darken increment image-name dest-image-name: brighten the image by the given "
                    +
                    "increment to create a new image, referred to henceforth by the given " +
                    "destination name. The increment may be negative (brightening) or positive " +
                    "(darkening)" + System.lineSeparator() +
                    "component-type image-name dest-image-name: Create a greyscale image with the "
                    +
                    "component-type (Color representation to base greyscale off of) of the image " +
                    "with the given name, and refer to it henceforth in the program by the given " +
                    "destination name. These commands can be either red green, blue, value, luma," +
                    " intensity components should be supported. For example a user can type " +
                    "red-component." + System.lineSeparator() +
                    "blur image-name dest-image-name: Also called a Gaussian Blur, will blur the" +
                    " image by applying a filter to every channel of a pixel"
                    + System.lineSeparator() +
                    "sepia-tone image-name dest-image-name: A characteristic reddish brown color " +
                    "which uses a color transformation supplied by a matrix."
                    + System.lineSeparator() +
                    "sharpen image-name dest-image-name: Sharpens the image by accentuating edges."
                    + System.lineSeparator() +
                    "grey-scale image-name dest-image-name: GreyScale of the image using the luma "
                    +
                    "value." + System.lineSeparator() +
                    "menu (Print supported instruction list)" + System.lineSeparator() +
                    "q or quit (quit the program) " + System.lineSeparator() +
                    "Type instruction: " + System.lineSeparator() + "Type instruction: " +
                    System.lineSeparator() + "Thank you for using the image processing" +
                    " program!" + System.lineSeparator(),
            dest.toString());
  }

  // tests inputs for a normal use of the application
  @Test
  public void testInputsLoad() {
    Reader in = new StringReader("load-image res/Smaple1.con Smaple1 q");
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    BetterImageModel model = new ConfirmInputsModel(log);

    TextImageView view = new TextView(dontCareOutput);
    ImageControllerImpl controller = new ImageControllerImpl(model, view, in);

    controller.control();
    assertEquals("path = res/Smaple1.con, name = Smaple1", log.toString());
  }

  // tests that the program will throw an IllegalStateException reading no input
  @Test
  public void testNoInputsException() {
    Reader in = new StringReader("load-image res/Smaple1.con Smaple1");
    StringBuilder dontCareOutput = new StringBuilder();
    StringBuilder log = new StringBuilder();
    BetterImageModel model = new ConfirmInputsModel(log);

    TextImageView view = new TextView(dontCareOutput);
    ImageControllerImpl controller = new ImageControllerImpl(model, view, in);

    try {
      controller.control();
    } catch (IllegalStateException e) {
      assertEquals("No Input.", e.getMessage());
    }
  }

  @Test
  public void testScriptReader() throws FileNotFoundException {
    String[] args = {"-file", "Assignment6Script.txt"};
    MainImage.main(args);
    assertTrue(new File("res/mangrey.png").delete());
    assertTrue(new File("res/mansep.png").delete());
    assertTrue(new File("res/mansharp.png").delete());
    assertTrue(new File("res/manblur.png").delete());
    assertTrue(new File("res/horizflipbmp.bmp").delete());
    assertTrue(new File("res/bmpflipped.bmp").delete());
    assertTrue(new File("res/bmpdark.bmp").delete());
    assertTrue(new File("res/brightmas.png").delete());
    assertTrue(new File("res/masred.bmp").delete());
    assertTrue(new File("res/exblue.png").delete());
    assertTrue(new File("res/exluma.jpg").delete());
    assertTrue(new File("res/exint.ppm").delete());
    assertTrue(new File("res/exval.bmp").delete());
    assertTrue(new File("res/masgreen.ppm").delete());
    assertTrue(new File("res/minluma.png").delete());
    assertTrue(new File("res/minlumamask.png").delete());
  }

  @Test
  public void saveImageConversionBMPtoPPM() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/BMPSmaple.bmp bmp " +
                    "save-image res/bmpppm.ppm bmp q")).control();

    assertTrue(new File("res/bmpppm.ppm").delete());
  }

  @Test
  public void saveImageConversionJPGtoPPM() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/masterpiece.jpg mas " +
            "save-image res/mas.ppm mas q")).control();

    assertTrue(new File("res/mas.ppm").delete());
  }

  @Test
  public void saveImageConversionPNGtoPPM() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/min.png min " +
            "save-image res/min.ppm min q")).control();

    assertTrue(new File("res/min.ppm").delete());
  }

  @Test
  public void saveImageConversionPNGtoJPG() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/min.png min " +
            "save-image res/min.jpg min q")).control();

    assertTrue(new File("res/min.jpg").delete());
  }

  @Test
  public void saveImageConversionJPGtoBMP() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/masterpiece.jpg min " +
            "save-image res/min.BMP min q")).control();

    assertTrue(new File("res/min.BMP").delete());
  }

  @Test
  public void saveImageConversionPPMtoPNG() {
    new ImageControllerImpl(pic, view, new StringReader("load-image res/Exmaple.ppm Exmaple " +
            "save-image res/Exmaple.png Exmaple q")).control();

    assertTrue(new File("res/Exmaple.png").delete());
  }
}