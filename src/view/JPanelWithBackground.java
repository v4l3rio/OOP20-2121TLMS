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
	private Image backgroundImage;
	
	private JPanelWithBackground() {
	}

	/**
	 * @param fileName 
	 * 		      the path of the image
	 * @throws IOException
	 * 		      if an I/O error occurs
	 * @return the JPanel 
	 */
	public static JPanelWithBackground createJPanelWithBackgroundFromPath(String fileName) throws IOException {
		final JPanelWithBackground panel = new JPanelWithBackground();
		panel.backgroundImage = ImageIO.read(new File(fileName));
		panel.setOpaque(false);
		return panel;
	}
	
	/**
	 * @param image
	 * 	          the Image to put on background
	 * @return the JPanel 
	 */
	public static JPanelWithBackground createJPanelWithBackgroundFromImage(Image image) {
		final JPanelWithBackground panel = new JPanelWithBackground();
		panel.backgroundImage = image;
		panel.setOpaque(false);
		return panel;
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
