package game.view.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import model.interfaces.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import game.controller.GameFrameListener;
import game.view.model.PlayerHistory;

@SuppressWarnings({ "serial", "unused" })
public class GameFrame extends JFrame {
	private CenterPanel centerPanel;
	private TopPanel topPanel;
	private BottomPanel bottomPanel;
	private PlayerHistory playerHistory;

	private GameEngine engine;
	
	public BottomPanel getBottomPanel() {
		return bottomPanel;
	}
	public TopPanel getTopPanel() {
		return topPanel;
	}
	public CenterPanel getCenterPanel() {
		return centerPanel;
	}
	public PlayerHistory getPlayerHistory() {
		return playerHistory;
	}
	
	public GameFrame(GameEngine engine) {
		// main title
		super("CardGameGUI");
		
		// get the screen size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// making the screen size to half of the current monitor
		setMinimumSize(new Dimension((int) dim.getWidth() / 2, (int) dim.getHeight() / 2));
		setBounds((int) (dim.getWidth() / 2 - dim.getWidth() / 4), (int) (dim.getHeight() / 2 - dim.getHeight() / 4),
				(int) (dim.getWidth() / 2), (int) (dim.getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		
		playerHistory = new PlayerHistory(this);
		
		// add a listener to resize components 
		GameFrameListener frameListener = new GameFrameListener(this);
		addComponentListener(frameListener);
		this.engine = engine;
	}

	public GameEngine getEngine() {
		return engine;
	}

	public void populate() {
		// create the main panels and populate them
		topPanel = new TopPanel(this);
		topPanel.ConstructTopPanel();
		add(topPanel, BorderLayout.NORTH);

		centerPanel = new CenterPanel(this);
		centerPanel.ConstructCenterTable();
		centerPanel.ConstructCenterCardPanel();
		add(centerPanel, BorderLayout.CENTER);

		bottomPanel = new BottomPanel(this);
		bottomPanel.ConstructBottomPanel();
		add(bottomPanel, BorderLayout.SOUTH);	
	}
}
