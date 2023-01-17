import org.junit.Before;
import org.junit.Test;

import controller.Features;
import controller.GUIImageControllerImpl;
import model.BetterImageModel;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Class meant to test the implementation of the GUI controller.
 */
public class GUIImageControllerImplTest {

  private BetterImageModel pic;

  private ImageView view;

  private static StringBuilder log;

  static {
    log = new StringBuilder();
  }

  @Before
  public void init() {
    log = new StringBuilder();
    pic = new MockModel(log);
    view = new MockView();
  }

  @Test
  public void blurTest() {
    Features gui = new GUIImageControllerImpl(pic, view);
    gui.blur();
    assertEquals("The blur function was called. ", log.toString());
  }


  @Test
  public void redGreyScaleImageTest() {
    Features gui = new GUIImageControllerImpl(pic, view);
    gui.greyScale("red-component");

    assertEquals("The red greyscale function was called. ", log.toString());
  }

  @Test
  public void VerticalFlipImageTest() {
    Features gui = new GUIImageControllerImpl(pic, view);
    gui.flip("vertical");

    assertEquals("The vertical flip function was called. ", log.toString());
  }

  @Test
  public void sepiaToneTest() {
    Features gui = new GUIImageControllerImpl(pic, view);
    gui.sepiaTone();

    assertEquals("The sepia tone function was called. ", log.toString());
  }

}