/* CS4010 Fall 2013
 * Instructor: John J. Antognoli
 * Student: Loc Nguyen (SSO ID: lhn7c5)
 * 
 * Project #2: Racing Tokens
 * Race tokens around the track
 */

package model;

import java.awt.Color;

public class RaceBuilder {

	public static Race createRacingTokens(int laps, int tokens) {
		Token token1 = new Token(Color.red, "Red Token");
		Token token2 = new Token(Color.blue, "Blue Token");
		Token token3 = new Token(Color.yellow, "Yellow Token");
		Token token4 = new Token(Color.green, "Green Token");

		Race race = new Race();
		race.setLaps(laps);

		if (tokens == 4) {
			race.addToken(token1);
			race.addToken(token2);
			race.addToken(token3);
			race.addToken(token4);
		} else if (tokens == 3) {
			race.addToken(token1);
			race.addToken(token2);
			race.addToken(token3);
		} else { // if tokens == 2
			race.addToken(token1);
			race.addToken(token2);
		}

		return race;
	}
}
