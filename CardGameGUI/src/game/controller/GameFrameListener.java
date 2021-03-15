package game.controller;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import game.view.elements.GameFrame;

public class GameFrameListener implements ComponentListener {

	private GameFrame frame;

	public GameFrameListener(GameFrame frame) {
		this.frame = frame;
	}

	@Override // resize all screen components based on screen size
	public void componentResized(ComponentEvent e) {
		frame.getCenterPanel().getCardPanel()
				.setPreferredSize(new Dimension(frame.getWidth(), frame.getCenterPanel().getHeight() * 2 / 3));
		frame.getCenterPanel().getCardPanel().repaint();
		frame.getCenterPanel().getCardPanel().revalidate();

		frame.getCenterPanel().getPlayerSummary()
				.setPreferredSize(new Dimension(frame.getWidth(), frame.getCenterPanel().getHeight() / 3));
		frame.getCenterPanel().getPlayerSummary().repaint();
		frame.getCenterPanel().getPlayerSummary().revalidate();

		frame.getCenterPanel().getCardPanel().setFrameHeight(frame.getHeight());
		frame.getCenterPanel().getCardPanel().setFrameWidth(frame.getWidth());

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
