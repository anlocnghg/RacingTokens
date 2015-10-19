/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Race;

@SuppressWarnings("serial")
public class RacePanel extends JPanel {
	private Race race;
	private int trackWidth, trackHeight; // outer track

	// Note: get track width and height only after the repaint()
	public int getTrackWidth() {
		return this.trackWidth;
	}

	public int getTrackHeight() {
		return this.trackHeight;
	}

	public RacePanel(Race race) {
		this.race = race;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		drawTracks(g);
		race.draw(g);

		// set track width and height
		this.trackWidth = this.getWidth();
		this.trackHeight = this.getHeight();
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}

	private void drawTracks(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRoundRect(10, 10, (int) (this.getWidth() - 20),
				(int) (this.getHeight() - 20), 100, 100);

		g.setColor(Color.yellow);
		g.drawRoundRect(150, 120, (int) (this.getWidth() - 300),
				(int) (this.getHeight() - 240), 100, 100);
	}
}
