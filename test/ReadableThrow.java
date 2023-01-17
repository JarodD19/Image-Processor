import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Implements the readable class but throws an exception every time.
 * Implements the read function and only throws an exception.
 * This is used to test that my controller implementation is able to handle the exception correctly.
 */
public class ReadableThrow implements Readable {

  /**
   * Constructs a readablethrow object.
   * Constructor doesn't have any paramaters.
   */
  public ReadableThrow() {
    // this constructor does not have any parameters
  }

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
