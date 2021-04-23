
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import application.TheLastManStandingApp;
import controller.MapController;
import controller.MapControllerImpl;
import controller.ScoreController;
import controller.ScoreControllerImpl;
import model.score.Pair;

/**
 * This class generates the view menu.
 */
public class Menu {

	private static final int BACKGROUND_WIDTH = 1280;
	private static final int BACKGROUND_HEIGHT = 720;
	private static final double RESIZE_BUTTONS_WIDTH = 0.15;
	private static final double RESIZE_BUTTONS_HEIGHT = 0.10;
	private final JFrame mainWindow;
	private JPanelWithBackground mainPanel;
	private JButton startButton;
	private JButton usernameButton;
	private JButton rankingButton;
	private JButton controlsButton;
	private JButton exitButton;
	private JButton mapButton;
	private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private final float[] buttonColor = Color.RGBtoHSB(102, 199, 255, null); // color using RGB settings
	private final List<JButton> buttonList = new ArrayList<>();
	private final ScoreController scoreController;
	private final MapController mapController;
	private final double buttonsWidth;
	private final double buttonsHeight;
	private final double buttonsLeftPositionWidth;
	private final double buttonsRightPositionWidth;
	private final double buttonsFirstRawHeight;
	private final double buttonsSecondRawHeight;
	private final double buttonsThirdRawHeight;
	

	/**
	 * @param args
	 * @throws IOException if an I/O error occurs
	 */
	public Menu(final String[] args) throws IOException {

		this.scoreController = new ScoreControllerImpl();
		this.mapController= new MapControllerImpl(); 
		this.scoreController.firstGame();
		this.mapController.firstGame();
		this.mainWindow = new JFrame("The Last Man Standing-Menu");
		addBackground(mainWindow, "/assets/levels/menuBackground.png");
		final BufferedImage img = ImageIO.read(getClass().getResource("/assets/levels/menuBackground.png"));
		final int imgBackgroundWidth = img.getWidth();
		final int imgBackgroundHeight = img.getHeight();
		buttonsWidth = imgBackgroundWidth * RESIZE_BUTTONS_WIDTH;
		buttonsHeight = imgBackgroundHeight * RESIZE_BUTTONS_HEIGHT;
		buttonsLeftPositionWidth = imgBackgroundWidth * 0.05;
		buttonsRightPositionWidth = imgBackgroundWidth * 0.75;
		buttonsFirstRawHeight = imgBackgroundHeight * 0.15;
		buttonsSecondRawHeight = imgBackgroundHeight * 0.40;
		buttonsThirdRawHeight = imgBackgroundHeight * 0.65;
		addTitle("THE LAST MAN STANDING");
		mainPanel.setLayout(null); // disable LayoutManager to set buttons positions easier
		initButtons();
		final String user = scoreController.readUser();

		startButton.addActionListener(l -> {
			mainWindow.dispose();
			TheLastManStandingApp.main(args);
		});

		usernameButton.addActionListener(l -> {
			final String name = (String) JOptionPane.showInputDialog(mainWindow, "Insert Username:", "",
					JOptionPane.QUESTION_MESSAGE, null, null, user);
			if (name != null && !name.equals("")) {
				try {
					scoreController.writeUser(name);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error Username file");
				}
			}
		});

		rankingButton.addActionListener(l -> {
			try {
				
				final List<Pair<String,List<String>>> rankingList = scoreController.getRanking();
				JOptionPane.showMessageDialog(mainWindow,
						createStringRanking(rankingList),
						"Ranking",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error Ranking file");
			}
		});
		exitButton.addActionListener(l -> {
			mainWindow.dispose();
		});

		controlsButton.addActionListener(l -> {
			JOptionPane.showMessageDialog(mainWindow,
					"MOVEMENT\n"
				  + "A  -  left\n" 
				  + "D  -  right\n" 
				  + "W -  jump\n"
				  + "Q  -  look right\n"
				  + "E  -  look right\n"
				  + "------------------------------\n"
				  + "ACTIONS\n"
				  + "L  -  shoot\n" 
				  + "R  -  reload\n"
				  + "S  -  fly down (when player is red)"
				  , "Controls",
					JOptionPane.INFORMATION_MESSAGE);
		});
		
		mapButton.addActionListener(l -> {
			final Object[] possibilities = {"Cemetery", "Pyramid", "Canyon"};
			final String map = (String)JOptionPane.showInputDialog(
			                    mainWindow,
			                    "Choose the map:",
			                    "",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "Cemetery");
			if (map != null && !map.equals("")) {
				try {
					mapController.writeMap(map);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error Map file");
				}
			}
		});

		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainWindow.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);

	}

	/**
	 * @param frame
	 * @param path  path of Background image
	 * @throws IOException if an I/O error occurs
	 */
	private void addBackground(final JFrame frame, final String path) throws IOException {
		mainPanel = JPanelWithBackground.createJPanelWithBackgroundFromURL(getClass().getResource(path));
		frame.getContentPane().add(mainPanel);
	}

	/**
	 * initialize all buttons settings.
	 */
	private void initButtons() {
		startButton = new JButton("START");
		usernameButton = new JButton("USERNAME");
		rankingButton = new JButton("RANKING");
		controlsButton = new JButton("CONTROLS");
		exitButton = new JButton("EXIT");
		mapButton = new JButton("MAPS");
		buttonList.add(startButton);
		buttonList.add(controlsButton);
		buttonList.add(exitButton);
		buttonList.add(usernameButton);
		buttonList.add(rankingButton);
		buttonList.add(mapButton);
		setButtonColor(buttonList, buttonColor);
		buttonList.forEach(b -> mainPanel.add(b));
		buttonList.forEach(b -> b.setSize((int)buttonsWidth, (int)buttonsHeight));
		startButton.setLocation((int)buttonsLeftPositionWidth, (int)buttonsFirstRawHeight);
		controlsButton.setLocation((int)buttonsLeftPositionWidth, (int)buttonsSecondRawHeight);
		mapButton.setLocation((int)buttonsLeftPositionWidth, (int)buttonsThirdRawHeight);
		rankingButton.setLocation((int)buttonsRightPositionWidth, (int)buttonsFirstRawHeight);
		usernameButton.setLocation((int)buttonsRightPositionWidth, (int)buttonsSecondRawHeight);
		exitButton.setLocation((int)buttonsRightPositionWidth, (int)buttonsThirdRawHeight);
		
	}

	/**
	 * @param buttons the buttons lists
	 * @param color   the color array
	 */
	private void setButtonColor(final List<JButton> buttons, final float[] color) {
		buttons.forEach(b -> b.setBackground(Color.getHSBColor(color[0], color[1], color[2])));
	}

	/**
	 * @param titleName the window title
	 */
	private void addTitle(final String titleName) {
		final JLabel title;
		title = new JLabel(titleName);
		mainPanel.add(title);
		title.setSize(800, 100);
		title.setLocation(300, 5);
		title.setForeground(Color.white);
		title.setFont(titleFont);
	}
	
	/**
	 * Creates a string of all of rankings for a more intuitive view.
	 * @param rankingList
	 *            the list of rankings, one for each map
	 * @return
	 *      the string of all of rankings
	 */
	private String createStringRanking(final List<Pair<String,List<String>>> rankingList) {
		final List<String> list = new ArrayList<>(); 
		rankingList.forEach( l -> list.add(l.getX() + ":\n"
				+ l.getY().get(0) + "\n"
				+ l.getY().get(1) + "\n"
			    + l.getY().get(2) + "\n\n"));
		return list.stream().collect(Collectors.joining());
	}

}
