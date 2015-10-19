/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

import javax.swing.SwingUtilities;
import view.RaceFrame;
import model.RaceBuilder;

public class Program implements Runnable {
	private static String[] args;

	// assume
	private int lapCount = 1; // in range of 1, 2, or 3
	private int tokenCount = 2; // in range of 2, 3, or 4

	public static void main(String[] arguments) {
		args = arguments;
		SwingUtilities.invokeLater(new Program());
	}

	public void run() {
		int frameWidth = 1200;
		int frameHeight = 900;

		if (args.length == 2) {
			lapCount = Integer.parseInt(args[0]);
			tokenCount = Integer.parseInt(args[1]);

		} else if (args.length == 1) {
			lapCount = Integer.parseInt(args[0]);
			tokenCount = 2;
		} else {
			lapCount = 1;
			tokenCount = 4;
		}

		new RaceFrame(RaceBuilder.createRacingTokens(lapCount, tokenCount),
				frameWidth, frameHeight);
	}

}
