
package view;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import application.TheLastManStandingApp;
import controller.ScoreController;
import controller.ScoreControllerImpl;

/**
 * 
 * This class generates the view menu
 *
 */
public class Menu {
	
	private ScoreController controller;
	private final int BACKGROUND_WIDTH = 1280;
	private final int BACKGROUND_HEIGHT = 720;
	private JFrame mainWindow; 
	private JPanelWithBackground mainPanel;
	private List<JButton> buttonList = new ArrayList<>();
	private JButton startButton;
	private JButton usernameButton;
	private JButton rankingButton;
	private JButton controlsButton;
	private JButton exitButton;
	private JLabel title;
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
	private float[] buttonColor = Color.RGBtoHSB(102, 199, 255, null); //color using RGB settings
	
	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * 		       if an I/O error occurs
	 */
	public Menu(String[] args) throws IOException {
		
		this.controller = new ScoreControllerImpl();
		this.mainWindow = new JFrame("The Last Man Standing-Menu");
		addBackground(mainWindow, "src/assets/levels/menuBackground.png");		
		addTitle("THE LAST MAN STANDING");
		mainPanel.setLayout(null);  //disable LayoutManager to set buttons positions easier
		initButtons();
		String user = readLastUser();
		
		startButton.addActionListener(l -> {
			mainWindow.dispose();
			TheLastManStandingApp.main(args);
		});
		
		usernameButton.addActionListener(l -> {
			String name = (String)JOptionPane.showInputDialog(
		        mainWindow,"Insert Username:","", JOptionPane.QUESTION_MESSAGE, null, null, user);
			if(name != null && !name.equals("")) {
				try {
					writeUser(name);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Error Username file");
				}
			}
		});
		
		rankingButton.addActionListener(l -> {
			try {
				List<String> rankingList = controller.getRanking();
				JOptionPane.showMessageDialog(mainWindow,	
				    rankingList.get(0) + "\n" + 
					rankingList.get(1) + "\n" + 		
					rankingList.get(2),
					"Ranking",
					JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error Ranking file");
			}
		});
		exitButton.addActionListener(l -> {
			System.exit(0);
		});
		
		controlsButton.addActionListener(l -> {
			JOptionPane.showMessageDialog(mainWindow,			    
		        "A  -  left\n"
		      + "D  -  right\n"
		      + "W -  jump\n"
		      + "L  -  shoot\n"
		      + "R  -  reload\n",
		      	"Controls",
				JOptionPane.INFORMATION_MESSAGE);
		});		
		
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainWindow.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
		
	}
	
	private void addBackground(JFrame frame, String path) throws IOException {
		mainPanel = new JPanelWithBackground(path);
		frame.getContentPane().add(mainPanel);		
	}

	private void initButtons() {
		startButton = new JButton("START");
		usernameButton = new JButton("USERNAME");
		rankingButton = new JButton("RANKING");
		controlsButton = new JButton("CONTROLS");
		exitButton = new JButton("EXIT");
		buttonList.add(startButton);
		buttonList.add(controlsButton);
		buttonList.add(exitButton);
		buttonList.add(usernameButton);
		buttonList.add(rankingButton);
		setButtonColor(buttonList, buttonColor);
		buttonList.forEach(b -> mainPanel.add(b));
		controlsButton.setSize(200, 70);
		controlsButton.setLocation(230, 550);
		startButton.setSize(200, 70);
		startButton.setLocation(530, 550);
		rankingButton.setSize(200, 70);
		rankingButton.setLocation(830, 550);
		usernameButton.setSize(100, 35);
		usernameButton.setLocation(50, 585);
		exitButton.setSize(100, 35);
		exitButton.setLocation(1110, 585);
		
	}
	
	private void setButtonColor(List<JButton> buttons, float[] color) {
		buttons.forEach(b -> b.setBackground(Color.getHSBColor(color[0], color[1], color[2])));		
	}	
	
	private String readLastUser() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(TheLastManStandingApp.PATH_USER));
	    String name = reader.readLine();
	    reader.close();
	    return name;
	}
	
	private void writeUser(String user) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(TheLastManStandingApp.PATH_USER));
	    writer.write(user.toUpperCase());
	    writer.close();
	}
	
	private void addTitle(String titleName) {
		title = new JLabel(titleName);
		mainPanel.add(title);
		title.setSize(800, 100);
		title.setLocation(300, 5);
		title.setForeground(Color.white);
		title.setFont(titleFont);
	}
	
}
