package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

	private static final long serialVersionUID = -5042514025454946591L;
	private final Image backgroundImage;

	  public JPanelWithBackground(String fileName) throws IOException {
	    backgroundImage = ImageIO.read(new File(fileName));
	    this.setOpaque(false);
	  }

	  @Override
	  public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.drawImage(backgroundImage, 0, 0, this);

	  }
	}
