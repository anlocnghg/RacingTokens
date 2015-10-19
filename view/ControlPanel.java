/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.RaceRunnable;
import model.Race;
import model.Token;

public class ControlPanel {
	private JPanel panel;
	private JButton startResumeButton;
	private JButton incSpeedButton;
	private JButton decSpeedButton;
	private JButton lineUpButton;

	private Race race;
	private RaceFrame raceFrame;
	private RacePanel racePanel;
	private int maxVel = 300;

	private RaceRunnable raceRunnable;

	private JLabel winnerLabel;

	public void setWinnerLabel(Token token) {
		winnerLabel.setForeground(token.getColor());
		winnerLabel.setText(token.getName() + " is the Winner. Congrats!!");
	}

	public ControlPanel(RaceFrame raceFrame, Race race, RacePanel racePanel) {
		this.raceFrame = raceFrame;
		this.race = race;
		this.racePanel = racePanel;
		createPartControl();
	}

	private void createPartControl() {
		panel = new JPanel();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		startResumeButton = new JButton("Start");
		incSpeedButton = new JButton("++ Speed");
		incSpeedButton.setEnabled(false);
		decSpeedButton = new JButton("-- Speed");
		decSpeedButton.setEnabled(false);
		lineUpButton = new JButton("Line tokens up");
		lineUpButton.setEnabled(false);

		buttonPanel.add(startResumeButton);
		buttonPanel.add(incSpeedButton);
		buttonPanel.add(decSpeedButton);
		buttonPanel.add(lineUpButton);

		winnerLabel = new JLabel("Who wins?");
		winnerLabel.setOpaque(true); // to set background
		winnerLabel.setBackground(Color.white);
		winnerLabel.setForeground(Color.darkGray);

		panel.add(buttonPanel);
		panel.add(winnerLabel);

		handleEvents(startResumeButton);
	}

	private void handleEvents(final JButton startResumeButton) {
		// Start/Resume button
		startResumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (startResumeButton.getText() == "Start") {
					processStartStatus(startResumeButton);
				} else if (startResumeButton.getText() == "Stop") {
					processStopStatus(startResumeButton);
				} else if (startResumeButton.getText() == "Resume") {
					processResumeStatus(startResumeButton);
				}

			}

			private void processResumeStatus(JButton btn) {
				setRaceRunning();
				btn.setText("Stop");
			}

			private void processStopStatus(JButton btn) {
				stopRacing();
				btn.setText("Resume");
			}

			private void processStartStatus(JButton btn) {
				if (btn.getText() == "Start") {
					setRaceRunning();
				}
				btn.setText("Stop");

				incSpeedButton.setEnabled(true);
				decSpeedButton.setEnabled(true);
				lineUpButton.setEnabled(true);
			}

		});

		// Increase Speed button
		incSpeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maxVel += 100;
				if (maxVel > 0) {
					raceRunnable.setmaxVelocity(maxVel);
				}
			}
		});

		// Decrease Speed Button
		decSpeedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (maxVel > 100) {
					maxVel -= 100;
				}

				if (maxVel > 0) {
					raceRunnable.setmaxVelocity(maxVel);
				}
			}
		});

		// Line Token Up button
		lineUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRace();
				incSpeedButton.setEnabled(false);
				decSpeedButton.setEnabled(false);
			}
		});
	}

	private void setRace() {
		stopRacing();
		this.raceRunnable = null;
		race.initialize();
		raceFrame.repaintRacePanel();
		startResumeButton.setText("Start");
	}

	private void setRaceRunning() {
		if (raceRunnable == null) {
			raceRunnable = new RaceRunnable(raceFrame, race, maxVel, racePanel);
			new Thread(this.raceRunnable).start();
		} else {
			this.raceRunnable.setRaceRunning(true);
			new Thread(this.raceRunnable).start();
		}
	}

	private void stopRacing() {
		if (this.raceRunnable != null) {
			this.raceRunnable.setRaceRunning(false);
		}
	}

	public JPanel getControlPanel() {
		return this.panel;
	}

}
