package view;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;


import controller.Features;
import model.BetterImageModel;
import model.Pixel;

/**
 * Class that represents the actual graphical user interface, which supports all functionality and
 * creates the user interface.
 */
public class ImageGUI extends JFrame implements ImageView {

  BetterImageModel model;

  TreeMap<Long, Integer> histMapRed;

  TreeMap<Long, Integer> histMapGreen;

  TreeMap<Long, Integer> histMapBlue;

  TreeMap<Long, Integer> histMapInt;

  // brightness label
  private JLabel brightDropDesc = new JLabel("Brighten/Darken Increment");

  // the label describing the scaling
  private JLabel scaleDropDesc = new JLabel("Type-Component (Type being either red, green, " +
          "blue, value, luma, or intensity)");

  // the label describing the flip
  private JLabel flipDropDesc = new JLabel("Horizontal/Vertical flip");

  private JPanel flips = new JPanel(); // horizontal and vertical

  private JPanel greyScale = new JPanel(); // all component type grey-scales

  private JPanel brightness = new JPanel(); // brighten and darken

  private JPanel filter = new JPanel(); // blur and sharpen

  private JPanel loadSave = new JPanel();

  private JPanel allButtons = new JPanel();

  private JPanel imagePanel = new JPanel();

  private JLabel hist = new JLabel();

  private JLabel hist2 = new JLabel();

  private JLabel hist3 = new JLabel();

  private JLabel hist4 = new JLabel();

  private JPanel histPanel = new JPanel(); // panel for the histogram

  private JPanel redHistPanel = new JPanel(); // panel for the histogram

  private JPanel blueHistPanel = new JPanel(); // panel for the histogram

  private JPanel greenHistPanel = new JPanel(); // panel for the histogram

  private JPanel greyHistPanel = new JPanel(); // panel for the histogram

  private JButton blurButton = new JButton("Blur Image");

  private JButton downSizeButton = new JButton("Down-Size");

  private JButton flipButton = new JButton("Flip Image");

  private JComboBox<String> flipDir = new JComboBox<>(new String[]{"Horizontal", "Vertical"});

  private JComboBox<String> scaleComponent = new JComboBox<>(new String[]{"red-component",
      "green-component", "blue component", "value-component", "luma-component",
      "intensity-component"});

  //meant to make a range of ints from 0 to 255
  private final int[] temp = IntStream.rangeClosed(0, 255).toArray();

  // casts the int[] to an Integer[]
  private final Integer[] range = Arrays.stream(temp).boxed().toArray(Integer[]::new);

  // throws the range variable into the combo box
  private JComboBox<Integer> brightIncrement = new JComboBox<>(range);

  private JLabel img = new JLabel();


  private JButton sharpenButton = new JButton("Sharpen Image");

  private JButton greyScaleButton = new JButton("Grey Image");

  private JButton brightenButton = new JButton("Brighten Image");

  private JButton darkenButton = new JButton("Darken Image");

  private JButton sepiaToneButton = new JButton("Apply Sepia Tone to Image");

  private JButton load = new JButton("Load Image");

  private JButton save = new JButton("Save Image");

  private JScrollPane imageScroll = new JScrollPane(img);

  private JLabel loadSaveLabel = new JLabel("Load and Save:");

  private JLabel spacer = new JLabel("");


  /**
   * ImageGUI constructor to create the visual representation of our image editor.
   * Allows the user to use buttons to edit images rather than using text inputs.
   * @param caption Name of our GUI window.
   * @param model BetterImageModel representing a image in the GUI.
   */
  public ImageGUI(String caption, BetterImageModel model) {
    super();
    this.model = model;
    this.createHist();
    setDefaultLookAndFeelDecorated(true);
    this.setTitle(caption);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1280, 720);
    this.setResizable(false);

    Container cont = this.getContentPane();

    BoxLayout box = new BoxLayout(cont, BoxLayout.LINE_AXIS);
    cont.setLayout(box);
    flips.add(flipDropDesc);
    flips.add(flipDir);
    flips.add(flipButton);

    flips.setLayout(new GridLayout(3, 1));

    greyScale.add(scaleDropDesc);
    greyScale.add(scaleComponent);
    greyScale.add(greyScaleButton);


    greyScale.setLayout(new GridLayout(3, 1));

    filter.add(blurButton);
    filter.add(sepiaToneButton);
    filter.add(sharpenButton);
    filter.add(downSizeButton);

    filter.setLayout(new GridLayout(2, 1));

    brightness.add(brightDropDesc);
    brightness.add(brightIncrement);
    brightness.add(brightenButton);
    brightness.add(darkenButton);


    brightness.setLayout(new GridLayout(4, 1));

    loadSave.add(loadSaveLabel);
    loadSave.add(spacer);
    loadSave.add(load);
    loadSave.add(save);

    loadSave.setLayout(new GridLayout(2, 1));

    allButtons.setLayout(new GridLayout(5, 5));

    // throws all buttons onto a button panel
    allButtons.add(flips);
    allButtons.add(greyScale);
    allButtons.add(filter);
    allButtons.add(brightness);
    allButtons.add(loadSave);

    imagePanel.setLayout(new GridLayout(2, 1));

    redHistPanel.setPreferredSize(new Dimension(400, 150));
    redHistPanel.setMinimumSize(redHistPanel.getPreferredSize());
    greenHistPanel.setPreferredSize(new Dimension(400, 150));
    blueHistPanel.setPreferredSize(new Dimension(400, 150));
    greyHistPanel.setPreferredSize(new Dimension(400, 150));


    // histPanel.setLayout(new GridLayout(4, 2));
    redHistPanel.add(hist);
    greenHistPanel.add(hist2);
    blueHistPanel.add(hist3);
    greyHistPanel.add(hist4);
    histPanel.add(redHistPanel);
    histPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    histPanel.add(greenHistPanel);
    histPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    histPanel.add(blueHistPanel);
    histPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    histPanel.add(greyHistPanel);
    cont.add(allButtons);
    cont.add(Box.createRigidArea(new Dimension(5, 0)));
    img.setIcon(new ImageIcon(this.createPic()));
    imageScroll.setPreferredSize(new Dimension(600, 600));
    histPanel.setPreferredSize(new Dimension(400, 600));
    imagePanel.add(imageScroll);
    cont.add(histPanel);
    cont.add(Box.createRigidArea(new Dimension(5, 0)));
    cont.add(imageScroll); // edited in the controller


    // adds the container to the frame
    //this.add(cont);
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features feature) {
    blurButton.addActionListener(evt -> feature.blur());
    flipButton.addActionListener(evt -> feature.flip(
            (Objects.requireNonNull(flipDir.getSelectedItem()).toString())));
    sharpenButton.addActionListener(evt -> feature.sharpen());
    greyScaleButton.addActionListener(evt -> feature.greyScale(Objects.requireNonNull(
            scaleComponent.getSelectedItem()).toString()));
    brightenButton.addActionListener(evt -> feature.brighten(
            (int) Objects.requireNonNull(brightIncrement.getSelectedItem())));
    darkenButton.addActionListener(evt -> feature.darken(
            (int) Objects.requireNonNull(brightIncrement.getSelectedItem())));
    sepiaToneButton.addActionListener(evt -> feature.sepiaTone());
    load.addActionListener(evt -> feature.loadImage());
    save.addActionListener(evt -> feature.saveImage());
    downSizeButton.addActionListener(evt -> feature.downSize());

  }

  /**
   * Normalize x.
   *
   * @param x The value to be normalized.
   * @return The result of the normalization.
   */
  private double normalize(double x, double y, double z) {
    return ((x - y)) / (z - y) * 125;
  }

  /**
   * Rotates a graphic 180 degrees.
   *
   * @param g   the graphic to be rotated
   * @param buf the buffered image to be acted on
   */
  private void rotateGraphic(Graphics2D g, BufferedImage buf) {
    g.rotate(Math.toRadians(180), 550 / 2,
            150 / 2);
    g.drawImage(buf, null, 0, 0);
  }

  /**
   * Paints the rectangle with the respective color for the type of histogram it is creating.
   */
  public void paintHists() {
    BufferedImage buf = new BufferedImage(550, 130, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphic = buf.createGraphics(); // creates the graphic
    rotateGraphic(graphic, buf);
    BufferedImage buf2 = new BufferedImage(550, 130, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphic2 = buf2.createGraphics(); // creates the graphic
    rotateGraphic(graphic2, buf2);
    BufferedImage buf3 = new BufferedImage(550, 130, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphic3 = buf3.createGraphics(); // creates the graphic
    rotateGraphic(graphic3, buf3);
    BufferedImage buf4 = new BufferedImage(550, 130, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphic4 = buf4.createGraphics(); // creates the graphic
    rotateGraphic(graphic4, buf4);
    graphic.setColor(new Color(250, 190, 190, 150));
    graphic.fillRect(0, 0, 1000, 1000);
    graphic2.setColor(new Color(190, 250, 190, 150));
    graphic2.fillRect(0, 0, 1000, 1000);
    graphic3.setColor(new Color(190, 190, 250, 150));
    graphic3.fillRect(0, 0, 1000, 1000);
    graphic4.setColor(new Color(190, 190, 190, 150));
    graphic4.fillRect(0, 0, 1000, 1000);
    int redMin = histMapRed.values().stream().min(Integer::compare).get();
    int greenMin = histMapGreen.values().stream().min(Integer::compare).get();
    int blueMin = histMapBlue.values().stream().min(Integer::compare).get();
    int intMin = histMapInt.values().stream().min(Integer::compare).get();
    int redMax = histMapRed.values().stream().max(Integer::compare).get();
    int greenMax = histMapGreen.values().stream().max(Integer::compare).get();
    int blueMax = histMapBlue.values().stream().max(Integer::compare).get();
    int intMax = histMapInt.values().stream().max(Integer::compare).get();
    for (int i = 0; i < 256; i++) {
      Rectangle r = new Rectangle(i * 2, 21, 2,
              (int) normalize(this.histMapRed.get((long) i), redMin, redMax));
      graphic.setColor(new Color(255, 0, 0, 150)); // sets the color of the rectangle
      graphic.fillRect(
              (int) r.getX(),
              (int) r.getY(),
              (int) r.getWidth(),
              (int) r.getHeight()
      );
    }

    for (int i = 0; i < 256; i++) {
      Rectangle r = new Rectangle(i * 2, 21, 2,
              (int) normalize(this.histMapGreen.get((long) i), greenMin, greenMax));
      graphic2.setColor(new Color(0, 255, 0, 150)); // sets the color of the rectangle
      graphic2.fillRect(
              (int) r.getX(),
              (int) r.getY(),
              (int) r.getWidth(),
              (int) r.getHeight()
      );
    }

    for (int i = 0; i < 256; i++) {
      Rectangle r = new Rectangle(i * 2, 21, 2,
              (int) normalize(this.histMapBlue.get((long) i), blueMin, blueMax));
      graphic3.setColor(new Color(0, 0, 255, 150)); // sets the color of the rectangle
      graphic3.fillRect(
              (int) r.getX(),
              (int) r.getY(),
              (int) r.getWidth(),
              (int) r.getHeight()
      );
    }

    for (int i = 0; i < 256; i++) {
      Rectangle r = new Rectangle(i * 2, 29, 2,
              (int) normalize(this.histMapInt.get((long) i), intMin, intMax));
      graphic4.setColor(new Color(80, 80, 80, 150)); // sets the color of the rectangle
      graphic4.fillRect(
              (int) r.getX(),
              (int) r.getY(),
              (int) r.getWidth(),
              (int) r.getHeight()
      );
    }
    ImageIcon histogram = new ImageIcon(buf);

    ImageIcon histogram2 = new ImageIcon(buf2);

    ImageIcon histogram3 = new ImageIcon(buf3);

    ImageIcon histogram4 = new ImageIcon(buf4);
    // adds the icon to a label
    hist.setIcon(histogram);

    hist2.setIcon(histogram2);

    hist3.setIcon(histogram3);

    hist4.setIcon(histogram4);
  }

  private void createHist() {
    this.histMapRed = new TreeMap<Long, Integer>();
    for (int i = 0; i < 256; i++) {
      this.histMapRed.put((long) i, 0);
    }
    this.histMapGreen = new TreeMap<Long, Integer>();
    for (int i = 0; i < 256; i++) {
      this.histMapGreen.put((long) i, 0);
    }
    this.histMapBlue = new TreeMap<Long, Integer>();
    for (int i = 0; i < 256; i++) {
      this.histMapBlue.put((long) i, 0);
    }
    this.histMapInt = new TreeMap<Long, Integer>();
    for (int i = 0; i < 256; i++) {
      this.histMapInt.put((long) i, 0);
    }
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        histMapRed.put(model.getPixelAt(i, j).getRed(),
                histMapRed.get(model.getPixelAt(i, j).getRed()) + 1);
        histMapGreen.put(model.getPixelAt(i, j).getGreen(),
                histMapGreen.get(model.getPixelAt(i, j).getGreen()) + 1);
        histMapBlue.put(model.getPixelAt(i, j).getBlue(),
                histMapBlue.get(model.getPixelAt(i, j).getBlue()) + 1);
        long key = (model.getPixelAt(i, j).getRed() + model.getPixelAt(i, j).getGreen()
                + model.getPixelAt(i, j).getBlue()) / 3;
        histMapInt.put(key, 1 + histMapInt.get(key));
      }
    }
    this.paintHists();

    // needs a panel

  }

  /**
   * Method to tell the user the file they chose is valid.
   *
   * @param j the file the user actually chooses
   * @return allows the user to see the file they chose
   */
  public int showFileChooser(JFileChooser j) {
    return j.showOpenDialog(ImageGUI.this);
  }

  /**
   * Meant to tell the user that a file could not be loaded in a JOption pane.
   */
  public void loadErrorPopup() {
    JOptionPane.showMessageDialog(ImageGUI.this, "This file could not " +
            "be loaded.", "ERROR", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Meant to tell the user that a file could not be saved in a JOption pane.
   */
  public void saveErrorPopup() {
    JOptionPane.showMessageDialog(ImageGUI.this, "This file could not " +
            "be saved.", "ERROR", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Meant to tell the user that a file could not be acted upon in a JOption.
   */
  public void defaultErrorPopup() {
    JOptionPane.showMessageDialog(ImageGUI.this, "File error," +
            " please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Method meant to take a certain model and apply the model as the new model in the program.
   * Also revalidates and repaints the GUI.
   * @param model the model to change to
   */
  public void change(BetterImageModel model) {
    this.model = model;
    img.setIcon(new ImageIcon(this.createPic()));
    this.createHist();
    revalidate();
    repaint();
  }

  private BufferedImage createPic() {
    BufferedImage img = new BufferedImage(this.model.getWidth(), this.model.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    Pixel[][] pic = this.model.getImage();
    int height = this.model.getHeight();
    int width = this.model.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        img.setRGB(j, i, pic[i][j].getColor().getRGB());
      }
    }
    return img;
  }
}
