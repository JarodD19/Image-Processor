import javax.swing.JFileChooser;

import controller.Features;
import model.BetterImageModel;
import view.ImageView;

/**
 * Meant to be a mock of the view class to use for testing.
 */
public class MockView implements ImageView {


  public MockView() {
    // empty constructor
  }

  @Override
  public void addFeatures(Features feature) {
    // empty method body
  }

  @Override
  public int showFileChooser(JFileChooser loadFileChooser) {
    return 0;
  }

  @Override
  public void loadErrorPopup() {
    // empty method body
  }

  @Override
  public void change(BetterImageModel model) {
    // empty method body
  }

  @Override
  public void saveErrorPopup() {
    // empty method body
  }
}
