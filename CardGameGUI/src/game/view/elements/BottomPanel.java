package game.view.elements;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {
		
	@SuppressWarnings("unused")
	private GameFrame frame;
	private GameStatusBar gameBar;
	
	public GameStatusBar getGameBar() {
		return gameBar;
	}
	
	// constructor 
	public BottomPanel(GameFrame frame) {
		this.frame = frame;
		this.setBackground(Color.YELLOW);		
	}
	
	// add elements to panel
	public void ConstructBottomPanel() {
		setLayout(new BorderLayout());
		gameBar = new GameStatusBar();
		this.add(gameBar, BorderLayout.SOUTH);
	}
}
