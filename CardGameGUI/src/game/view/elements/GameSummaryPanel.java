package game.view.elements;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("unused")
public class GameSummaryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	private JTable playerTable;
	// create the column names
	private final String[] colNames = {"ID", "NAME", "POINTS", "BET", "RESULT", "WIN/LOSS"};
	
	public GameSummaryPanel(GameFrame frame) {		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getCenterPanel().getHeight()/3));
	}
	
	// populate the summary with features
	public void populate() {
		this.setBorder(BorderFactory.createTitledBorder("Game Summary"));
		tableModel = new DefaultTableModel(colNames, 0);
		playerTable = new JTable(tableModel);
		playerTable.setFillsViewportHeight(true);
		// add a scroll option if too big
		JScrollPane scroll = new JScrollPane(playerTable);	
		this.add(scroll, BorderLayout.CENTER);
	}
	
	// to add a player to display on summary
	public void addNewPlayer(Player player) {
		Object playerData[] = {player.getPlayerId(), 
				 	     player.getPlayerName(), 
				 	     player.getPoints(),
					     player.getBet(), 
					     player.getResult(),
					     "n/a"				     
		};
		
		// make sure duplicate players do not get displayed
		int getDuplicatePlayer = getPlayerLocation(player);
		
		if (getDuplicatePlayer != -1) {
			tableModel.removeRow(getDuplicatePlayer);
			tableModel.addRow(playerData);
		} else {
			tableModel.addRow(playerData);
		}
	}
	
	// helper method for getting player location on summary panel
	public int getPlayerLocation(Player player) {
		int LOCATION = -1;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if (player.getPlayerId().equals(tableModel.getValueAt(i, 0))) {
				LOCATION = i;
			}
		}
		return LOCATION;
	}
	
	// update player attributes on summary panel
	public void showBet(Player player) {
		tableModel.setValueAt(player.getBet(),getPlayerLocation(player), 3);
	}
	
	public void removePlayer(Player player) {
		tableModel.removeRow(getPlayerLocation(player));
	}
	
	public void setNewBalance(Player player) {
		tableModel.setValueAt(player.getPoints(), getPlayerLocation(player), 2);
	}
	
	public void showWinLoss(Player player, String finalResult) {
		tableModel.setValueAt(finalResult, getPlayerLocation(player), 5);
	}
	
	public void showPlayerResult(Player player) {
		tableModel.setValueAt(player.getResult(), getPlayerLocation(player), 4);
	}
	
}
 