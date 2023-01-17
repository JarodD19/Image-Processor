import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.TextView;

import static org.junit.Assert.fail;

/**
 * Class meant to test the view methods.
 */
public class TextViewTest {
  Appendable ap;
  TextView view1;

  @Before
  public void init() {
    ap = new StringBuilder();
    view1 = new TextView(ap);
  }

  @Test
  public void renderMessage() {
    //here I am testing the original constructor
    Appendable badAppendable = new AppendableThrow();
    TextView textView = new TextView(badAppendable);
    try {
      textView.renderMessage("something");
      fail("this should throw an exception");
    } catch (IOException e) {
      //catches exception
    }
  }
}