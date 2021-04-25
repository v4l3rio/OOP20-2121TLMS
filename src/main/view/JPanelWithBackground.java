package main.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * Extends JPanel to create a Panel with background.
 *
 */
public class JPanelWithBackground extends JPanel {

	private static final long serialVersionUID = -5042514025454946591L;
	private transient Image backgroundImage;
	
	private JPanelWithBackground() {
	}

	/**
	 * @param fileName 
	 * 		      the path of the image
	 * @throws IOException
	 * 		      if an I/O error occurs
	 * @return the JPanel 
	 */
	public static JPanelWithBackground createJPanelWithBackgroundFromURL(final URL fileName) throws IOException {
		final JPanelWithBackground panel = new JPanelWithBackground();
		panel.backgroundImage = ImageIO.read(fileName);
		panel.setOpaque(false);
		return panel;
	}
	
	/**
	 * @param image
	 * 	          the Image to put on background
	 * @return the JPanel 
	 */
	public static JPanelWithBackground createJPanelWithBackgroundFromImage(final Image image) {
		final JPanelWithBackground panel = new JPanelWithBackground();
		panel.backgroundImage = image;
		panel.setOpaque(false);
		return panel;
	}
	
	 /**
	  * {@inheritDoc}
	  */
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
