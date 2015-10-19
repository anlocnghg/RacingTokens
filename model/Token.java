/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Token implements Runnable {
	public static final int RADIUS = 10;
	public static final int MARGIN = 5;
	public static final int DIAMETER = 2 * RADIUS;
	public static final int POSITION = DIAMETER + MARGIN;

	private static Point currentPosition = new Point(MARGIN + RADIUS + 50,
			MARGIN + RADIUS + 10);

	private static Random random = new Random();

	// double because milliseconds
	private double distance; // how far this token has moved
	private double distanceRight, distanceDown, distanceLeft, distanceUp;

	private int trackWidth, trackHeight; // track dimension

	public void setTrackWidth(int w) {
		this.trackWidth = w;
	}

	public void setTrackHeight(int h) {
		this.trackHeight = h;
	}

	private boolean shouldMoveRight, shouldMoveDown, shouldMoveLeft,
			shouldMoveUp;
	private int velocity = 100; // pixels per second
	private int timeSeconds = 1;

	public void setTimeSeconds(int millis) {
		this.timeSeconds = millis;
	}

	private Color color;
	private String name;
	private Point initialPosition;

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Token(Color color, String name) {
		this.color = color;
		this.name = name;
		setInitialPosition();
		initialize();

		this.shouldMoveRight = true;
	}

	private void setInitialPosition() {
		this.initialPosition = new Point(Token.currentPosition.x,
				Token.currentPosition.y);
		Token.currentPosition.y += POSITION; // stack up vertically
		Token.currentPosition.x += 10; // to set up fairly
	}

	public void initialize() {
		this.distance = 0;
		this.distanceDown = this.distanceLeft = this.distanceRight = this.distanceUp = 0;

		this.shouldMoveRight = true;
		this.shouldMoveDown = this.shouldMoveLeft = this.shouldMoveUp = false;

	}

	public String getName() {
		return this.name;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setVelocity(int n) {
		this.velocity = random.nextInt(n) + 1;
	}

	private void setShouldMoveRightDownLeftUpFalse() {
		this.shouldMoveRight = this.shouldMoveDown = this.shouldMoveLeft = this.shouldMoveUp = false;
	}

	// include move right, move down, move left, and move up
	public void moveToken() {
		if (this.distanceRight <= this.trackWidth) {
			setShouldMoveRightDownLeftUpFalse();
			this.shouldMoveRight = true;

			moveRight(this.timeSeconds);

		} else if (this.distanceDown <= this.trackHeight) {

			setShouldMoveRightDownLeftUpFalse();
			this.shouldMoveDown = true;
			moveDown(this.timeSeconds);

		} else if (this.distanceLeft <= this.trackWidth) {

			setShouldMoveRightDownLeftUpFalse();
			this.shouldMoveLeft = true;
			moveLeft(this.timeSeconds);

		} else if (this.distanceUp <= this.trackHeight) {

			setShouldMoveRightDownLeftUpFalse();
			this.shouldMoveUp = true;
			moveUp(this.timeSeconds);

		}
	}

	private void moveLeft(int miliseconds) {
		double pixels = 0.001D * velocity * miliseconds;
		this.distanceLeft += pixels;
		this.distance += pixels;

		this.distanceUp = 0;
	}

	private void moveUp(int miliseconds) {
		double pixels = 0.001D * velocity * miliseconds;
		this.distanceUp += pixels;
		this.distance += pixels;

		if (this.distanceUp >= this.trackHeight) {
			this.distanceRight = 0;
		}

	}

	private void moveRight(int miliseconds) {
		double pixels = 0.001D * velocity * miliseconds;
		this.distanceRight += pixels;
		this.distance += pixels;

		this.distanceDown = 0;
	}

	private void moveDown(int miliseconds) {
		double pixels = 0.001D * velocity * miliseconds;
		this.distanceDown += pixels;
		this.distance += pixels;

		this.distanceLeft = 0;
	}

	public void draw(Graphics g) {
		g.setColor(color);

		this.setColor(color);

		if (this.shouldMoveRight) {
			drawRight(g);
		} else if (this.shouldMoveDown) {
			drawDown(g);
		} else if (this.shouldMoveLeft) {
			drawLeft(g);
		} else if (this.shouldMoveUp) {
			drawUp(g);
		}
	}

	private void drawLeft(Graphics g) {
		g.fillOval(
				this.initialPosition.x + (int) Math.round(this.distanceRight)
						- (int) Math.round(this.distanceLeft),

				this.initialPosition.y + (int) Math.round(this.distanceDown),

				DIAMETER, DIAMETER);

	}

	private void drawUp(Graphics g) {
		g.fillOval(

				this.initialPosition.x + (int) Math.round(this.trackWidth)
						- (int) Math.round(this.distanceLeft),

				this.initialPosition.y + (int) Math.round(this.trackHeight)
						- (int) Math.round(this.distanceUp),

				DIAMETER, DIAMETER);

		// System.out.println("Value is: " + (int) Math.round(this.distanceUp));
	}

	private void drawRight(Graphics g) {
		g.fillOval(
				this.initialPosition.x + (int) Math.round(this.distanceRight),

				this.initialPosition.y,

				DIAMETER, DIAMETER);

	}

	private void drawDown(Graphics g) {
		g.fillOval(
				this.initialPosition.x + (int) Math.round(this.distanceRight),

				this.initialPosition.y + (int) Math.round(this.distanceDown),

				DIAMETER, DIAMETER);

	}

	public void run() {
		this.moveToken();
	}

}
