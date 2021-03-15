package game.view.elements;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {
	
	private GameFrame frame;
	private GameToolBar toolBar;
	private GameMenuBar menuBar;
	
	public GameMenuBar getMenuBar() {
		return menuBar;
	}

	public GameToolBar getToolBar() {
		return toolBar;
	}

	public TopPanel(GameFrame frame) {
		this.frame = frame;
		this.setBackground(Color.PINK);
		
	}
	
	public void ConstructTopPanel() {
		// create everything in the top panel
		setLayout(new BorderLayout());
		
		toolBar = new GameToolBar(frame);
		this.add(toolBar, BorderLayout.CENTER);
		
		menuBar = new GameMenuBar(frame);
		this.add(menuBar, BorderLayout.NORTH);
		
	}

}
