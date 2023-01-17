package view;

import java.io.IOException;

/**
 * Abstract view class that is meant to eradicate redundant code.
 */
public abstract class AbstractViewText implements TextImageView {

  protected Appendable dest;

  /**
   * Constructs an view with an appendable object which can be appended onto.
   * @param dest an appendable object meant to show a message for a user.
   */
  public AbstractViewText(Appendable dest) {
    if (dest == null) {
      throw new IllegalArgumentException("Not a valid initialization.");
    } else {
      this.dest = dest;
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param mess the message that needs to be appended to the appendable object
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String mess) throws IOException {
    this.dest.append(mess);
  }
}
