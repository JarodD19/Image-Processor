package view;

import javax.swing.JFileChooser;

import controller.Features;
import model.BetterImageModel;

/**
 * The interface for our view class specifying the actions the view offers.
 */
public interface ImageView {

  void addFeatures(Features feature);

  int showFileChooser(JFileChooser loadFileChooser);

  void loadErrorPopup();

  void change(BetterImageModel model);

  void saveErrorPopup();
}
