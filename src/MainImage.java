import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.GUIImageControllerImpl;
import controller.ImageControllerImpl;
import model.BetterImageModel;
import model.PopIMGModel;
import view.ImageGUI;
import view.TextView;

/**
 * The class that holds the main function used to play the controller.
 */
public class MainImage {

  /**
   * The main function used to play the controller.
   *
   * @param args the arguments passed to the main
   */
  public static void main(String[] args) throws FileNotFoundException {
    Readable rd = new InputStreamReader(System.in);
    BetterImageModel model = new PopIMGModel("res/BMPSmaple.bmp", "");
    Appendable dest = System.out;
    if (args.length == 0) {
      new GUIImageControllerImpl(model, new ImageGUI("Image Processor", model));
    } else {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-file")) {
          try {
            if (args.length > i + 1) {
              rd = new FileReader(args[i + 1]);
            }
          } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Not a valid file.");
          }
          ImageControllerImpl controller = new ImageControllerImpl(model, new TextView(dest), rd);
          controller.control();
          break;
        } else {
          if (args[i].equals("-text")) {
            ImageControllerImpl controller = new ImageControllerImpl(model, new TextView(dest), rd);
            controller.control();
          } else {
            throw new IllegalStateException("Invalid command!");
          }
        }
      }
    }
  }
}

