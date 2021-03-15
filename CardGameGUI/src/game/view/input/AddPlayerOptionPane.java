package game.view.input;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddPlayerOptionPane {

	private JTextField id;
	private JTextField name;
	private JTextField balance;

	public AddPlayerOptionPane() {

		id = new JTextField();
		name = new JTextField();
		balance = new JTextField();
		Object[] message = { "ID:", id, "Name:", name, "Balance:", balance }; // to get 3 inputs in one tab
		
		@SuppressWarnings("unused")
		int option = JOptionPane.showConfirmDialog(null, message, "Add Player", JOptionPane.OK_CANCEL_OPTION);

	}

	public String getIDPane() {
		return id.getText();
	}

	public String getNamePane() {
		return name.getText();
	}

	public String getBalancePane() {
		return balance.getText();
	}
}
