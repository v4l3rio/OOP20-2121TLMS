package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * Extends JPanel to create a Panel with background
 *
 */
public class JPanelWithBackground extends JPanel {

	private static final long serialVersionUID = -5042514025454946591L;
	private final Image backgroundImage;

	/**
	 * 
	 * @param fileName 
	 * 		      the path of the image
	 * @throws IOException
	 * 		      if an I/O error occurs
	 */
	public JPanelWithBackground(String fileName) throws IOException {
		backgroundImage = ImageIO.read(new File(fileName));
		this.setOpaque(false);
	}
	
	 /**
	  * {@inheritDoc}
	  */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
