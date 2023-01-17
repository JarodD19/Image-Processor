
import java.io.IOException;

/**
 * This version of appendable will always throw the IOException.
 * Implements the append function but only throws the IOException.
 * This class is used to test that the code in the view correctly handles the IOException being
 * thrown.
 */
public class AppendableThrow implements Appendable {

  /**
   * Constructs an appendablethrow object.
   * Constructor doesn't have any paramaters.
   */
  public AppendableThrow() {
    // this constructor does not have any parameters
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }


  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }


}
