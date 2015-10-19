/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Race;
import model.Token;

public class RaceFrame {

	private JFrame raceFrame;
	private RacePanel racePanel;
	private ControlPanel controlPanel;

	private Race race;

	public RaceFrame(Race race, int w, int h) {
		this.race = race;
		createPartControl(w, h);
	}

	protected void createPartControl(int frameWidth, int frameHeight) {

		racePanel = new RacePanel(race);
		controlPanel = new ControlPanel(this, race, racePanel);

		raceFrame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.racePanel, BorderLayout.CENTER);
		panel.add(this.controlPanel.getControlPanel(), BorderLayout.SOUTH);

		raceFrame.add(panel);
		raceFrame.setTitle("Racing Token");
		// raceFrame.setLocationRelativeTo(null);
		raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raceFrame.pack();
		raceFrame.setSize(frameWidth, frameHeight);
		raceFrame.setVisible(true);
	}

	public JFrame getFrame() {
		return this.raceFrame;
	}

	public void repaintRacePanel() {
		this.racePanel.repaint();
	}

	public void declareWinner(Token winnerToken) {
		this.controlPanel.setWinnerLabel(winnerToken);
	}

}
