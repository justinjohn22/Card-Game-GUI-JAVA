package game.view.elements;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import game.controller.GameMenuListener;

@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {
	
	private GameFrame gameFrame;
	private JMenu edit;
	private JMenuItem colourChange;

	public JMenuItem getColourChange() {
		return colourChange;
	}

	public GameMenuBar (GameFrame gameFrame) {
		// create a new menu bar for the player to change color
		this.gameFrame = gameFrame;
		JMenuBar jMenuBar = new JMenuBar();
		gameFrame.setJMenuBar(jMenuBar);
		edit = new JMenu("Edit");
		jMenuBar.add(edit);
		addMenuItem();
	}
	
	public void addMenuItem() {	
		// create and add the menu item
		colourChange = new JMenuItem("Change Colour");
		colourChange.addActionListener(new GameMenuListener(gameFrame));
		edit.add(colourChange);
	}
	
}

