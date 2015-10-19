/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package controller;

import javax.swing.SwingUtilities;

import model.Race;
import view.RaceFrame;
import view.RacePanel;

public class RaceRunnable implements Runnable {

	private RaceFrame raceFrame;
	private RacePanel racePanel;
	private Race race;
	private int maxVelocity;

	private boolean raceRunning;

	public RaceRunnable(RaceFrame raceFrame, Race race, int maxVel,
			RacePanel racePanel) {
		this.raceFrame = raceFrame;
		this.race = race;
		this.racePanel = racePanel;
		this.maxVelocity = maxVel;
		raceRunning = true;
	}

	public void setmaxVelocity(int maxV) {
		this.maxVelocity = maxV;
	}

	public void setRaceRunning(boolean isRunning) {
		this.raceRunning = isRunning;
	}

	public void run() {
		while (raceRunning && race.areAllTokensRunning()) {
			int track = 2 * (racePanel.getWidth() + racePanel.getHeight());
			race.setDistance(track * race.getLaps() - 480);

			race.setTokenVelocity(this.maxVelocity);
			race.updateTokenPositions(1, racePanel.getWidth() - 120,
					racePanel.getHeight() - 120); // in milliseconds

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					raceFrame.repaintRacePanel();

					if (race.isWinner() != null) {
						raceRunning = false;
						raceFrame.declareWinner(race.isWinner());
					}
				}
			});

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

}
