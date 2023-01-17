

package controller;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.BetterImageModel;
import model.PPMModel;
import model.PopIMGModel;
import view.ImageView;

/**
 * The controller that tells the GUI what to do and when to do it.
 */
public class GUIImageControllerImpl implements Features {

  private BetterImageModel model;

  private ImageView view;


  /**
   * Contructs a GUIImageController given a better image model and an image view.
   *
   * @param model the model of the image.
   * @param view  what is being shown to the user.
   */
  public GUIImageControllerImpl(BetterImageModel model, ImageView view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }


  @Override
  public void loadImage() {
    final JFileChooser loadFileChooser = new JFileChooser(".");
    FileNameExtensionFilter extFilter = new FileNameExtensionFilter("Supported image" +
            " types are: PPM, PNG, JPG, and BMP.", "jpg", "bmp", "png", "ppm");
    loadFileChooser.setFileFilter(extFilter);
    int val = view.showFileChooser(loadFileChooser);
    if (val == JFileChooser.APPROVE_OPTION) {
      File file = loadFileChooser.getSelectedFile();
      try {
        String ext = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(".") + 1);
        if (ext.equals("ppm")) {
          this.model = new PPMModel();
        } else if (ext.equals("jpg") || ext.equals("png") || ext.equals("bmp")) {
          this.model = new PopIMGModel();
        }
        this.model = model.loadImage(file.getAbsolutePath(), "");
        this.view.change(this.model);
      } catch (FileNotFoundException e) {
        view.loadErrorPopup(); // throws the load error
      }
    }
  }

  @Override
  public void saveImage() {
    final JFileChooser saveFileChooser = new JFileChooser(".");
    int val = view.showFileChooser(saveFileChooser);
    if (val == JFileChooser.APPROVE_OPTION) {
      File file = saveFileChooser.getSelectedFile();
      try {
        model.saveImage(file.getAbsolutePath(), "");
      } catch (FileNotFoundException e) {
        view.saveErrorPopup(); // throws the save error
      }
    }
  }

  @Override
  public void flip(String s) {
    if (s.equals("Horizontal")) {
      this.model.flipHorizontal();
    } else {
      this.model.flipVertical();
    }
    this.view.change(this.model);
  }

  @Override
  public void brighten(int inc) {
    this.model.brighten(inc);
    this.view.change(this.model);

  }

  @Override
  public void darken(int inc) {
    this.model.darken(inc);
    this.view.change(this.model);
  }

  @Override
  public void greyScale(String comp) {
    switch (comp) {
      case "red-component":
        this.model.redGreyscale();
        this.view.change(this.model);
        break;
      case "green-component":
        this.model.greenGreyscale();
        this.view.change(this.model);
        break;
      case "blue-component":
        this.model.blueGreyscale();
        this.view.change(this.model);
        break;
      case "value-component":
        this.model.valueGreyScale();
        this.view.change(this.model);
        break;
      case "luma-component":
        this.model.lumaGreyScale();
        this.view.change(this.model);
        break;
      case "intensity-component":
        this.model.intensityGreyScale();
        this.view.change(this.model);
        break;
      default:
        // Not possible for there to be any other case as our
        // dropdown box has a fixed set of options.
        break;
    }
  }

  @Override
  public void blur() {
    this.model.blur();
    this.view.change(this.model);
  }

  @Override
  public void downSize() {
    int[] dim = getDimensionsPopUp();
    this.model.downSize(dim[0], dim[1]);
    this.view.change(this.model);
  }

  @Override
  public void sepiaTone() {
    this.model.sepiaTone();
    this.view.change(this.model);
  }

  @Override
  public void sharpen() {
    this.model.sharpen();
    this.view.change(this.model);
  }


  /**
   * Pops up an input box for users to specify the width and height for down-sizing an image.
   *
   * @return an array of ints index 0 being the width and index 1 being the height.
   */
  private int[] getDimensionsPopUp() {
    int[] ans = null;
    JTextField width = new JTextField(5);
    JTextField height = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("Width:"));
    myPanel.add(width);
    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    myPanel.add(new JLabel("Height:"));
    myPanel.add(height);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Please Enter width and height Values", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      ans = new int[]{Integer.parseInt(width.getText()), Integer.parseInt(height.getText())};
    }
    return ans;
  }
}
