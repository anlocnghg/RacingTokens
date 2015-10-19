/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package model;

import java.awt.Graphics;
import java.util.ArrayList;

public class Race {
	private int distance; // distance track
	private ArrayList<Token> tokens;
	private int laps = 1;

	public void setLaps(int lapNum) {
		this.laps = lapNum;
	}

	public int getLaps() {
		return this.laps;
	}

	public Race() {
		this.tokens = new ArrayList<Token>();
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void initialize() {
		for (Token token : tokens) {
			token.initialize();
		}
	}

	public void addToken(Token token) {
		this.tokens.add(token);
	}

	public int getTokenCount() {
		return this.tokens.size();
	}

	public double getDistance() {
		return this.distance;
	}

	public void setTokenVelocity(int n) {
		for (Token token : tokens) {
			token.setVelocity(n);
		}
	}

	public void updateTokenPositions(int miliseconds, int width, int height) {
		for (Token token : tokens) {
			token.setTimeSeconds(miliseconds);
			token.setTrackWidth(width);
			token.setTrackHeight(height);

			// each token has its own thread
			Thread thread = new Thread(token);
			thread.start();
		}
	}

	public Token isWinner() {
		for (Token token : tokens) {
			if (this.distance <= token.getDistance()) {
				return token;
			}
		}

		return null;
	}

	public boolean areAllTokensRunning() {
		for (Token token : tokens) {
			if ((this.distance + Token.RADIUS) > token.getDistance()) {
				return true;
			}
		}

		return false;
	}

	public void draw(Graphics g) {
		for (Token token : tokens) {
			token.draw(g);
		}
	}

}
